package edu.neu.aedproject.rranjolkar.trading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.aedproject.rranjolkar.Organization;
import edu.neu.aedproject.rranjolkar.Role;
import edu.neu.aedproject.rranjolkar.UserAccount;
import edu.neu.aedproject.rranjolkar.WorkQueue;
import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;

public class TradingOrganization extends Organization {

    private List<Holding> holdings = new ArrayList<>();

    private List<ClientAccount> accounts = new ArrayList<>();

    public TradingOrganization() {
        List<Role> roles = new ArrayList<>();
        Role pmRole = new Role("Portfolio Manager");
        Role traderRole = new Role("Trader");
        roles.add(pmRole);
        roles.add(traderRole);
        setRoles(roles);
    }

    public List<Holding> getHoldings() {
        if (holdings == null) {
            holdings = new ArrayList<>();
        }
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public List<ClientAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ClientAccount> accounts) {
        this.accounts = accounts;
    }

    public List<Holding> findHoldings(Security security) {
        List<Holding> results = new ArrayList<>();
        if (holdings != null && !holdings.isEmpty()) {
            for (Holding holding : holdings) {
                if (security.equals(holding.getSecurity())) {
                    results.add(holding);
                }
            }
        }
        return results;
    }

    public double findCashHoldingValue(ClientAccount account) {
        Holding result = findCashHolding(account);
        if (result != null) {
            return result.totalPrice();
        } else {
            return 0;
        }
    }

    public double findInvestedValue(ClientAccount account) {
        double value = 0;
        if (holdings != null && !holdings.isEmpty()) {
            for (Holding holding : holdings) {
                if (account.equals(holding.getClientAccount()) && holding.getSecurity() != null) {
                    value += holding.totalPrice();
                }
            }
        }
        return value;
    }

    public List<ClientAccount> findPortfolios(UserAccount portfolioManager) {
        List<ClientAccount> results = new ArrayList<>();
        if (accounts != null && !accounts.isEmpty()) {
            for (ClientAccount account : accounts) {
                if (portfolioManager.equals(account.getPortfolioManager())) {
                    results.add(account);
                }
            }
        }
        return results;
    }

    public TradingWorkRequest submitTradingWorkRequest(ClientAccount account,
            Security security, double quantity, double priceLimit, String side) {
        TradingWorkRequest request = new TradingWorkRequest();
        request.setClientAccount(account);
        request.setMessage("Security: " + security.getSymbol() + ", quantity: " + quantity);
        request.setNotes(new ArrayList<>(Arrays.asList("Request submitted.")));
        request.setPriceLimit(priceLimit);
        request.setQuantity(quantity);
        request.setRemainingQuantity(quantity);
        request.setSide(side);
        request.setSecurity(security);
        request.setSender(account.getPortfolioManager());
        getWorkQueue().getWorkRequests().add(request);
        return request;
    }

    public Map<String, Double> findHoldingsDistribution(ClientAccount account) {
        Map<String, Double> distribution = new HashMap<>();
        distribution.put(Security.TYPE_EQUITY, 0D);
        distribution.put(Security.TYPE_FIXINC, 0D);
        distribution.put(Security.TYPE_ALT, 0D);
        if (holdings != null && !holdings.isEmpty()) {
            for (Holding holding : holdings) {
                if (account.equals(holding.getClientAccount())) {
                    Security security = holding.getSecurity();
                    String secType = security.getType();
                    double currentValue = distribution.get(secType);
                    currentValue += holding.totalPrice();
                    distribution.put(secType, currentValue);
                }
            }
        }
        return distribution;
    }

    public List<Double> findPriceHistory(Security security, int total) {
        List<Double> history = new ArrayList<>();
        if (security.getPriceHistory() != null
                && !security.getPriceHistory().isEmpty()) {
            int index = security.getPriceHistory().size() > total ? total - 1
                    : security.getPriceHistory().size() - 1;
            security.getPriceHistory().subList(0, index);
        }
        return history;
    }

    public List<TradingWorkRequest> getTradingWorkRequestsForPortfolioManager(UserAccount manager) {
        WorkQueue workQueue = getWorkQueue();
        List<WorkRequest> requests = workQueue.getWorkRequests();
        List<TradingWorkRequest> results = new ArrayList();
        if (CommonUtils.isNotEmpty(requests)) {
            for (WorkRequest request : requests) {
                if (request instanceof TradingWorkRequest) {
                    TradingWorkRequest tradingWorkRequest = (TradingWorkRequest) request;
                    boolean canceled = request.getStatus().equals(WorkRequest.WORK_STATUS_CANCELLED);
                    if (!canceled && tradingWorkRequest.getSender().getUsername().equals(manager.getUsername())) {
                        results.add(tradingWorkRequest);
                    }
                }
            }
        }
        return results;
    }

    public List<TradingWorkRequest> getTradeMonitorRequests() {
        List<TradingWorkRequest> results = new ArrayList();
        WorkQueue workQueue = getWorkQueue();
        List<WorkRequest> requests = workQueue.getWorkRequests();
        if (CommonUtils.isNotEmpty(requests)) {
            for (WorkRequest request : requests) {
                if (request instanceof TradingWorkRequest) {
                    TradingWorkRequest tradingWorkRequest = (TradingWorkRequest) request;
                    String status = tradingWorkRequest.getStatus();
                    boolean isRejected = WorkRequest.WORK_STATUS_REJECTED.equals(status);
                    boolean isResolved = WorkRequest.WORK_STATUS_RESOLVED.equals(status);
                    boolean isCanceled = WorkRequest.WORK_STATUS_CANCELLED.equals(status);
                    boolean isReturned = WorkRequest.WORK_STATUS_RETURNED.equals(status);
                    boolean isCorrectStatus = !isRejected && !isResolved && !isCanceled && !isReturned;
                    if (tradingWorkRequest.getReceiver() == null && isCorrectStatus) {
                        results.add(tradingWorkRequest);
                    }
                }
            }
        }
        return results;
    }

    public List<TradingWorkRequest> getTradersOrders(UserAccount trader) {
        List<TradingWorkRequest> results = new ArrayList();
        WorkQueue workQueue = getWorkQueue();
        List<WorkRequest> requests = workQueue.getWorkRequests();
        if (CommonUtils.isNotEmpty(requests)) {
            for (WorkRequest request : requests) {
                if (request instanceof TradingWorkRequest) {
                    TradingWorkRequest tradingWorkRequest = (TradingWorkRequest) request;
                    String status = tradingWorkRequest.getStatus();
                    boolean isRejected = WorkRequest.WORK_STATUS_REJECTED.equals(status);
                    boolean isResolved = WorkRequest.WORK_STATUS_RESOLVED.equals(status);
                    boolean isCanceled = WorkRequest.WORK_STATUS_CANCELLED.equals(status);
                    UserAccount requestTrader = tradingWorkRequest.getReceiver();
                    boolean isMatchingTrader = requestTrader != null && requestTrader.getUsername().equals(trader.getUsername());
                    if (isMatchingTrader && !isRejected && !isResolved && !isCanceled) {
                        results.add(tradingWorkRequest);
                    }
                }
            }
        }
        return results;
    }

    public List<IOIWorkRequest> getIOIWorkRequests() {
        List<IOIWorkRequest> results = new ArrayList();
        WorkQueue workQueue = getWorkQueue();
        List<WorkRequest> requests = workQueue.getWorkRequests();
        if (CommonUtils.isNotEmpty(requests)) {
            for (WorkRequest request : requests) {
                if (request instanceof IOIWorkRequest) {
                    IOIWorkRequest tradingWorkRequest = (IOIWorkRequest) request;
                    String status = tradingWorkRequest.getStatus();
                    boolean isRejected = WorkRequest.WORK_STATUS_REJECTED.equals(status);
                    boolean isResolved = WorkRequest.WORK_STATUS_RESOLVED.equals(status);
                    boolean isCanceled = WorkRequest.WORK_STATUS_CANCELLED.equals(status);
                    if (!isRejected && !isResolved && !isCanceled) {
                        results.add(tradingWorkRequest);
                    }
                }
            }
        }
        return results;
    }

    public Holding findCashHolding(ClientAccount account) {
        Holding result = null;
        if (holdings != null && !holdings.isEmpty()) {
            for (Holding holding : holdings) {
                if (account.equals(holding.getClientAccount()) && holding.getSecurity() == null) {
                    result = holding;
                    break;
                }
            }
        }
        return result;
    }

    Holding findHolding(ClientAccount account, Security security) {
        Holding result = null;
        if (holdings != null && !holdings.isEmpty()) {
            for (Holding holding : holdings) {
                if (account.equals(holding.getClientAccount()) && holding.getSecurity() != null && holding.getSecurity().equals(security)) {
                    result = holding;
                    break;
                }
            }
        }
        return result;
    }

}
