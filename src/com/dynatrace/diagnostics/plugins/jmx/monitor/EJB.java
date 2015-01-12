package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBTimeHolder;

import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EJB implements Callable<ArrayList<EJBStats>>, WeblogicConstants {

	private ArrayList<EJBStats> test;
	
	public EJB(ArrayList<EJBStats> test){
        this.test = test;
	}

	
	private static final Logger log = Logger.getLogger(EJB.class.getName());
	private Set<ObjectInstance> mbeans;
	private Iterator<ObjectInstance> iter;
	
	EJBTimeHolder ad = new EJBTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    String [] monitorEJB;

    public EJB (ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment
    		, String[] monitorData, String[] ejbMonitor) {
      	envs = env;
          destRT = destR;
          connections = connection;
          environments=environment;
          dataMonitor=monitorData;
          monitorEJB=ejbMonitor;
      }

      @Override
      public ArrayList<EJBStats> call() throws Exception {
    	  
      try {
    		  String serverName = (String) connections.getAttribute(destRT, "Name");
    		  int c = 0;
    		  int d = dataMonitor.length;
    		  int i = monitorEJB.length;
    		  int r = 0;
    		  
    		  while (r < i){
    			  String beanName = monitorEJB[r].toString();
    			  while (c < d ) {
    				  String appName = dataMonitor[c].toString();
    				  mbeans = connections.queryMBeans(new ObjectName("*:Location=" + serverName + ",Type=EJBTransactionRuntime,ApplicationRuntime="+appName+",Name=" + beanName +"*"), null);
    				  iter = mbeans.iterator();
    				  if(iter.hasNext()){
    					  EJBStats stats = new EJBStats();
    					  while (iter.hasNext()) {
    						  try {
    							  String ejb = (iter.next().getObjectName().getCanonicalKeyPropertyListString());
    							  String ejbName = (String) connections.getAttribute(new ObjectName("com.bea:" + ejb), "Name").toString();
    							  Long tctc = (Long) connections.getAttribute(new ObjectName("com.bea:" + ejb), "TransactionsCommittedTotalCount");
    							  Long trbc = (Long) connections.getAttribute(new ObjectName("com.bea:" + ejb), "TransactionsRolledBackTotalCount");
    							  Long ttoc = (Long) connections.getAttribute(new ObjectName("com.bea:" + ejb), "TransactionsTimedOutTotalCount");
				
    							  stats.setserverName(serverName);
    							  stats.setappName(appName);
    							  stats.setejbName(ejbName);
    							  stats.settctc(tctc);
    							  stats.settrbc(trbc);
    							  stats.setttoc(ttoc);
				  
    							  ad.addEJBStats(stats);
    						  }catch (Exception e){
    							  //Should set all to 0????
    							  log.warning("EJB error: " + e);
    						  }
    					  }
    				  }
    			  c++;
    			  }
    		  r++;
    		  c=0;
    		  }
    		  
    	  } catch (Exception e) {
    	      	if (log.isLoggable(Level.WARNING)) {
    	      		log.warning("Error EJB data: " + e);
    	      	}
    	  }
      test = ad.getArrayList();
		return test;
      }
}
