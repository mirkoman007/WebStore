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
import javax.servlet.http.HttpSession;
import mirkozaper.from.hr.dal.Constants;
import mirkozaper.from.hr.dal.RepositoryFactory;
import mirkozaper.from.hr.model.LoginLog;
import mirkozaper.from.hr.model.User;

/**
 *
 * @author mirko
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            HttpSession session = request.getSession();
            
            boolean credentialsValid = RepositoryFactory.getRepository().userLogin(username, password);
            
            if(credentialsValid){
                User user = RepositoryFactory.getRepository().selectUser(username);
                session.setAttribute(Constants.Session.LOGGED_IN_USER, user);
                RepositoryFactory.getRepository().createLoginLog(new LoginLog(user.getId(),request.getRemoteAddr()));
                
                response.sendRedirect("./index.jsp");
            }
            else {
                response.sendRedirect("./login.jsp");
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
