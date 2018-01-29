package edu.neu.aedproject.rranjolkar;

public class Role extends IdObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
