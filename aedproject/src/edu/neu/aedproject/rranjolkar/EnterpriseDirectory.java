package edu.neu.aedproject.rranjolkar;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseDirectory extends IdObject {

    private List<Enterprise> enterprises = new ArrayList<>();

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprises = enterprises == null ? new ArrayList<>()
                : enterprises;
    }

    public List<Enterprise> getEnterprises() {
        if(enterprises==null) {
            enterprises = new ArrayList();
        }
        return enterprises;
    }
}
