package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationSStateStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordSingleStateMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordStateMeasure.class.getName());
	String measureName;
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ApplicationSStateStats> ad)  throws Exception {
		DynamicMeasure testing = new DynamicMeasure();
		try {
			ArrayList<ApplicationSStateStats> list = ad;
			for (ApplicationSStateStats fromStatic : list) {
				String app = fromStatic.getappName();
				Integer deploy = fromStatic.getsingleDeploy();
				String Enviro = fromStatic.getEnviro();
				String serverName = fromStatic.getserverName();
          	
				measureName = serverName+"|ApplicationSingleDeploy|" + app + "|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_APPLICATIONSTATE_GROUP, WEBLOGIC_APPLICATIONDEPLOYEDSINGLESTATE_METRIC, measureName, (double)deploy);
			}
		
		} catch (Exception e) {
          log.info("Record Single State: " + e);
		} 
	}
}