/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.security;

import edu.neu.aedproject.rranjolkar.Organization;
import edu.neu.aedproject.rranjolkar.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rutika Ranjolkar
 */
public class SecurityOrganization extends Organization {

    public SecurityOrganization() {
        List<Role> roles = new ArrayList<>();
        Role secManagerRole = new Role("Security Manager");
        Role secAnalystRole = new Role("Security Analyst");
        roles.add(secManagerRole);
        roles.add(secAnalystRole);
        setRoles(roles);
    }

}
