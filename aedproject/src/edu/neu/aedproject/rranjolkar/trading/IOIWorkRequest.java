package edu.neu.aedproject.rranjolkar.trading;

import java.util.Date;

import edu.neu.aedproject.rranjolkar.WorkRequest;

public class IOIWorkRequest extends WorkRequest {

    public static final String IOI_STATUS_IGNORED = "Ignored";

    public static final String IOI_STATUS_INTERESTED = "Interested";

    public static final String IOI_STATUS_SUCCESS = "Successful";

    public static final String IOI_STATUS_FAILURE = "Failure";

    public static final String SEC_CLASS_SPAM = "Spam";

    public static final String SEC_CLASS_THREAT = "Threat";

    private Broker broker;

    private Security security;

    private double quantity;

    private double priceLimit;

    private String message;

    private double riskPercent = 0;

    private Date submittedAt;

    private String securityClass;

    public IOIWorkRequest() {
    }

    public IOIWorkRequest(Broker broker, String message, Date submittedAt) {
        this.broker = broker;
        this.message = message;
        this.submittedAt = submittedAt;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public double getRiskPercent() {
        return riskPercent;
    }

    public void setRiskPercent(double risk) {
        this.riskPercent = risk;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Date getSubmittedAt() {
        return submittedAt;
    }

    @Override
    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void successful(boolean successful, String reason) {
        if (successful) {
            setStatus(IOI_STATUS_FAILURE);
            getNotes().add("IOI Failed: " + reason);
        } else {
            setStatus(IOI_STATUS_SUCCESS);
            getNotes().add("IOI Successful: " + reason);
        }
    }

    public String getSecurityClass() {
        return securityClass;
    }

    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

    @Override
    public String toString() {
        return message;
    }
}
