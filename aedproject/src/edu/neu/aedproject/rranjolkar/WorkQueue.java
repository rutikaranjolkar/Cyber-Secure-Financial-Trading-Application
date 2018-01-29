package edu.neu.aedproject.rranjolkar;

import java.util.ArrayList;
import java.util.List;

public class WorkQueue {

    private List<WorkRequest> workRequests = new ArrayList<>();

    public void setWorkRequests(List<WorkRequest> workRequests) {
        this.workRequests = workRequests == null ? new ArrayList<>()
                : workRequests;
    }

    public List<WorkRequest> getWorkRequests() {
        if(workRequests==null) {
            workRequests = new ArrayList<>();
        }
        return workRequests;
    }
}
