package edu.neu.aedproject.rranjolkar.trading;

import java.util.ArrayList;
import java.util.List;

import edu.neu.aedproject.rranjolkar.IdObject;

public class Security extends IdObject {

    public static final String SIDE_BUY = "Buy";

    public static final String SIDE_SELL = "Sell";

    public static final String TYPE_EQUITY = "Equity";

    public static final String TYPE_FIXINC = "Fixed Income";

    public static final String TYPE_ALT = "Alternative";

    private String name;

    private String symbol;

    private String type;

    private List<Double> priceHistory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriceHistory(List<Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public List<Double> getPriceHistory() {
        return priceHistory;
    }

    public void addPriceHistory(double price) {
        priceHistory = priceHistory == null ? new ArrayList<>() : priceHistory;
        priceHistory.add(price);
    }

    @Override
    public String toString() {
        return name;
    }

}
