package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ClusterStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ClusterTimeHolder;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;

public class ClusterCommunication implements Callable<ArrayList<ClusterStats>>, WeblogicConstants {

	private ArrayList<ClusterStats> test;
	
	public ClusterCommunication(ArrayList<ClusterStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(ClusterCommunication.class.getName());
    
	ClusterTimeHolder ad = new ClusterTimeHolder();
	
    private String serverNames = "";
    private int testing = 0;
    
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String adminServer;
    
    public ClusterCommunication(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment
    		, String adminServerName) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        adminServer=adminServerName;
    }

    @Override
    public ArrayList<ClusterStats> call() throws Exception {
        
        try {
        	ClusterStats stats = new ClusterStats();
            serverNames = (String) connections.getAttribute(destRT, "Name");
            
            if (serverNames.equalsIgnoreCase(adminServer)) {
                throw new Exception("This will be caught below");
            }
            stats.setserverName(serverNames);
            stats.setEnviro(environments);
            
            ObjectName clusterRuntime = (ObjectName) connections.getAttribute(destRT, "ClusterRuntime");
            String clusterName = (String) connections.getAttribute(clusterRuntime, "Name").toString();
            
            stats.setclusterName(clusterName);
            
            Long MultiLostCount = (Long) connections.getAttribute(clusterRuntime, "MulticastMessagesLostCount");
            
            stats.setMultiLostCount(MultiLostCount);
            
            int SerAliveC = (Integer) connections.getAttribute(clusterRuntime, "AliveServerCount");
            
            stats.setServerAliveCount(SerAliveC);
            ad.addServerStats(stats);
        } catch (Exception e) {
            
            if (serverNames.equalsIgnoreCase(adminServer)) {
                testing = 1;
            }
           
            if (testing != 1) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error Cluster Communication " + serverNames + ": " + e);
            	}
            }
            testing = 0;
        }
        test = ad.getArrayList();
		return test;
    }
}
