package edu.neu.aedproject.rranjolkar.trading;

import java.util.List;

import edu.neu.aedproject.rranjolkar.IdObject;
import java.util.ArrayList;

public class SecurityDirectory extends IdObject {

    private ArrayList<Security> securities;

    public void setSecurities(ArrayList<Security> securities) {
        this.securities = securities;
    }

    public List<Security> getSecurities() {
        if(securities==null) {
            securities = new ArrayList<>();
        }
        return securities;
    }

}
