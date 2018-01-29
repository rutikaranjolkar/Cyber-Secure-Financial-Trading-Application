/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar;

import java.util.Date;

/**
 *
 * @author Rutika Ranjolkar
 */
public class AuditItem {

    private Date date;

    private String action;

    public AuditItem(Date date, String action) {
        this.date = date;
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
