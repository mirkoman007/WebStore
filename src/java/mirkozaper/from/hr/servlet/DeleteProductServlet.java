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

/**
 *
 * @author mirko
 */
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String parameter = request.getParameter("id");
        
        if(parameter!=null){
            try {
                int idProduct = Integer.parseInt(parameter);
                
                RepositoryFactory.getRepository().deleteProduct(idProduct);
            } catch (Exception ex) {
                Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        response.sendRedirect("./products.jsp?page=succDeleted");
    }


}
