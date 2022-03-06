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
public class User {
    private int id;
    private String username;
    private String password;
    private int userType;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String country;

    public User(String username, String password, String firstName, String lastName, String address, String zipCode, String city, String country) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public User(int id, String username, int userType, String firstName, String lastName, String address, String zipCode, String city, String country) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    
    
}
