package edu.neu.aedproject.rranjolkar;

import java.util.ArrayList;
import java.util.List;

public class UserAccountDirectory {

    private List<UserAccount> userAccounts;

    public List<UserAccount> getUserAccounts() {
        if(userAccounts==null) {
            userAccounts = new ArrayList<>();
        }
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

}
