/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.model;

/**
 *
 * @author mirko
 */
public class Item extends Product{
    
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(Product p,int quantity) {
        super(p.getId(), p.getName(), p.getImageUrl(), p.getPrice(), p.getDescription(), p.getCategoryId());
        this.quantity = quantity;
    }
    
    
    
}
