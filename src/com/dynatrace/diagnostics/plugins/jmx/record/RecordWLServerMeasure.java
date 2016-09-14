package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import java.util.ArrayList;

public class RecordWLServerMeasure implements WeblogicConstants {
	
	private static final Logger log = Logger.getLogger(RecordWLServerMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<WLServerStats> ad)  throws Exception {
		try {
			DynamicMeasure testing = new DynamicMeasure();
			ArrayList<WLServerStats> list = ad;
            for (WLServerStats fromStatic : list) {
            	String Enviro = fromStatic.getEnviro();
                String WLServerName = fromStatic.getserverName();
                Integer openSockets = fromStatic.getOpenSocketsCurrentCount();
                Integer health = fromStatic.gethealthState();
                Integer listenAdd = fromStatic.getListenAddress();
                Long activation = fromStatic.getActivationTime();
                Integer serverState = fromStatic.getserverState();

                String measureName = WLServerName+"|OpenSocket|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SERVER_GROUP, WEBLOGIC_OPENSOCKETSERVER_METRIC, measureName, (double)openSockets);
                
                measureName = WLServerName+"|ServerHealth|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SERVER_GROUP, WEBLOGIC_HEALTHSERVER_METRIC, measureName, (double)health);
                
                measureName = WLServerName+"|ListenAddress|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SERVER_GROUP, WEBLOGIC_LISTENADDRESS_METRIC, measureName, (double)listenAdd);
                
                measureName = WLServerName+"|RunningTime|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SERVER_GROUP, WEBLOGIC_RUNNING_METRIC, measureName, (double)activation);
                
                measureName = WLServerName+"|ServerRunningState|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SERVER_GROUP, WEBLOGIC_SERVERRUNNINGSTATE_METRIC, measureName, (double)serverState);
                
            }
		} catch (Exception e) {
            log.warning("Record WLServer data: " + e);
        } 
	}
}
