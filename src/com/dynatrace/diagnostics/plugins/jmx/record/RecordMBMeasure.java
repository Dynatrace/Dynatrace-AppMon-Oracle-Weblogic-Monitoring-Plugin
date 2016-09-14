package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.MBStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordMBMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordMBMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs,ArrayList<MBStats> ad)  throws Exception {
		
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<MBStats> list = ad;
            for (MBStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                String mbName = fromStatic.getmessageBridge();
                Integer state = fromStatic.getstate();
                Integer forward = fromStatic.getforward();
                
                String measureName = WLServerName + "|MessageBridge|" + mbName + "|State|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_MESSAGEBRIDGE_GROUP, WEBLOGIC_STATE_METRIC, measureName, (double)state);
                
                measureName = WLServerName + "|MessageBridge|" + mbName + "|Forwarding|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_MESSAGEBRIDGE_GROUP, WEBLOGIC_FORWARD_METRIC, measureName, (double)forward);
            }
		} catch (Exception e) {
            log.warning("Record RecordMBMeasure data: " + e);
        } 		
	}
}
