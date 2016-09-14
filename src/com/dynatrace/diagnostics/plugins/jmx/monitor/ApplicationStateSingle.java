package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationSStateStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationSStateTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.concurrent.Callable;


public class ApplicationStateSingle implements Callable<ArrayList<ApplicationSStateStats>>, WeblogicConstants {
	
	private ArrayList<ApplicationSStateStats> test;
	
	public ApplicationStateSingle(ArrayList<ApplicationSStateStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(ApplicationSStateStats.class.getName());
	ApplicationSStateTimeHolder as = new ApplicationSStateTimeHolder();
	
    private String serverName;
    MBeanServerConnection connections;
    ObjectName serverRT;
    MonitorEnvironment envs;
    String environments;
    
    String[] deploySingle;
    String[] ignoreState;
    private ArrayList<Object> appNames = new ArrayList<Object>();
    
    public ApplicationStateSingle(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment,
    		String[] deployedSingle, String[] stateIgnore) {
    	envs = env;
        serverRT = destR;
        connections = connection;
        environments=environment;
        deploySingle=deployedSingle;
        ignoreState=stateIgnore;
        
    }

    @Override
    public ArrayList<ApplicationSStateStats> call() throws Exception {
        try {
            serverName = (String) connections.getAttribute(serverRT, "Name");
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
            	}
            	
            }
            	//Validate The applications we want to monitor are in the list - If the deployment fails, it will not be in the list.
           		int numUse = deploySingle.length;
           		int p = 0;
        
           		String deployment;
           		ApplicationSStateStats stat = new ApplicationSStateStats();
           		
           		while (p < numUse) {
           			if (serverName.equalsIgnoreCase(deploySingle[p].toString())) {
           				String pattern = ".*" + deploySingle[p + 1].toString() + ".*";
           				if (log.isLoggable(Level.INFO)) {
           					log.info("Application Single State - Pattern we are looking for: " + pattern);
           				}
           				boolean b = true;
           				deployment=deploySingle[p + 1].toString();
           				for (Object run : appNames) {
           					b = Pattern.matches(pattern, run.toString());
           					if (b) {
            					if (log.isLoggable(Level.INFO)) {
            						log.info("b true");
            					}
            					
            					break;
           					} else if (!b) {
           						if (log.isLoggable(Level.INFO)) {
           							log.info("b false");
           						}
           					}
           					if (log.isLoggable(Level.INFO)) {
           						log.info("Application Single State Name: " + run.toString());
           					}
                   if (log.isLoggable(Level.INFO)) {
                    	log.info("clusterTest = 0");
                    }
                }
           				
               if (!b) {
                    try {
                    	//App is not deployed...
                    	stat.setserverName(serverName);
                    	stat.setEnviro(environments);
                    	stat.setappName(deployment);
                    	stat.setsingleDeploy(1);
                    	as.addServerSStats(stat);
                    } catch (Exception e) {
                   		log.warning("Error Setting Application State Alert: " + e);
                    }
                } else if(b){
                	stat.setserverName(serverName);
                	stat.setEnviro(environments);
                	stat.setappName(deployment);
                	//Application is deployed.
                	stat.setsingleDeploy(0);
                	as.addServerSStats(stat);
                }
            }
            p += 2;
        }
        } catch (Exception e) {
        	log.warning("Application Single State error: " + e);
        }         
        test = as.getArrayList();
		return test;
    }
}