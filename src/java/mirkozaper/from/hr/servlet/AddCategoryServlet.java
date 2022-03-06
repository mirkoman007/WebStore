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
import mirkozaper.from.hr.model.Category;

/**
 *
 * @author mirko
 */
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String imageUrl = request.getParameter("imageUrl");
        
        try {
            RepositoryFactory.getRepository().createCategory(new Category(name, imageUrl));
        } catch (Exception ex) {
            Logger.getLogger(AddCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("./categories.jsp?page=succAdded");
    }


}
