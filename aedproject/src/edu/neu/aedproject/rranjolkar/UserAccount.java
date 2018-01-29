package edu.neu.aedproject.rranjolkar;

import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount extends IdObject {

    private String username;

    private String password;

    private Employee employee;

    private WorkQueue workQueue;

    private Role role;

    private List<Integer> loginHours;

    private Date vacationStartDate = CommonUtils.parseDate(1800, 1, 1, 1, 0);

    private Date vacationEndDate = CommonUtils.parseDate(1800, 1, 2, 1, 0);

    private String vacationDetails;

    private Date lastNormalLoginDate = new Date();

    private boolean blocked;

    private int securityRiskNumber = 0;

    private List<AuditItem> auditTrail;

    private String totpKey;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Integer> getLoginHours() {
        if (loginHours == null) {
            loginHours = new ArrayList<>();
        }
        return loginHours;
    }

    public void setLoginHours(List<Integer> loginHours) {
        this.loginHours = loginHours;
    }

    public Date getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(Date vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public Date getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(Date vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public Date getLastNormalLoginDate() {
        return lastNormalLoginDate;
    }

    public void setLastNormalLoginDate(Date lastNormalLoginDate) {
        this.lastNormalLoginDate = lastNormalLoginDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
        this.securityRiskNumber++;
    }

    public String getVacationDetails() {
        return vacationDetails;
    }

    public void setVacationDetails(String vacationDetails) {
        this.vacationDetails = vacationDetails;
    }

    public int getSecurityRiskNumber() {
        return securityRiskNumber;
    }

    public void setSecurityRiskNumber(int securityRiskNumber) {
        this.securityRiskNumber = securityRiskNumber;
    }

    public List<AuditItem> getAuditTrail() {
        if (auditTrail == null) {
            auditTrail = new ArrayList<>();
        }
        return auditTrail;
    }

    public void setAuditTrail(List<AuditItem> auditTrail) {
        this.auditTrail = auditTrail;
    }

    public boolean currentlyOnVacation() {
        Date date = new Date();
        return vacationStartDate.compareTo(date) <= 0 && vacationEndDate.compareTo(date) >= 0;
    }

    public String getTotpKey() {
        return totpKey;
    }

    public void setTotpKey(String totpKey) {
        this.totpKey = totpKey;
    }

    public void backFromVacation() {
        this.blocked = false;
        this.vacationStartDate = CommonUtils.parseDate(1800, 1, 1, 1, 0);
        this.vacationEndDate = CommonUtils.parseDate(1800, 1, 1, 2, 0);
        this.vacationDetails = null;
        this.securityRiskNumber = 0;
    }

    @Override
    public String toString() {
        return employee.getName();
    }
}
