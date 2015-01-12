package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ClusterStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordClusterMeasure implements WeblogicConstants {
	
	private static final Logger log = Logger.getLogger(RecordClusterMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ClusterStats> ad)  throws Exception {
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<ClusterStats> list = ad;
            for (ClusterStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                Long MultiLostCount = fromStatic.getMultiLostCount();
                Integer ServerAliveCount = fromStatic.getServerAliveCount();
                String Enviro = fromStatic.getEnviro();	
                String clusterName = fromStatic.getclusterName();
                
                measureName = WLServerName+"|Cluster|" + clusterName + "|MulticastMessagesLost|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_CLUSTER_GROUP, WEBLOGIC_MULTICASTMESSAGESLOST_METRIC, measureName, (double)MultiLostCount);
                
                measureName = WLServerName+"|Cluster|" + clusterName + "|AliveServerCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_CLUSTER_GROUP, WEBLOGIC_ALIVESERVERCOUNT_METRIC, measureName, (double)ServerAliveCount);
			
            }
		} catch (Exception e) {
            log.warning("Record Cluster Data: " + e);
        } 		
	}
}
