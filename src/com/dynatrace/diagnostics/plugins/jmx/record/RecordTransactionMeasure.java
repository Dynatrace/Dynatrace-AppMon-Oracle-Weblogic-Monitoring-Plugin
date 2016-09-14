package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

public class RecordTransactionMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordTransactionMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<TransactionStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
            ArrayList<TransactionStats> list = ad;
            for (TransactionStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                Long ActiveTransactionsTotalCount = fromStatic.getActiveTransactionsTotalCount();
                Long TransactionAbandonedTotalCount = fromStatic.getTransactionAbandonedTotalCount();
                Long TransactionCommittedTotalCount = fromStatic.getTransactionCommittedTotalCount();
                Long TransactionHeuristicsTotalCount = fromStatic.getTransactionHeuristicsTotalCount();
                Long TransactionRolledBackAppTotalCount = fromStatic.getTransactionRolledBackAppTotalCount();
                Long TransactionRolledBackResourceTotalCount = fromStatic.getTransactionRolledBackResourceTotalCount();
                Long TransactionRolledBackSystemTotalCount = fromStatic.getTransactionRolledBackSystemTotalCount();
                Long TransactionRolledBackTimeoutTotalCount = fromStatic.getTransactionRolledBackTimeoutTotalCount();
                Long TransactionRolledBackTotalCount = fromStatic.getTransactionRolledBackTotalCount();
                Long TransactionTotalCount = fromStatic.getTransactionTotalCount();		
		
                String measureName = WLServerName+"|Transaction|ActiveTransactionsTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_ACTIVETRANSACTION_METRIC, measureName, (double)ActiveTransactionsTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionAbandonedTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONABANDONED_METRIC, measureName, (double)TransactionAbandonedTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionCommittedTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONCOMMITTED_METRIC, measureName, (double)TransactionCommittedTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionHeuristicsTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONHEURISTICS_METRIC, measureName, (double)TransactionHeuristicsTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionRolledBackAppTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONROLLEDBACKAPP_METRIC, measureName, (double)TransactionRolledBackAppTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionRolledBackResourceTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONROLLEDBACKRESOURCE_METRIC, measureName, (double)TransactionRolledBackResourceTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionRolledBackSystemTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONTROLLEDBACKSYSTEM_METRIC, measureName, (double)TransactionRolledBackSystemTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionRolledBackTimeoutTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONTROLLEDBACKTIMEOUT_METRIC, measureName, (double)TransactionRolledBackTimeoutTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionRolledBackTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONROLLEDBACK_METRIC, measureName, (double)TransactionRolledBackTotalCount);
                
                measureName = WLServerName+"|Transaction|TransactionTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_TRANSACTION_GROUP, WEBLOGIC_TRANSACTIONTOTAL_METRIC, measureName, (double)TransactionTotalCount);
                
            }
        } catch (Exception e) {
            log.warning("Record Transaction data: " + e);
        } 
	}
}
