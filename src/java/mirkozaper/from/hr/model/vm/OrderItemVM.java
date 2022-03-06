/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.model.vm;

/**
 *
 * @author mirko
 */
public class OrderItemVM {
    
    private int productId;
    private float price;
    private int quantity;
    private String name;
    private String imageUrl;
    private String description;

    public OrderItemVM(int productId, float price, int quantity, String name, String imageUrl, String description) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
