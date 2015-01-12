package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import java.util.ArrayList;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionTimeHolder;

import java.util.concurrent.Callable;

public class Transaction implements Callable<ArrayList<TransactionStats>>, WeblogicConstants {

	private ArrayList<TransactionStats> test;
	
	public Transaction(ArrayList<TransactionStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(Transaction.class.getName());
	private ArrayList<TransactionStats> statsList = new ArrayList<TransactionStats>();
	
	TransactionTimeHolder ad = new TransactionTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    
    public Transaction(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<TransactionStats> call() throws Exception {
        try {
        	TransactionStats stats = new TransactionStats();
            String name = (String) connections.getAttribute(destRT, "Name");
            stats.setserverName(name);
            
            stats.setEnviro(environments);
            
            ObjectName threadRT = (ObjectName) connections.getAttribute(destRT, "JTARuntime");
            
            String ActiveTransactionsTotalCount = connections.getAttribute(threadRT, "ActiveTransactionsTotalCount").toString();
            long l = Long.parseLong(ActiveTransactionsTotalCount);
            
            stats.setActiveTransactionsTotalCount(l);
            
            String TransactionAbandonedTotalCount = connections.getAttribute(threadRT, "TransactionAbandonedTotalCount").toString();
            
            l = Long.parseLong(TransactionAbandonedTotalCount);
            
            stats.setTransactionAbandonedTotalCount(l);
            
            String TransactionCommittedTotalCount = connections.getAttribute(threadRT, "TransactionCommittedTotalCount").toString();
            
            l = Long.parseLong(TransactionCommittedTotalCount);
            
            stats.setTransactionCommittedTotalCount(l);
            
            String TransactionHeuristicsTotalCount = connections.getAttribute(threadRT, "TransactionHeuristicsTotalCount").toString();
            
            l = Long.parseLong(TransactionHeuristicsTotalCount);
            
            stats.setTransactionHeuristicsTotalCount(l);
            
            String TransactionRolledBackAppTotalCount = connections.getAttribute(threadRT, "TransactionRolledBackAppTotalCount").toString();
             
            l = Long.parseLong(TransactionRolledBackAppTotalCount);
            
            stats.setTransactionRolledBackAppTotalCount(l);
            
            String TransactionRolledBackResourceTotalCount = connections.getAttribute(threadRT, "TransactionRolledBackResourceTotalCount").toString();
            
            l = Long.parseLong(TransactionRolledBackResourceTotalCount);
            
            stats.setTransactionRolledBackResourceTotalCount(l);
            
            String TransactionRolledBackSystemTotalCount = connections.getAttribute(threadRT, "TransactionRolledBackSystemTotalCount").toString();
            
            l = Long.parseLong(TransactionRolledBackSystemTotalCount);
            
            stats.setTransactionRolledBackSystemTotalCount(l);
            
            String TransactionRolledBackTimeoutTotalCount = connections.getAttribute(threadRT, "TransactionRolledBackTimeoutTotalCount").toString();
            
            l = Long.parseLong(TransactionRolledBackTimeoutTotalCount);
            
            stats.setTransactionRolledBackTimeoutTotalCount(l);
            
            String TransactionRolledBackTotalCount = connections.getAttribute(threadRT, "TransactionRolledBackTotalCount").toString();
            
            l = Long.parseLong(TransactionRolledBackTotalCount);
            
            stats.setTransactionRolledBackTotalCount(l);
            
            String TransactionTotalCount = connections.getAttribute(threadRT, "TransactionTotalCount").toString();
            
            l = Long.parseLong(TransactionTotalCount);
            
            stats.setTransactionTotalCount(l);
            
            statsList.add(stats);
            ad.addServerStatsList(statsList);
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Transaction error: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}
