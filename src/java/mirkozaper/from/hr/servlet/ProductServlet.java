/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mirkozaper.from.hr.dal.Constants;
import mirkozaper.from.hr.dal.RepositoryFactory;
import mirkozaper.from.hr.model.Item;
import mirkozaper.from.hr.model.Product;

/**
 *
 * @author mirko
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("id");
        if(parameter!=null){
            try {
                HttpSession session = request.getSession();
                List<Item> cart = (List<Item>)session.getAttribute(Constants.Session.CART_ITEMS);
                if(cart==null){
                    cart=new ArrayList<>();
                }
                
                Product product = RepositoryFactory.getRepository().selectProduct(Integer.parseInt(parameter));
                
                boolean productAdded=false;
                
                for (Item item : cart) {
                    if(item.getId()==product.getId()){
                        item.setQuantity(item.getQuantity()+1);
                        productAdded=true;
                    }
                }
                
                if(!productAdded)
                    cart.add(new Item(product, 1));
                
                session.setAttribute(Constants.Session.CART_ITEMS, cart);
            } catch (Exception ex) {
                Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        response.sendRedirect("./cart.jsp");
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            HttpSession session = request.getSession();
            
            List<Item> cart = (List<Item>)session.getAttribute(Constants.Session.CART_ITEMS);
            
            if(cart==null){
                cart=new ArrayList<>();
            }
            
            Product product = RepositoryFactory.getRepository().selectProduct(productId);
            
            
            boolean productAdded=false;
            
            for (Item item : cart) {
                if(item.getId()==product.getId()){
                    item.setQuantity(item.getQuantity()+quantity);
                    productAdded=true;
                }
            }
            
            if(!productAdded)
                cart.add(new Item(product, quantity));
            
            session.setAttribute(Constants.Session.CART_ITEMS, cart);
            
            response.sendRedirect("./cart.jsp");
            
        }   catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

}
