package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationStateStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordStateMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordStateMeasure.class.getName());
	String measureName;
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ApplicationStateStats> ad)  throws Exception {
		DynamicMeasure testing = new DynamicMeasure();
		try {
			
			ArrayList<ApplicationStateStats> list = ad;
            for (ApplicationStateStats fromStatic : list) {
            	String app = fromStatic.getappName();
            	String Comp = fromStatic.getCompName();
            	String Enviro = fromStatic.getEnviro();
            	String serverName = fromStatic.getserverName();
            	Long health = fromStatic.getappHealth();
            	Integer state = fromStatic.getappState();
            	
            	measureName = serverName+"|ApplicationState|" + app +"|HealthState|"+Enviro;
    			testing.populateDynamicMeasure(envs, WEBLOGIC_APPLICATIONSTATE_GROUP, WEBLOGIC_APPLICATIONHEALTH_METRIC, measureName, (double)health);
        	
            	measureName = serverName+"|ApplicationState|" + app + "|" + Comp + "|RunState|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_APPLICATIONSTATE_GROUP, WEBLOGIC_APPLICATIONRUNSTATE_METRIC, measureName, (double)state);    
            	
            }
		
        } catch (Exception e) {
            log.warning("Record State data: " + e);
        } 
	}
}
