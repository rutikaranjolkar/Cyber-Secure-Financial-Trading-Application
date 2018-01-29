package edu.neu.aedproject.rranjolkar.trading;

import edu.neu.aedproject.rranjolkar.IdObject;
import edu.neu.aedproject.rranjolkar.UserAccount;

public class ClientAccount extends IdObject {

    private String name;

    private UserAccount portfolioManager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserAccount getPortfolioManager() {
        return portfolioManager;
    }

    public void setPortfolioManager(UserAccount portfolioManager) {
        this.portfolioManager = portfolioManager;
    }

    @Override
    public String toString() {
        return name;
    }

}
