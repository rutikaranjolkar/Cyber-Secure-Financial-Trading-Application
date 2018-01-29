/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.services;

import edu.neu.aedproject.rranjolkar.AuditItem;
import edu.neu.aedproject.rranjolkar.Ecosystem;
import edu.neu.aedproject.rranjolkar.Enterprise;
import edu.neu.aedproject.rranjolkar.Network;
import edu.neu.aedproject.rranjolkar.Organization;
import edu.neu.aedproject.rranjolkar.UserAccount;
import edu.neu.aedproject.rranjolkar.security.SecurityOrganization;
import edu.neu.aedproject.rranjolkar.security.VacationLoginBlockedWorkRequest;
import edu.neu.aedproject.rranjolkar.ui.GenericJFrame;
import edu.neu.aedproject.rranjolkar.util.OTPUtils;
import java.util.Date;

/**
 *
 * @author Rutika Ranjolkar
 */
public class LoginService {

    private static LoginService instance;
    
    private final boolean SKIP_OTP_AUTH = false;

    private UserAccount currentAccount;

    private Network currentNetwork;

    private Enterprise currentEnterprise;

    private Organization currentOrganization;

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public UserAccount authenticate(String username, String password) {

        Ecosystem ecosystem = GenericJFrame.getInstance().getEcosystem();

        /**
         * Special handling for sysadmin *
         */
        if ("sysadmin".equals(username) && "pass".equals(password)) {
            UserAccount sysadmin = new UserAccount();
            sysadmin.setUsername("sysadmin");
            return sysadmin;
        }

        for (Network network : ecosystem.getNetworkDirectory().getNetworks()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterprises()) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizations()) {
                    for (UserAccount user : organization.getUserAccountDirectory().getUserAccounts()) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            currentAccount = user;
                            currentNetwork = network;
                            currentEnterprise = enterprise;
                            currentOrganization = organization;
                            if (!user.currentlyOnVacation() && !user.isBlocked()) {
                                user.setLastNormalLoginDate(new Date());
                                user.getAuditTrail().add(new AuditItem(new Date(), "Logged in normally"));
                            } else if (user.currentlyOnVacation()) {
                                user.setBlocked(true);
                                SecurityOrganization securityOrganization = currentEnterprise.getOrganization(SecurityOrganization.class);
                                VacationLoginBlockedWorkRequest request = new VacationLoginBlockedWorkRequest();
                                request.setSender(user);
                                securityOrganization.getWorkQueue().getWorkRequests().add(request);
                                user.getAuditTrail().add(new AuditItem(new Date(), "Account Blocked (attempted login on vacation)"));
                            }
                            return user;
                        }
                    }
                }
            }
        }

        return null;
    }

    public boolean userExists(String username) {
        Ecosystem ecosystem = GenericJFrame.getInstance().getEcosystem();
        for (Network network : ecosystem.getNetworkDirectory().getNetworks()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterprises()) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizations()) {
                    for (UserAccount user : organization.getUserAccountDirectory().getUserAccounts()) {
                        if (user.getUsername().equals(username)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean secondStepAuthenticate(UserAccount user, String code) {
        String expectedKey = OTPUtils.generateKey(user.getTotpKey());
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        return code.trim().equalsIgnoreCase(expectedKey) || SKIP_OTP_AUTH;
    }

    public void logout() {
        currentAccount.getAuditTrail().add(new AuditItem(new Date(), "Logged out normally"));
        this.currentAccount = null;
    }

    public UserAccount getCurrentAccount() {
        return currentAccount;
    }

    public Enterprise getCurrentEnterprise() {
        return currentEnterprise;
    }

    public Network getCurrentNetwork() {
        return currentNetwork;
    }

    public Organization getCurrentOrganization() {
        return currentOrganization;
    }
}
