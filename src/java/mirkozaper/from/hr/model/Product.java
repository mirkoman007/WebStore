/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mirkozaper.from.hr.model;

/**
 *
 * @author mirko
 */
public class Product {
    private int id;
    private String name;
    private String imageUrl;
    private float price;
    private String description;
    private int categoryId;

    public Product(String name, String imageUrl, float price, String description, int categoryId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    public Product(int id, String name, String imageUrl, float price, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }


}
