package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerTimeHolder;

import java.util.ArrayList;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.dynatrace.diagnostics.plugins.jmx.convertdata.DateTime;

import java.util.concurrent.Callable;


public class WLServer implements Callable<ArrayList<WLServerStats>>, WeblogicConstants {

	
	private ArrayList<WLServerStats> test;
	
	public WLServer(ArrayList<WLServerStats> test){
        this.test = test;
	}

	
	private static final Logger log = Logger.getLogger(WLServer.class.getName());
	private ArrayList<WLServerStats> statsList = new ArrayList<WLServerStats>();
    
	
	WLServerTimeHolder ad = new WLServerTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;

    public WLServer(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
    	envs = env;
    	destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<WLServerStats> call() throws Exception {
        try {
        	WLServerStats stats = new WLServerStats();
        	
            long time = DateTime.getDateTime();
            
            stats.setEnviro(environments);
            
            String name = (String) connections.getAttribute(destRT, "Name");
            
            stats.setserverName(name);
            
            Integer OpenSocket = (Integer) connections.getAttribute(destRT, "OpenSocketsCurrentCount");
            
            stats.setOpenSocketsCurrentCount(OpenSocket);
            
            String health = (String) connections.getAttribute(destRT, "HealthState").toString();
            
            int healthState = 0;
            if (!health.toLowerCase().contains("health_ok")) {
            	healthState = 1;
            }
            
            stats.sethealthState(healthState);
            
            String ListenAddress = (String) connections.getAttribute(destRT, "ListenAddress").toString();
            
            int ListenAdd= 0;
            if (!ListenAddress.contains("/")) {
            	ListenAdd=1;
            }
            
            stats.setListenAddress(ListenAdd);
            Long ActivationTime = (Long) connections.getAttribute(destRT, "ActivationTime");
            
            Long totalTime = (((time - ActivationTime) / 1000) / 60); //put into minutes
            
            stats.setActivationTime(totalTime);
            String serverState = (String) connections.getAttribute(destRT, "State");
            
            int State = 0;
            if (!serverState.toLowerCase().contains("running")) {
            	State = 1;
            }
            stats.setserverState(State);
            statsList.add(stats);
            ad.addServerStatsList(statsList);
            
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("WLServer error: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}
