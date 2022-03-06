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
import mirkozaper.from.hr.model.Product;

/**
 *
 * @author mirko
 */
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String imageUrl = request.getParameter("imageUrl");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String categoryId = request.getParameter("categoryId");
        
        try {
            RepositoryFactory.getRepository().updateProduct(new Product(Integer.parseInt(id),name, imageUrl, Float.parseFloat(price), description, Integer.parseInt(categoryId)));
        } catch (Exception ex) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("./products.jsp?page=succUpdated");
    }

}
