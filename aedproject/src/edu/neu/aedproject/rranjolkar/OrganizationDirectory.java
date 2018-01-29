package edu.neu.aedproject.rranjolkar;

import java.util.ArrayList;
import java.util.List;

public class OrganizationDirectory {

    private String name;

    private List<Organization> organizations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Organization> getOrganizations() {
        if (organizations == null) {
            organizations = new ArrayList<>();
        }
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

}
