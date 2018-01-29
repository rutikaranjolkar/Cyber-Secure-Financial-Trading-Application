package edu.neu.aedproject.rranjolkar;

public class Enterprise extends IdObject {

    private String name;

    private OrganizationDirectory organizationDirectory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        if (organizationDirectory == null) {
            organizationDirectory = new OrganizationDirectory();
        }
        return organizationDirectory;
    }

    public void setOrganizationDirectory(
            OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    public <T extends Organization> T getOrganization(Class<T> orgClass) {
        for (Organization organization : organizationDirectory.getOrganizations()) {
            if (orgClass.isInstance(organization)) {
                return (T) organization;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

}
