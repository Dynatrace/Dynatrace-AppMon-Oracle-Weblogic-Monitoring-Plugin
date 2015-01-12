package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadTimeHolder;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.logging.Logger;
import java.util.concurrent.Callable;

public class Threads implements Callable<ArrayList<ThreadStats>>, WeblogicConstants {
	
	private ArrayList<ThreadStats> test;
	
	public Threads(ArrayList<ThreadStats> test){
        this.test = test;
	}	
	
	private static final Logger log = Logger.getLogger(Threads.class.getName());
	private ArrayList<ThreadStats> statsList = new ArrayList<ThreadStats>();
    
    ThreadTimeHolder ad = new ThreadTimeHolder();
    
    
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    

    public Threads(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
        envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<ThreadStats> call() throws Exception {
        try {
        	ThreadStats stats = new ThreadStats();
            
            String name = (String) connections.getAttribute(destRT, "Name");
            stats.setserverName(name);
            
            stats.setEnviro(environments);
            
            ObjectName threadRT = (ObjectName) connections.getAttribute(destRT, "ThreadPoolRuntime");
            Integer ExecuteThreadTotalCounts = (Integer) connections.getAttribute(threadRT, "ExecuteThreadTotalCount");
            
            stats.setExecuteThreadTotalCount(ExecuteThreadTotalCounts);
            
            Integer ExecuteThreadIdleCounts = (Integer) connections.getAttribute(threadRT, "ExecuteThreadIdleCount");
            
            stats.setExecuteThreadIdleCount(ExecuteThreadIdleCounts);
            
            Integer HoggingThreadCounts = (Integer) connections.getAttribute(threadRT, "HoggingThreadCount");
            
            stats.setHoggingThreadCount(HoggingThreadCounts);
            
            Integer QueueLengths = (Integer) connections.getAttribute(threadRT, "QueueLength");
            
            stats.setQueueLength(QueueLengths);
            
            Integer StandbyThreadCounts = (Integer) connections.getAttribute(threadRT, "StandbyThreadCount");
            
            stats.setStandbyThreadCount(StandbyThreadCounts);
            
            String health = (String) connections.getAttribute(threadRT, "HealthState").toString();
            int healthState = 0;
            if (!health.toLowerCase().contains("health_ok")) {
            	healthState = 1;
            }
            
            stats.sethealth(healthState);
            
            Long CompletedRequestCount = (Long) connections.getAttribute(threadRT, "CompletedRequestCount");
            
            stats.setCompletedRequestCount(CompletedRequestCount);
            
            Double PresentThroughput = (Double) connections.getAttribute(threadRT, "Throughput");
            
            stats.setPresentThroughput(PresentThroughput);
            
            statsList.add(stats);
            ad.addServerStatsList(statsList);
            statsList.clear();
        } catch (Exception e) {
        	log.warning("Threads Exception: " + e);
        }
        test = ad.getArrayList();
		return test;
    }
}
