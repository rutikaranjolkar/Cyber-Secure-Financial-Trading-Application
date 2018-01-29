package edu.neu.aedproject.rranjolkar.trading;

import edu.neu.aedproject.rranjolkar.Network;

public class TradingNetwork extends Network {

    private SecurityDirectory securityDirectory;

    private BrokerDirectory brokerDirectory;

    public SecurityDirectory getSecurityDirectory() {
        if(securityDirectory==null) {
            securityDirectory = new SecurityDirectory();
        }
        return securityDirectory;
    }

    public void setSecurityDirectory(SecurityDirectory securityDirectory) {
        this.securityDirectory = securityDirectory;
    }

    public BrokerDirectory getBrokerDirectory() {
        if (brokerDirectory == null) {
            brokerDirectory = new BrokerDirectory();
        }
        return brokerDirectory;
    }

    public void setBrokerDirectory(BrokerDirectory brokerDirectory) {
        this.brokerDirectory = brokerDirectory;
    }

}
