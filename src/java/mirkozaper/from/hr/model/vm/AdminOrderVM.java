/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.model.vm;

import java.time.LocalDateTime;

/**
 *
 * @author mirko
 */
public class AdminOrderVM {
    
    private int id;
    private String username;
    private int paymentType;
    private LocalDateTime dateTime;

    public AdminOrderVM(int id, String username, int paymentType, LocalDateTime dateTime) {
        this.id = id;
        this.username = username;
        this.paymentType = paymentType;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    
}
