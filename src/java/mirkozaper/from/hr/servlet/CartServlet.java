/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mirkozaper.from.hr.dal.Constants;
import mirkozaper.from.hr.model.Item;

/**
 *
 * @author mirko
 */
public class CartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("id");
        String clearStr = request.getParameter("clear");
        
        List<Item> cart = (List<Item>)request.getSession().getAttribute(Constants.Session.CART_ITEMS);
        
        if(parameter!=null){
            int itemId = Integer.parseInt(parameter);
            
            if(cart!=null){
                for (int i = 0; i < cart.size(); i++) {
                    if(cart.get(i).getId()==itemId){
                        cart.remove(cart.get(i));
                    }
                }
            }
        }
        
        if(clearStr!=null){
            boolean parseBoolean = Boolean.parseBoolean(clearStr);
            if(parseBoolean){
                if(cart!=null){
                    cart.clear();
                }
            }
        }
        
        
        response.sendRedirect("./cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdStr = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");
        
        
        if(itemIdStr!=null && quantityStr!=null){
            List<Item> cart = (List<Item>)request.getSession().getAttribute(Constants.Session.CART_ITEMS);
            
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);
            
            if(cart!=null){
                for (int i = 0; i < cart.size(); i++) {
                    if(cart.get(i).getId()==itemId){
                        cart.get(i).setQuantity(quantity);
                    }
                }
                
                float rez=0;
                
                for (Item i : cart) {
                    rez+=i.getPrice()*i.getQuantity();
                }
                
                
            }
        }
        
        
        
        response.sendRedirect("./cart.jsp");
    }
    
    

}
