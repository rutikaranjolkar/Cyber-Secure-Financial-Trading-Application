/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.security;

import edu.neu.aedproject.rranjolkar.WorkRequest;

/**
 *
 * @author Rutika Ranjolkar
 */
public class VacationLoginBlockedWorkRequest extends WorkRequest {

    public static final String VACATION_LOGIN = "Login attempted during vacation";

    private String blockReason;

    public VacationLoginBlockedWorkRequest() {
        getNotes().add("A login attempt was made while the user was on vacation");
        setMessage("A login attempt was made while the user was on vacation");
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

}
