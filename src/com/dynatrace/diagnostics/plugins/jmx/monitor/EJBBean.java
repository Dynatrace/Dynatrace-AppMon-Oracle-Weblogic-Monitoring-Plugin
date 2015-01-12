package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBBStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBBTimeHolder;

import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EJBBean implements Callable<ArrayList<EJBBStats>>, WeblogicConstants {

	private ArrayList<EJBBStats> test;
	
	public EJBBean(ArrayList<EJBBStats> test){
        this.test = test;
	}

	
	private static final Logger log = Logger.getLogger(EJBBean.class.getName());
	private Set<ObjectInstance> mbeans;
	private Iterator<ObjectInstance> iter;
	
	EJBBTimeHolder ad = new EJBBTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    String [] monitorEJB;

    public EJBBean (ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment
    		, String[] monitorData, String[] ejbMonitor) {
      	envs = env;
          destRT = destR;
          connections = connection;
          environments=environment;
          dataMonitor=monitorData;
          monitorEJB=ejbMonitor;
      }

      @Override
      public ArrayList<EJBBStats> call() throws Exception {
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
    				  mbeans = connections.queryMBeans(new ObjectName("*:Location=" + serverName + ",Type=EJBPoolRuntime,ApplicationRuntime="+appName+",Name=" + beanName +"*"), null);
    				  iter = mbeans.iterator();
    				  if(iter.hasNext()){
    					  EJBBStats stat = new EJBBStats();
    					  while (iter.hasNext()) {
    					  
    						  try {
    							  String ejb = (iter.next().getObjectName().getCanonicalKeyPropertyListString());
					
    							  String ejbName = (String) connections.getAttribute(new ObjectName("com.bea:" + ejb), "Name").toString();
    							  int biuc = (Integer) connections.getAttribute(new ObjectName("com.bea:" + ejb), "BeansInUseCurrentCount");
    							  int pbcc = (Integer) connections.getAttribute(new ObjectName("com.bea:" + ejb), "PooledBeansCurrentCount");
    							  int wcc = (Integer) connections.getAttribute(new ObjectName("com.bea:" + ejb), "WaiterCurrentCount");
    							  Long ttc = (Long) connections.getAttribute(new ObjectName("com.bea:" + ejb), "TimeoutTotalCount");
					
    							  stat.setserverName(serverName);  
    							  stat.setappName(appName);
    							  stat.setejbName(ejbName);
    							  stat.setEnviro(environments);
    							  stat.setbiuc(biuc);
    							  stat.setpbcc(pbcc);
    							  stat.setwcc(wcc);
    							  stat.setttc(ttc);
    							  ad.addEJBBStats(stat);
    						  }catch (Exception e){
    							  log.warning("EJB Bean error: " + e);
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
    	      		log.warning("Error EJB Bean data: " + e);
    	      	}
    	  }
      test = ad.getArrayList();
		return test;
      }
}
