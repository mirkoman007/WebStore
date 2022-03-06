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
public class ProductVM {
    private int id;
    private String name;
    private String imageUrl;
    private float price;
    private String category;

    public ProductVM(int id, String name, String imageUrl, float price, String category) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
    
}
