/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.ai;

import edu.neu.aedproject.rranjolkar.services.LoginService;
import edu.neu.aedproject.rranjolkar.trading.Broker;
import edu.neu.aedproject.rranjolkar.trading.IOIWorkRequest;
import edu.neu.aedproject.rranjolkar.trading.TradingNetwork;
import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;

/**
 *
 * @author Rutika Ranjolkar
 */
public class MachineLearningUtil {

    public static void classifyIOIs(List<IOIWorkRequest> iois) {
        TradingNetwork network = (TradingNetwork) LoginService.getInstance().getCurrentNetwork();
        List<Broker> brokers = network.getBrokerDirectory().getBrokers();
        List<DoublePoint> doublePoints = new ArrayList<>();
        if (CommonUtils.isNotEmpty(iois)) {
            for (IOIWorkRequest ioi : iois) {
                ioi.setRiskPercent(0);
                Broker ioiBroker = ioi.getBroker();
                double brokerIndex = brokers.indexOf(ioiBroker);
                double[] input = new double[2];
                input[0] = brokerIndex * 2;
                int hour = CommonUtils.getFieldFromDate(ioi.getSubmittedAt(), Calendar.HOUR_OF_DAY);
                int time = hour;
                input[1] = time;
                DoublePoint doublePoint = new DoublePoint(input);
                doublePoints.add(doublePoint);
            }
            int minPoints = 4;
            DBSCANClusterer<DoublePoint> clusterer = new DBSCANClusterer<>(1, minPoints);
            List<Cluster<DoublePoint>> clusters = clusterer.cluster(doublePoints);
            for (Cluster<DoublePoint> cluster : clusters) {
                List<DoublePoint> clusterPoints = cluster.getPoints();
                for (DoublePoint clusterPoint : clusterPoints) {
                    double[] clusterPointValues = clusterPoint.getPoint();
                    double clusterBrokerIndex = clusterPointValues[0];
                    double clusterTime = clusterPointValues[1];
                    for (IOIWorkRequest ioi : iois) {
                        Broker ioiBroker = ioi.getBroker();
                        double ioiBrokerIndex = 2 * brokers.indexOf(ioiBroker);
                        double ioiHour = CommonUtils.getFieldFromDate(ioi.getSubmittedAt(), Calendar.HOUR_OF_DAY);
                        double ioiTime = ioiHour;
                        if (clusterBrokerIndex == ioiBrokerIndex && clusterTime == ioiTime) {
                            ioi.setRiskPercent(100);
                        }
                    }
                }
            }
        }
    }

}
