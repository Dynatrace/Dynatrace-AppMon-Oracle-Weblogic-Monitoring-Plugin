package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.text.DecimalFormat;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMTimeHolder;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;

public class JVM implements Callable<ArrayList<JVMServerStats>>, WeblogicConstants {

	private ArrayList<JVMServerStats> test;
	
	public JVM(ArrayList<JVMServerStats> test){
        this.test = test;
	}	
	
	private static final Logger log = Logger.getLogger(JVM.class.getName());
	private ArrayList<JVMServerStats> statsList = new ArrayList<JVMServerStats>();
	JVMTimeHolder ad = new JVMTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;

    static public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

    public JVM(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<JVMServerStats> call() throws Exception {

        try {
        	JVMServerStats stats = new JVMServerStats();
            String name = (String) connections.getAttribute(destRT, "Name");
            stats.setServerName(name);
            stats.setEnviro(environments);
            
            ObjectName jvmRT = (ObjectName) connections.getAttribute(destRT, "JVMRuntime");

            String HeapTotalCurrent = "0";
            try {
                HeapTotalCurrent = connections.getAttribute(jvmRT, "HeapSizeCurrent").toString();
                long l = Long.parseLong(HeapTotalCurrent);
                
                stats.setHeapTotalCurrent(l);
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error getting Heap Information (Setting Heap Information to 0): " + e);
            	}
                    long l = Long.parseLong(HeapTotalCurrent);
                    stats.setHeapTotalCurrent(l);
            }
            String HFCurrent = "0";
            try {
                HFCurrent = connections.getAttribute(jvmRT, "HeapFreeCurrent").toString();
                
                long l = Long.parseLong(HFCurrent);
                
                stats.setHFCurrent(l);
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error getting Heap Information (Setting Heap Information to 0): " + e);
            	}
            	
            	long l = Long.parseLong(HFCurrent);
            	stats.setHFCurrent(l);
            }
            String HUCurrent = "0";
            try {
                long heapUsed = Long.parseLong(HeapTotalCurrent) - Long.parseLong(HFCurrent);
                stats.setHUCurrent(heapUsed);
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error getting Heap Information (Setting Heap Information to 0): " + e);
            	}
            	
            	long l = Long.parseLong(HUCurrent);
            	stats.setHUCurrent(l);
            }
            
            Double AllProcessorsAverageLoad = 0.0;
            Long TotalGarbageCollectionCount = 0L;
            Long TotalGarbageCollectionTime = 0L;
            Double JVMProcessorLoad = 0.0;
            Integer NumberOfProcessors = 0;
            
            try {
                NumberOfProcessors = (Integer) connections.getAttribute(jvmRT, "NumberOfProcessors");
                
                AllProcessorsAverageLoad = (Double) connections.getAttribute(jvmRT, "AllProcessorsAverageLoad");
                
                TotalGarbageCollectionCount = (Long) connections.getAttribute(jvmRT, "TotalGarbageCollectionCount");
                
                TotalGarbageCollectionTime = (Long) connections.getAttribute(jvmRT, "TotalGarbageCollectionTime");
                
                JVMProcessorLoad = (Double) connections.getAttribute(jvmRT, "JvmProcessorLoad");
                
                stats.setNumberOfProcessors(NumberOfProcessors);
                stats.setAllProcessorsAverageLoad(AllProcessorsAverageLoad);
                stats.setTotalGarbageCollectionCount(TotalGarbageCollectionCount);
                stats.setGarbageCollectionTime(TotalGarbageCollectionTime);
                stats.setJVMProcessorLoad(JVMProcessorLoad);
                
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error getting Processor and GC information (Most likely, Weblogic is not exposing this information. " + e);
            	}
            	stats.setNumberOfProcessors(NumberOfProcessors);
                stats.setAllProcessorsAverageLoad(AllProcessorsAverageLoad);
                stats.setTotalGarbageCollectionCount(TotalGarbageCollectionCount);
                stats.setGarbageCollectionTime(TotalGarbageCollectionTime);
                stats.setJVMProcessorLoad(JVMProcessorLoad);
                
            }
            
            statsList.add(stats);
            ad.addServerStatsList(statsList);
            
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error JVM data: " + e);
        	}
        	
        }
        test = ad.getArrayList();
		return test;
    }
}

