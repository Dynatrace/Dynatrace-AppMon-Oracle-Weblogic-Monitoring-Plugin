package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadWorkmanagerStats;

public class RecordThreadWorkMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordThreadWorkMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ThreadWorkmanagerStats> ad)  throws Exception {
		DynamicMeasure testing = new DynamicMeasure();
		try {
        	ArrayList<ThreadWorkmanagerStats> workManager = ad;
            for (ThreadWorkmanagerStats fromStatic : workManager) {
                String ServerName = fromStatic.getserverName();
                String AppName = fromStatic.getApplication();
                String WorkManager = fromStatic.getWorkManager();
                int ThreadCount = fromStatic.getTotalThreads();
                String Enviro = fromStatic.getEnviro();
                
                measureName = ServerName+"|Workmanager|"+ AppName + "|" + WorkManager + "|" +Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_WORKMANAGER_METRIC, measureName, (double)ThreadCount); 
            }
		
		} catch (Exception e) {
            log.warning("Record Workmanager data: " + e);
        } 
	}
}