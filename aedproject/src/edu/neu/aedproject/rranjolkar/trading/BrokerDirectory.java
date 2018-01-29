package edu.neu.aedproject.rranjolkar.trading;

import java.util.List;

import edu.neu.aedproject.rranjolkar.IdObject;
import java.util.ArrayList;

public class BrokerDirectory extends IdObject {

    private List<Broker> brokers;

    public List<Broker> getBrokers() {
        if (brokers == null) {
            brokers = new ArrayList<>();
        }
        return brokers;
    }

    public void setBrokers(List<Broker> brokers) {
        this.brokers = brokers;
    }

}
