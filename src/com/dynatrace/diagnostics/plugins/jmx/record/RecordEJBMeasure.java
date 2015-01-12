package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordEJBMeasure implements WeblogicConstants {
	private static final Logger log = Logger.getLogger(RecordEJBMeasure.class.getName());
	String measureName;
	
public void WriteToDT (MonitorEnvironment envs, ArrayList<EJBStats> ad)  throws Exception {
	DynamicMeasure testing = new DynamicMeasure();
		try {
			
			ArrayList<EJBStats> list = ad;
			for (EJBStats fromStatic : list) {
				
				String serverName = fromStatic.getserverName();
				String appName = fromStatic.getappName();
				String ejbName = fromStatic.getejbName();
				String Enviro = fromStatic.getEnviro();
				Long tctc = fromStatic.gettctc();
				Long trbc = fromStatic.gettrbc();
				Long ttoc = fromStatic.getttoc();
				
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|TransactionsCommittedTotalCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_TRANSACTIONSCOMMITTEDTOTAL_METRIC, measureName, (double)tctc);
    
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|TransactionsRolledBackTotalCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_TRANSACTIONSROLLEDBACKTOTAL_METRIC, measureName, (double)trbc);
    
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|TransactionsTimedOutTotalCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_TRANSACTIONSTIMEOUTTOTAL_METRIC, measureName, (double)ttoc);
				
			}
			
		} catch (Exception e) {
		    log.warning("Record EJB Data: " + e);
		} 
	}		
}
