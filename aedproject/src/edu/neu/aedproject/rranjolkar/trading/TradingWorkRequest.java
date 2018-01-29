package edu.neu.aedproject.rranjolkar.trading;

import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.services.LoginService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TradingWorkRequest extends WorkRequest {

    public static final String WORK_STATUS_PLACED = "Placed";

    private String side;

    private double quantity;

    private Security security;

    private double priceLimit;

    private ClientAccount clientAccount;

    private double remainingQuantity;

    private Broker broker;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public double getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    public double getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(double remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void place(Broker broker) {
        setStatus(WORK_STATUS_PLACED);
        setBroker(broker);
        getNotes().add("Placed with: " + broker);
        /**
         * This simulates broker action *
         */
        for (int i = 0; i < 30; i++) {
            double factor = 1 / (1 + 0.1 * Math.random());
            security.addPriceHistory(priceLimit * factor);
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(2000);
                        update(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void update(double qty) {
        if (remainingQuantity == 0) {
            return;
        } else if (WORK_STATUS_ERROR.equals(getStatus())) {
            getNotes().add("Cannot update while status is ERROR");
        } else {
            if (qty > remainingQuantity) {
                qty = remainingQuantity;
            }
            setStatus(WORK_STATUS_WIP);
            getNotes().add("Fulfilled quantity: " + qty);
            remainingQuantity = remainingQuantity - qty;
            if (remainingQuantity == 0) {
                resolve();

                TradingOrganization organization = (TradingOrganization) LoginService.getInstance().getCurrentOrganization();
                Holding cashHolding = organization.findCashHolding(clientAccount);
                double oldCashValue = cashHolding.getPrice();
                double transactionValue = quantity * priceLimit;
                double newCashValue = oldCashValue;
                Holding holding = organization.findHolding(clientAccount, security);
                if (Security.SIDE_BUY.equals(side)) {
                    if (transactionValue > oldCashValue) {
                        error("Busted trade: Not enough cash funds to buy.");
                    } else {
                        newCashValue = oldCashValue - transactionValue;
                        cashHolding.setPrice(newCashValue);
                        if (holding == null) {
                            holding = new Holding(clientAccount, security, quantity, priceLimit);
                            organization.getHoldings().add(holding);
                        } else {
                            double newQuantity = holding.getQuantity() + quantity;
                            holding.setPrice(priceLimit);
                            holding.setQuantity(newQuantity);
                        }
                    }
                } else {
                    newCashValue = oldCashValue + transactionValue;
                    if (holding == null) {
                        error("Busted trade: Not enough shares to sell.");
                    } else {
                        double oldQuantity = holding.getQuantity();
                        double newQuantity = oldQuantity - quantity;
                        if (newQuantity < 0) {
                            error("Busted trade: Not enough shares to sell.");
                        } else {
                            holding.setQuantity(newQuantity);
                            holding.setPrice(priceLimit);
                            cashHolding.setPrice(newCashValue);
                        }
                    }
                }
            }
        }
    }
}
