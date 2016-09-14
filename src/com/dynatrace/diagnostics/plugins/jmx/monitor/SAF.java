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
import com.dynatrace.diagnostics.plugins.jmx.variableholder.SAFStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.SAFTimeHolder;


public class SAF implements Callable<ArrayList<SAFStats>>, WeblogicConstants {

	private ArrayList<SAFStats> test;
	
	public SAF(ArrayList<SAFStats> test){
        this.test = test;
	}	

	
	private static final Logger log = Logger.getLogger(MessageBridge.class.getName());
	private Set<ObjectInstance> mbeans;
	private Iterator<ObjectInstance> iter;
	
	SAFTimeHolder ad = new SAFTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    String [] monitorEJB;	
	
	
    public SAF (ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment, String[] monitorData) {
      	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        dataMonitor=monitorData;
          
      }	
	
	
	@Override
    public ArrayList<SAFStats> call() throws Exception {
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
   		  		mbeans = connections.queryMBeans(new ObjectName("*:Location=" + serverName +",Type=SAFAgentRuntime*"), null);
   		  	} catch (Exception e) {
   		  		log.info("Not able to get mbean data: " + e);
   		  		log.info("Application will continue.");
   		  	}
   		  	iter = mbeans.iterator();
   		  	if(iter.hasNext()){
   		  		while (iter.hasNext()) {
   		  			val=0;
   		  			SAFStats stats = new SAFStats();
   		  			String mbs = (iter.next().getObjectName().getCanonicalKeyPropertyListString());
   		  			String mbsName = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "Name").toString();   
   		  			
   		  			
   		  			for(int i=1; i < dataMonitor.length; i=i+2){
   		  				if(mbsName.equalsIgnoreCase(dataMonitor[i]) && serverName.equalsIgnoreCase(dataMonitor[i-1])){
   		  					val = 1;
   		  					break;
   		  				}
   		  			}
   		  			if(val == 1){
   		  				
   		  				Long FailedMessagesTotal = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "FailedMessagesTotal"); 
   		  				Long MessagesCurrentCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "MessagesCurrentCount");
   		  				Long MessagesPendingCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "MessagesPendingCount"); 
   		  				Long MessagesHighCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "MessagesHighCount");
		  				
		  				String PausedForForwarding = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "PausedForForwarding").toString(); 
   		  				String PausedForIncoming = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "PausedForIncoming").toString();
   		  				String PausedForReceiving = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "PausedForReceiving").toString(); 
		  				
   		  				Long RemoteEndpointsCurrentCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "RemoteEndpointsCurrentCount");
   		  				Long RemoteEndpointsHighCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "RemoteEndpointsHighCount"); 
   		  				Long RemoteEndpointsTotalCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "RemoteEndpointsTotalCount");
   		  				
   		  				Long ConversationsCurrentCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "ConversationsCurrentCount");
		  				Long ConversationsHighCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "ConversationsHighCount"); 
		  				Long ConversationsTotalCount = (Long) connections.getAttribute(new ObjectName("com.bea:" + mbs), "ConversationsTotalCount");
   		  				
   		  				String HealthState = (String) connections.getAttribute(new ObjectName("com.bea:" + mbs), "HealthState").toString();
   		  				
   		  				
   		  				int healthState = 1;
   		  				if (!HealthState.toLowerCase().contains("health_ok")) {
   		  					healthState = 0;
   		  				}
   		  				int pff=1;
   		  				if (!PausedForForwarding.toLowerCase().contains("false")) {
   		  					pff = 0;
		  				}
   		  				int pfi=1;
   		  				if (!PausedForIncoming.toLowerCase().contains("false")) {
   		  					pfi = 0;
		  				}
   		  				int pfr=1;
   		  				if (!PausedForReceiving.toLowerCase().contains("false")) {
   		  					pfr = 0;
		  				}
   		  				stats.setEnviro(environments);
   		  				stats.setserverName(serverName);
   		  				stats.setSAFAgent(mbsName);
   		  				stats.setFailedMessagesTotal(FailedMessagesTotal);
   		  				stats.setMessagesCurrentCount(MessagesCurrentCount);
   		  				stats.setMessagesPendingCount(MessagesPendingCount);
   		  				stats.setMessagesHighCount(MessagesHighCount);
   		  				stats.setPausedForForwarding(pff);
   		  				stats.setPausedForIncoming(pfi);
   		  				stats.setPausedForReceiving(pfr);
   		  				stats.setRemoteEndpointsCurrentCount(RemoteEndpointsCurrentCount);
   		  				stats.setRemoteEndpointsHighCount(RemoteEndpointsHighCount);
   		  				stats.setRemoteEndpointsTotalCount(RemoteEndpointsTotalCount);
   		  				stats.setHealthState(healthState);
   		  				stats.setConversationsCurrentCount(ConversationsCurrentCount);
   		  				stats.setConversationsHighCount(ConversationsHighCount);
   		  				stats.setConversationsTotalCount(ConversationsTotalCount);
   		  				ad.addSAFStats(stats);
   		  			}
   		  		}
   		  	}
   		  }
		 } catch (Exception e) {
 	      	if (log.isLoggable(Level.WARNING)) {
 	      		log.warning("Error SAF data: " + e);
 	      	}
 	  }
		 test = ad.getArrayList();
		 return test;	
    }
}