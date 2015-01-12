package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordApplicationDataMeasure implements WeblogicConstants{
	private static final Logger log = Logger.getLogger(RecordClusterMeasure.class.getName());
	String measureName;
	MonitorEnvironment env;
	

	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ApplicationDataStats> ad)  throws Exception {
		env=envs;
		try {
			DynamicMeasure testing = new DynamicMeasure();
			
            for (ApplicationDataStats fromStatic : ad) {
                String WLServerName = fromStatic.getserverName();
                String appName = fromStatic.getappName();
                String Enviro = fromStatic.getEnviro();
                String name = fromStatic.getCompRuntime();
                Long OpenSessionsCurrentCount = fromStatic.getOpenSessionsCurrentCount();
                Long OpenSessionsHighCount = fromStatic.getOpenSessionsHighCount();
                Long SessionsOpenedTotalCount = fromStatic.getSessionsOpenedTotalCount();
		
                measureName = WLServerName+"|ApplicationData|" + appName + "|" + name + "|OpenSessionsCurrentCount|"+Enviro;
                testing.populateDynamicMeasure(env, WEBLOGIC_APPLICATIONDATA_GROUP, WEBLOGIC_OPENSESSIONSCURRENT_METRIC, measureName, (double)OpenSessionsCurrentCount);
                
                measureName = WLServerName+"|ApplicationData|" + appName + "|" + name + "|OpenSessionsHighCount|"+Enviro;
                testing.populateDynamicMeasure(env, WEBLOGIC_APPLICATIONDATA_GROUP, WEBLOGIC_OPENSESSIONSHIGH_METRIC, measureName, (double)OpenSessionsHighCount);
                
                measureName = WLServerName+"|ApplicationData|" + appName + "|" + name + "|SessionsOpenedTotalCount|"+Enviro;
                testing.populateDynamicMeasure(env, WEBLOGIC_APPLICATIONDATA_GROUP, WEBLOGIC_SESSIONSOPENEDTOTAL_METRIC, measureName, (double)SessionsOpenedTotalCount);
            }
            
		} catch (Exception e) {
            log.warning("Record Application Data: " + e);
        }
	}
}
