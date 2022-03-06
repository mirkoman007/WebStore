/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.servlet;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mirkozaper.from.hr.dal.RepositoryFactory;

/**
 *
 * @author mirko
 */
public class DeleteCategoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String parameter = request.getParameter("id");
        
        if(parameter!=null){
            try {
                int idCategory = Integer.parseInt(parameter);
                
                RepositoryFactory.getRepository().deleteCategory(idCategory);
            } catch (SQLServerException ex) {
                response.sendRedirect("./categories.jsp?page=notEmpty");
                return;
            } catch (Exception ex) {
                Logger.getLogger(DeleteCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        response.sendRedirect("./categories.jsp?page=succDeleted");
    }


}
