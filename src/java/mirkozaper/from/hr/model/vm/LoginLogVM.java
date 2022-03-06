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
public class LoginLogVM {
    private int id;
    private String username;
    private LocalDateTime dateTime;
    private String ipAddress;

    public LoginLogVM(int id, String username, LocalDateTime dateTime, String ipAddress) {
        this.id = id;
        this.username = username;
        this.dateTime = dateTime;
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    
    
}
