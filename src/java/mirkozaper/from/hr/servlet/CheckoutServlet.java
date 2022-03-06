/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mirkozaper.from.hr.dal.Constants;
import mirkozaper.from.hr.dal.RepositoryFactory;
import mirkozaper.from.hr.model.Item;
import mirkozaper.from.hr.model.User;

/**
 *
 * @author mirko
 */
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String zip = request.getParameter("zip");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        
        String paymentMethod = request.getParameter("paymentMethod");
        
        if(paymentMethod.equals("paypal")){
            try {
                List<Item> cart = (List<Item>)request.getSession().getAttribute(Constants.Session.CART_ITEMS);
                User user=(User)request.getSession().getAttribute(Constants.Session.LOGGED_IN_USER);
                int orderId=RepositoryFactory.getRepository().createOrder(2, user.getId(), firstName, lastName, address, zip, city, country, cart);
                response.sendRedirect("./checkout-complete.jsp?orderId="+orderId);

            } catch (Exception ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                List<Item> cart = (List<Item>)request.getSession().getAttribute(Constants.Session.CART_ITEMS);
                User user=(User)request.getSession().getAttribute(Constants.Session.LOGGED_IN_USER);
                int orderId=RepositoryFactory.getRepository().createOrder(1, user.getId(), firstName, lastName, address, zip, city, country, cart);
                response.sendRedirect("./checkout-complete.jsp?orderId="+orderId);

            } catch (Exception ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }


}
