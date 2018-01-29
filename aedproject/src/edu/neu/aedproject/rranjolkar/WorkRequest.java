package edu.neu.aedproject.rranjolkar;

import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkRequest {

    public static final String WORK_STATUS_SUBMITTED = "Submitted";

    public static final String WORK_STATUS_RECEIVED = "Received";

    public static final String WORK_STATUS_WIP = "In Progress";

    public static final String WORK_STATUS_RETURNED = "Returned";

    public static final String WORK_STATUS_RESOLVED = "Resolved";

    public static final String WORK_STATUS_CANCELLED = "Cancelled";

    public static final String WORK_STATUS_REJECTED = "Rejected";

    public static final String WORK_STATUS_ERROR = "Error";

    private String message;

    private UserAccount sender;

    private UserAccount receiver;

    private String status;

    private Date submittedAt;

    private Date receivedAt;

    private Date resolvedAt;

    private List<String> notes = new ArrayList<>();

    private boolean escalated = false;

    private String escalationNotes = null;

    public WorkRequest() {
        this.status = WORK_STATUS_SUBMITTED;
        this.submittedAt = new Date();
        this.notes.add("Request submitted");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Date resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public String getEscalationNotes() {
        return escalationNotes;
    }

    public void setEscalationNotes(String escalationNotes) {
        this.escalationNotes = escalationNotes;
    }

    public void receive(UserAccount receiver) {
        this.receiver = receiver;
        this.receivedAt = new Date();
        this.setStatus(WORK_STATUS_RECEIVED);
        this.notes.add("Status updated to Received");
    }

    public void resolve() {
        this.status = WORK_STATUS_RESOLVED;
        this.notes.add("Status updated to Resolved");
    }

    public void resubmit(String reason) {
        this.status = WORK_STATUS_SUBMITTED;
        this.notes.add("Resubmitted to sender: " + reason);
    }

    public void returnToSender(String reason) {
        this.status = WORK_STATUS_RETURNED;
        this.notes.add("Request Returned: " + reason);
    }

    public void cancel(String reason) {
        this.status = WORK_STATUS_CANCELLED;
        this.notes.add("Request Cancelled: " + reason);
    }

    public void reject(String reason) {
        this.status = WORK_STATUS_REJECTED;
        this.notes.add("Request Rejected: " + reason);
    }

    public void error(String reason) {
        this.status = WORK_STATUS_ERROR;
        this.notes.add("Request in error: " + reason);
        this.receiver = null;
    }

    public String lastNote() {
        if (CommonUtils.isNotEmpty(notes)) {
            return notes.get(notes.size() - 1);
        }
        return null;
    }

    public boolean isEscalated() {
        return escalated;
    }

    public void setEscalated(boolean escalated) {
        this.escalated = escalated;
    }

    @Override
    public String toString() {
        return message;
    }

}
