package edu.neu.aedproject.rranjolkar.trading.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.aedproject.rranjolkar.Ecosystem;
import edu.neu.aedproject.rranjolkar.Employee;
import edu.neu.aedproject.rranjolkar.Enterprise;
import edu.neu.aedproject.rranjolkar.EnterpriseDirectory;
import edu.neu.aedproject.rranjolkar.NetworkDirectory;
import edu.neu.aedproject.rranjolkar.Organization;
import edu.neu.aedproject.rranjolkar.OrganizationDirectory;
import edu.neu.aedproject.rranjolkar.Role;
import edu.neu.aedproject.rranjolkar.UserAccount;
import edu.neu.aedproject.rranjolkar.UserAccountDirectory;
import edu.neu.aedproject.rranjolkar.WorkQueue;
import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.security.SecurityOrganization;
import edu.neu.aedproject.rranjolkar.trading.Broker;
import edu.neu.aedproject.rranjolkar.trading.BrokerDirectory;
import edu.neu.aedproject.rranjolkar.trading.ClientAccount;
import edu.neu.aedproject.rranjolkar.trading.Holding;
import edu.neu.aedproject.rranjolkar.trading.IOIWorkRequest;
import edu.neu.aedproject.rranjolkar.trading.Security;
import edu.neu.aedproject.rranjolkar.trading.SecurityDirectory;
import edu.neu.aedproject.rranjolkar.trading.TradingNetwork;
import edu.neu.aedproject.rranjolkar.trading.TradingOrganization;

public class EcosystemBuilder {

    private static Security IBM = buildSecurity("IBM", "IBM USA", 120);
    private static Security GOOG = buildSecurity("GOOG", "Google Inc", 450);
    private static Security AAPL = buildSecurity("AAPL", "Apple Inc", 300);

    public static List<Security> SECURITIES = Arrays.asList(IBM, GOOG, AAPL);

    public static TradingNetwork MODEL_NETWORK = new TradingNetwork();
    private static Broker JPM = buildBroker("JPM", "JP Morgan");
    private static Broker MORGAN = buildBroker("MORGAN", "Morgan Brokerage");
    private static Broker RBC = buildBroker("RBC", "Royal Bank of Canada");

    public static List<Broker> BROKERS = Arrays.asList(JPM, MORGAN, RBC);

    public static Ecosystem build() {
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.setName("Financial Ecosystem");
        ecosystem.setNetworkDirectory(buildNetworkDirectory());
        return ecosystem;
    }

    private static NetworkDirectory buildNetworkDirectory() {
        NetworkDirectory directory = new NetworkDirectory();
        buildUSTradingNetwork();
        directory.setNetworks(new ArrayList<>(Arrays.asList(MODEL_NETWORK)));
        return directory;
    }

    private static void buildUSTradingNetwork() {
        MODEL_NETWORK.setName("North America Trading Network");
        MODEL_NETWORK.setBrokerDirectory(buildBrokerDirectory());
        MODEL_NETWORK.setEnterpriseDirectory(buildEnterpriseDirectory());
        MODEL_NETWORK.setSecurityDirectory(buildSecurityDirectory());
    }

    private static SecurityDirectory buildSecurityDirectory() {
        SecurityDirectory directory = new SecurityDirectory();
        ArrayList<Security> securities = new ArrayList<>();

        securities.add(GOOG);
        securities.add(AAPL);
        securities.add(IBM);

        directory.setSecurities(securities);
        return directory;
    }

    private static Security buildSecurity(String sym, String name, double price) {
        Security sec = new Security();
        sec.setSymbol(sym);
        sec.setName(name);
        for (int i = 0; i < 30; i++) {
            double factor = 1 / (1 + 0.1 * Math.random());
            sec.addPriceHistory(price * factor);
        }
        return sec;
    }

    private static EnterpriseDirectory buildEnterpriseDirectory() {
        EnterpriseDirectory enterpriseDirectory = new EnterpriseDirectory();
        List<Enterprise> enterprises = new ArrayList<>();
        enterprises.add(buildEnterprise());
        enterpriseDirectory.setEnterprises(enterprises);
        return enterpriseDirectory;
    }

    private static Enterprise buildEnterprise() {
        Enterprise enterprise = new Enterprise();
        enterprise.setName("Jackson Investments");
        OrganizationDirectory organizationDirectory = new OrganizationDirectory();
        List<Organization> organizations = new ArrayList<>();
        organizations.add(buildTradingOrganization());
        organizations.add(buildSecurityOrganization());
        organizationDirectory.setOrganizations(organizations);
        enterprise.setOrganizationDirectory(organizationDirectory);
        return enterprise;
    }

    private static Organization buildSecurityOrganization() {
        SecurityOrganization organization = new SecurityOrganization();

        List<Role> roles = new ArrayList<>();
        Role secManagerRole = new Role("Security Manager");
        Role secAnalystRole = new Role("Security Analyst");
        roles.add(secManagerRole);

        Employee secManager = new Employee();
        secManager.setName("Security Manager");
        UserAccount smUserAccount = new UserAccount();
        smUserAccount.setTotpKey("sm123");
        smUserAccount.setEmployee(secManager);
        smUserAccount.setUsername("sm");
        smUserAccount.setPassword("pass");
        smUserAccount.setRole(secManagerRole);
        smUserAccount.getLoginHours().add(8);
        smUserAccount.getLoginHours().add(9);
        smUserAccount.getLoginHours().add(8);
        smUserAccount.getLoginHours().add(9);
        smUserAccount.getLoginHours().add(9);

        Employee secAnalyst = new Employee();
        secAnalyst.setName("Security Sam");
        UserAccount secAnalystAccount = new UserAccount();
        secAnalystAccount.setTotpKey("security123");
        secAnalystAccount.setEmployee(secAnalyst);
        secAnalystAccount.setUsername("security");
        secAnalystAccount.setPassword("pass");
        secAnalystAccount.setRole(secAnalystRole);

        UserAccountDirectory userAccountDirectory = new UserAccountDirectory();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(smUserAccount);
        userAccounts.add(secAnalystAccount);
        userAccountDirectory.setUserAccounts(userAccounts);
        organization.setUserAccountDirectory(userAccountDirectory);

        List<WorkRequest> reqs = new ArrayList<>();

        WorkQueue workQueue = new WorkQueue();
        workQueue.setWorkRequests(reqs);
        organization.setWorkQueue(workQueue);

        return organization;
    }

    private static TradingOrganization buildTradingOrganization() {
        TradingOrganization organization = new TradingOrganization();

        List<Role> roles = new ArrayList<>();
        Role pmRole = new Role("Portfolio Manager");
        Role traderRole = new Role("Trader");
        roles.add(pmRole);

        Employee portfolioManager = new Employee();
        portfolioManager.setName("Porty Paul");
        UserAccount pmUserAccount = new UserAccount();
        pmUserAccount.setTotpKey("pm123");
        pmUserAccount.setEmployee(portfolioManager);
        pmUserAccount.setUsername("pm");
        pmUserAccount.setPassword("pass");
        pmUserAccount.setRole(pmRole);
        pmUserAccount.getLoginHours().add(8);
        pmUserAccount.getLoginHours().add(9);
        pmUserAccount.getLoginHours().add(8);
        pmUserAccount.getLoginHours().add(9);
        pmUserAccount.getLoginHours().add(9);

        Employee trader = new Employee();
        trader.setName("Trader Joe");
        UserAccount traderAccount = new UserAccount();
        traderAccount.setTotpKey("trader123");
        traderAccount.setEmployee(trader);
        traderAccount.setUsername("trader");
        traderAccount.setPassword("pass");
        traderAccount.setRole(traderRole);

        List<ClientAccount> accounts = new ArrayList<>();

        ClientAccount mit = new ClientAccount();
        mit.setName("MIT");
        mit.setPortfolioManager(pmUserAccount);
        accounts.add(mit);

        ClientAccount neu = new ClientAccount();
        neu.setName("Northeastern University");
        neu.setPortfolioManager(pmUserAccount);
        accounts.add(neu);

        UserAccountDirectory userAccountDirectory = new UserAccountDirectory();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(pmUserAccount);
        userAccounts.add(traderAccount);
        userAccountDirectory.setUserAccounts(userAccounts);
        organization.setUserAccountDirectory(userAccountDirectory);

        List<Holding> holdings = new ArrayList<>();
        holdings.add(new Holding(mit, GOOG, 2000, 12));
        holdings.add(new Holding(mit, null, 1, 400000));
        holdings.add(new Holding(neu, AAPL, 3000, 22));
        holdings.add(new Holding(neu, null, 1, 600000));
        organization.setHoldings(holdings);

        List<WorkRequest> reqs = new ArrayList<>();
        WorkQueue workQueue = new WorkQueue();
        workQueue.setWorkRequests(reqs);
        organization.setWorkQueue(workQueue);

        organization.setAccounts(accounts);
        return organization;
    }

    private static BrokerDirectory buildBrokerDirectory() {
        BrokerDirectory brokerDirectory = new BrokerDirectory();
        brokerDirectory.setBrokers(new ArrayList<>(BROKERS));
        return brokerDirectory;
    }

    private static Broker buildBroker(String sym, String name) {
        Broker broker = new Broker();
        broker.setName(name);
        broker.setSymbol(sym);
        return broker;
    }
}
