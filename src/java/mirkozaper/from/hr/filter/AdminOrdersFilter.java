/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import mirkozaper.from.hr.dal.Constants;
import mirkozaper.from.hr.dal.RepositoryFactory;
import mirkozaper.from.hr.model.vm.AdminOrderVM;

/**
 *
 * @author mirko
 */
public class AdminOrdersFilter implements Filter {
    
    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AdminOrdersFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminOrdersFilter:DoBeforeProcessing");
        }
        
        try {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            
            String username = request.getParameter("username");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            
            
            List<AdminOrderVM> orders;
            
            if(username!=null && username.length()>0 && dateFrom!=null && dateFrom.length()>0 && dateTo!=null && dateTo.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdmin(username,Date.valueOf(dateFrom),Date.valueOf(dateTo));
            }
            else if(username!=null && username.length()>0 && dateFrom!=null && dateFrom.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdminFrom(username,Date.valueOf(dateFrom));
            }
            else if(username!=null && username.length()>0 &&dateTo!=null && dateTo.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdminTo(username,Date.valueOf(dateTo));
            }
            else if(username!=null && username.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdmin(username);
            }
            else if(dateFrom!=null && dateFrom.length()>0 && dateTo!=null && dateTo.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdmin(Date.valueOf(dateFrom),Date.valueOf(dateTo));
            }
            else if(dateFrom!=null && dateFrom.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdminFrom(Date.valueOf(dateFrom));
            }
            else if(dateTo!=null && dateTo.length()>0){
                orders = RepositoryFactory.getRepository().selectOrdersByAdminTo(Date.valueOf(dateTo));
            }
            else{
                orders = RepositoryFactory.getRepository().selectOrdersByAdmin();
            }
            
            httpRequest.setAttribute(Constants.Request.ORDERS,orders);
            
        } catch (Exception ex) {
            Logger.getLogger(AdminOrdersFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminOrdersFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("AdminOrdersFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AdminOrdersFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AdminOrdersFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminOrdersFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
