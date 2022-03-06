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
public class UserOrderVM {
    
    private int id;
    private int paymentType;
    private LocalDateTime dateTime;

    public int getId() {
        return id;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public UserOrderVM(int id, int paymentType, LocalDateTime dateTime) {
        this.id = id;
        this.paymentType = paymentType;
        this.dateTime = dateTime;
    }

    
}
