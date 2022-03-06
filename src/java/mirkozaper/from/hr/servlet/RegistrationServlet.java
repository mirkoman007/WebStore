/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mirkozaper.from.hr.dal.RepositoryFactory;
import mirkozaper.from.hr.model.User;

/**
 *
 * @author mirko
 */
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            String address = request.getParameter("address");
            String zip = request.getParameter("zip");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            
            
            if(!password.equals(rePassword)){
                response.sendRedirect("./register.jsp?page=passnotmatch");
                return;
            }
            
            boolean userAlreadyExist = !RepositoryFactory.getRepository().userReg(new User(username, password, firstName, lastName, address, zip, city, country));
            
            if(userAlreadyExist){
                response.sendRedirect("./register.jsp?page=already");
                return;
            }
            else{
                response.sendRedirect("./login.jsp");
                return;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }


}
