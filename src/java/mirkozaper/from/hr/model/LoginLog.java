/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.model;

import java.time.LocalDateTime;

/**
 *
 * @author mirko
 */
public class LoginLog {
    
    private int id;
    private int userID;
    private LocalDateTime dateTime;
    private String ipAddress;

    public LoginLog(int userID, String ipAddress) {
        this.userID = userID;
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }


    
}
