package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationStateStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationStateTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;

import weblogic.management.runtime.*;

import java.util.concurrent.Callable;


public class ApplicationState implements Callable<ArrayList<ApplicationStateStats>>, WeblogicConstants {
	
	private ArrayList<ApplicationStateStats> test;
	
	public ApplicationState(ArrayList<ApplicationStateStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(ApplicationState.class.getName());
	ApplicationStateTimeHolder ad = new ApplicationStateTimeHolder();
	
    private String serverName;
    MBeanServerConnection connections;
    ObjectName serverRT;
    MonitorEnvironment envs;
    String environments;
    
    String[] ignoreState;
    private ArrayList<Object> appNames = new ArrayList<Object>();
    
    public ApplicationState(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment,
    		String[] deployedSingle, String[] stateIgnore) {
    	envs = env;
        serverRT = destR;
        connections = connection;
        environments=environment;
        ignoreState=stateIgnore;
        
    }

    @Override
    public ArrayList<ApplicationStateStats> call() throws Exception {
        try {
            serverName = (String) connections.getAttribute(serverRT, "Name");
            if (log.isLoggable(Level.INFO)) {
            	log.info("Application State: " + serverName);
        	}

            int pc = 1;
            boolean a;
            ObjectName[] appRT = (ObjectName[]) connections.getAttribute(serverRT, "ApplicationRuntimes");
            for (ObjectName app : appRT) {
            	pc = 1;
                //So we don't have to monitor all applications. Lots of data otherwise.
                int val = ignoreState.length;
                int z = 0;            	
            	while (z < val){
            		String pattern = ".*" + ignoreState[z].toString() + ".*";
                    a = Pattern.matches(pattern, app.toString());
            		if (a){
            			pc = 0;
            			break;
            		}else {
            			//Do nothing.
            		}
            		z++;
            	}
            	
            	if (pc == 0){
            		
            		appNames.add(app.toString());
            		//To accommodate 10.1 version since it does not have a health state value here
            		String runningState;
            		try {
            			runningState = connections.getAttribute(app, "HealthState").toString();
            		} catch (Exception e) {
            			runningState = ".*HEALTH_OK.*";
            			log.info("Application State - Setting health to OK state. Weblogic 10.1 does not have this value so we set it manually.");
            		}

            		String healthPattern = ".*HEALTH_OK.*";
            		boolean c = Pattern.matches(healthPattern, runningState);
                    long l = 0;
                    
            		if (!c) {
            			l = 1;
            		} else {
            			l = 0;
            		}
                
            		ObjectName[] componentRT = (ObjectName[]) connections.getAttribute(app, "ComponentRuntimes");
                
            		for (ObjectName compRT : componentRT) {
            			ApplicationStateStats stats = new ApplicationStateStats();
            			stats.setserverName(serverName);
                		stats.setappName(app.toString());
                        stats.setEnviro(environments);
                        stats.setappHealth(l);
                        
            			Integer stateInt = (Integer) connections.getAttribute(compRT, "DeploymentState");
            			String name = (String) connections.getAttribute(compRT, "Name");
            			
            			stats.setCompName(name);
            			
            			String stateString = "" + stateInt;
            			if (stateInt == ComponentRuntimeMBean.ACTIVATED) {
            				stateString = "ACTIVATED";
            				stats.setappState(stateInt);
            			}
            			if (stateInt == ComponentRuntimeMBean.NEW) {
            				stateString = "NEW";
            				stats.setappState(stateInt);
            			}
            			if (stateInt == ComponentRuntimeMBean.PREPARED) {
            				stateString = "PREPARED";
            				stats.setappState(stateInt);
            			}
            			if (stateInt == ComponentRuntimeMBean.UNPREPARED) {
            				stateString = "UNPREPARED";
            				stats.setappState(stateInt);
            			}
            			if (!stateString.equalsIgnoreCase("ACTIVATED") && !stateString.equalsIgnoreCase("NEW") && !stateString.equalsIgnoreCase("PREPARED") && !stateString.equalsIgnoreCase("UNPREPARED")) {
            				stateString = "Failed";
            				stats.setappState(stateInt);
            			}
            			ad.addServerStats(stats);
            		}
            	}
            }
        
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error recording Application State: " + e);
        	}
        } 
        test = ad.getArrayList();
		return test;
    }
}

