package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.MBStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.MBTimeHolder;


public class MessageBridge implements Callable<ArrayList<MBStats>>, WeblogicConstants {

	private ArrayList<MBStats> test;
	
	public MessageBridge(ArrayList<MBStats> test){
        this.test = test;
	}	

	
	private static final Logger log = Logger.getLogger(MessageBridge.class.getName());
	private Set<ObjectInstance> mbeans;
	private Iterator<ObjectInstance> iter;
	
	MBTimeHolder ad = new MBTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    String [] monitorEJB;	
	
	
    public MessageBridge (ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment, String[] monitorData) {
      	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        dataMonitor=monitorData;
          
      }	
	
	
	@Override
    public ArrayList<MBStats> call() throws Exception {
    	int val = 0;
		 try {
   		  	String serverName = (String) connections.getAttribute(destRT, "Name");
   		  	for(int i=0; i < dataMonitor.length; i=i+2){
   		  		if(serverName.equalsIgnoreCase(dataMonitor[i])){
   		  			val = 1;
   		  			break;
   		  		}
   		  	}
 
   		  if(val == 1){
   		  	try {
   		  		mbeans = connections.queryMBeans(new ObjectName("*:Location=" + serverName +",Type=MessagingBridgeRuntime*"), null);
   		  	} catch (Exception e) {
   		  		log.info("Not able to get mbean data: " + e);
   		  		log.info("Application will continue.");
   		  	}
   		  	iter = mbeans.iterator();
   		  	if(iter.hasNext()){
   		  		while (iter.hasNext()) {
   		  			val=0;
   		  			MBStats stats = new MBStats();
   		  			String mbs = (iter.next().getObjectName().getCanonicalKeyPropertyListString());
   		  			String mbsName = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "Name").toString();   
   		  			String mbsState = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "State").toString(); 
   		  			String mbsForward = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "Description").toString();
   		  			
   		  			for(int i=1; i < dataMonitor.length; i=i+2){
   		  				if(mbsName.equalsIgnoreCase(dataMonitor[i]) && serverName.equalsIgnoreCase(dataMonitor[i-1])){
   		  					val = 1;
   		  					break;
   		  				}
   		  			}
   		  			if(val == 1){
   		  				stats.setEnviro(environments);
   		  				stats.setserverName(serverName);
   		  				stats.setmessageBridge(mbsName);
   		  				if(mbsState.equalsIgnoreCase("Active")){
   		  					int state = 1;
   		  					stats.setstate(state);
   		  				}else{
   		  					log.info("State is not Active: "+ serverName + "|" + mbsName + "|" + environments);
   		  					int state = 0;
   		  					stats.setstate(state);
   		  				}
   		  				if(mbsForward.contains("Forwarding messages")){
   		  					int forward = 1;
   		  					stats.setforward(forward);
   		  				}else{
   		  					log.info("Message Bridge is not forwarding: "+ serverName + "|" + mbsName + "|" + environments);
   		  					int forward = 0;
   		  					stats.setforward(forward);
   		  				}
   		  				ad.addMBStats(stats);
   		  			}
   		  		}
   		  	}
   		  }
		 } catch (Exception e) {
 	      	if (log.isLoggable(Level.WARNING)) {
 	      		log.warning("Error MessageBridge data: " + e);
 	      	}
 	  }
		 test = ad.getArrayList();
		 return test;	
    }
}
