package edu.neu.aedproject.rranjolkar;

import java.util.ArrayList;
import java.util.List;

public class Organization extends IdObject {

    private String name;

    private EmployeeDirectory employeeDirectory;

    private UserAccountDirectory userAccountDirectory;

    private WorkQueue workQueue;

    private List<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }

    public void setEmployeeDirectory(EmployeeDirectory employeeDirectory) {
        this.employeeDirectory = employeeDirectory;
    }

    public WorkQueue getWorkQueue() {
        if (workQueue == null) {
            workQueue = new WorkQueue();
        }
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        if (userAccountDirectory == null) {
            userAccountDirectory = new UserAccountDirectory();
        }
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }

    public List<WorkRequest> findUnassignedWorkRequests() {
        List<WorkRequest> results = new ArrayList<>();
        for (WorkRequest request : getWorkQueue().getWorkRequests()) {
            if (request.getReceiver() == null) {
                results.add(request);
            }
        }
        return results;
    }

    public List<WorkRequest> findAssignedWorkRequests(UserAccount user) {
        List<WorkRequest> results = new ArrayList<>();
        for (WorkRequest request : getWorkQueue().getWorkRequests()) {
            UserAccount receiver = request.getReceiver();
            if (receiver != null && receiver.getUsername().equals(user.getUsername())) {
                results.add(request);
            }
        }
        return results;
    }

    @Override
    public String toString() {
        return name;
    }

}
