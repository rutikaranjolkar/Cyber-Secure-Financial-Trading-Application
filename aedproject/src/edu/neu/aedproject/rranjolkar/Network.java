package edu.neu.aedproject.rranjolkar;

public class Network extends IdObject {

    private String name;

    private EnterpriseDirectory enterpriseDirectory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnterpriseDirectory getEnterpriseDirectory() {
        if (enterpriseDirectory == null) {
            enterpriseDirectory = new EnterpriseDirectory();
        }
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(EnterpriseDirectory enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }

    @Override
    public String toString() {
        return name;
    }
}
