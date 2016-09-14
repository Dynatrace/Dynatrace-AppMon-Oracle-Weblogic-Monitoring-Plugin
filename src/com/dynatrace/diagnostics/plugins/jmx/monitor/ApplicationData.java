package com.dynatrace.diagnostics.plugins.jmx.monitor;


import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataTimeHolder;
import com.dynatrace.diagnostics.plugins.jmx.regex.PatternMatch;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class ApplicationData implements Callable<ArrayList<ApplicationDataStats>>, WeblogicConstants {

	private ArrayList<ApplicationDataStats> test;
	
    public ApplicationData(ArrayList<ApplicationDataStats> test){
	        this.test = test;
    }

	
	private static final Logger log = Logger.getLogger(ApplicationData.class.getName());
    ApplicationDataTimeHolder ad = new ApplicationDataTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    Boolean regex;
    

    public ApplicationData(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment, 
    		String[] monitorData, Boolean RegexAppData) { 
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        dataMonitor=monitorData;
        regex=RegexAppData;
    }

    @Override
    public ArrayList<ApplicationDataStats> call() throws Exception {

    	PatternMatch pm = new PatternMatch();
    	boolean match=false;
    	
        try {
        	int pc = 1;
        	boolean a;
            String serverName = (String) connections.getAttribute(destRT, "Name");
            ObjectName[] appRT = (ObjectName[]) connections.getAttribute(destRT, "ApplicationRuntimes");
            for (ObjectName app : appRT) {
                String appName = (String) connections.getAttribute(app, "Name");
                pc = 1;
                if(regex){
                	match=pm.match(appName, dataMonitor[0].toString());
                	if(match){
                		pc = 0;
                	}
                }else{
                	int val = dataMonitor.length;
                	int z = 0;
                	while (z < val){
                		String pattern = ".*" + dataMonitor[z].toString() + ".*";
                		a = Pattern.matches(pattern, app.toString());
                		if (a){
                			pc = 0;
                			break;
                		}else {
                			//Do nothing.
                		}
                		z++;
                	}
                }
                if (pc == 0){
                    ObjectName[] componentRT = (ObjectName[]) connections.getAttribute(app, "ComponentRuntimes");
                    for (ObjectName compRT : componentRT) {
                    	
            			String name = (String) connections.getAttribute(compRT, "Name");
            			
            			name = name.replace(".", "-");
                        name = name.replace(" ", "-");
                        name = name.replace("/", "-");
                                    
                        appName = appName.replace(".", "-");
                        appName = appName.replace(" ", "-");
                        appName = appName.replace("/", "-");

                        try{  
                            String OpenSessionsCurrentCount = (String) connections.getAttribute(compRT, "OpenSessionsCurrentCount").toString();
                                       
                            long l = Long.parseLong(OpenSessionsCurrentCount);
                            
                            String OpenSessionsHighCount = (String) connections.getAttribute(compRT, "OpenSessionsHighCount").toString();
                                        
                            long m = Long.parseLong(OpenSessionsHighCount);
                            
                            String SessionsOpenedTotalCount = (String) connections.getAttribute(compRT, "SessionsOpenedTotalCount").toString();
                                        
                            long n = Long.parseLong(SessionsOpenedTotalCount);
                            
                            try {
                            	ApplicationDataStats stats = new ApplicationDataStats();
                            	stats.setserverName(serverName);
                            	stats.setEnviro(environments);
                            	stats.setappName(appName);
                            	stats.setCompRuntime(name);
                            	stats.setOpenSessionsCurrentCount(l);
                            	stats.setOpenSessionsHighCount(m);
                            	stats.setSessionsOpenedTotalCount(n);
                            	
                            	ad.addServerStats(stats);
                            }catch (Exception p){
                            	log.info("Exception Storing Application Data: " + p.toString());
                            }
                        } catch (Exception e) {
                           	continue;
                        }
                    }
                }     
            }
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error Saving Application data: " + e);
        	}
        } 
        test = ad.getArrayList();
    	return test;
    }
}