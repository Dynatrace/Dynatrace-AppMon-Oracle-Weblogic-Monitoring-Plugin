package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordJVMMeasure implements WeblogicConstants {
	
	private static final Logger log = Logger.getLogger(RecordJVMMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs,ArrayList<JVMServerStats> ad)  throws Exception {
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<JVMServerStats> list = ad;
            for (JVMServerStats fromStatic : list) {
                String WLServerName = fromStatic.getServerName();
                String Enviro = fromStatic.getEnviro();
                Long garbageCollectionTime = fromStatic.getGarbageCollectionTime();
                Double AllProcessorsAverageLoad = fromStatic.getAllProcessorsAverageLoad();
                Long TotalGarbageCollectionCount = fromStatic.getTotalGarbageCollectionCount();
                Double JVMProcessorLoad = fromStatic.getJVMProcessorLoad();
                Integer NumberOfProcessors = fromStatic.getNumberOfProcessors();
                Long HeapTotal = fromStatic.getHeapTotalCurrent();
                Long HF = fromStatic.getHFCurrent();
                Long HUCurrent = fromStatic.getHUCurrent();
                
                String measureName = WLServerName+"|JVM|HeapSizeCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_HEAPSIZECURRENT_METRIC, measureName, (double)HeapTotal);
                
                measureName = WLServerName+"|JVM|HeapFreeCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_HEAPFREECURRENT_METRIC, measureName, (double)HF);
                
                measureName = WLServerName+"|JVM|HeapUsed|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_HEAPUSEDCURRENT_METRIC, measureName, (double)HUCurrent);
                
                measureName = WLServerName+"|JVM|NumberOfProcessors|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_NUMBEROFPROCESSORS_METRIC, measureName, (double)NumberOfProcessors);
                
                measureName = WLServerName+"|JVM|AllProcessorsAverageLoad|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_ALLPROCESSORSAVERAGELOAD_METRIC, measureName, (double)AllProcessorsAverageLoad);
                
                measureName = WLServerName+"|JVM|TotalGarbageCollectionCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_TOTALGARBAGECOLLECTIONCOUNT_METRIC, measureName, (double)TotalGarbageCollectionCount);
                
                measureName = WLServerName+"|JVM|TotalGarbageCollectionTime|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_TOTALGARBAGECOLLECTIONTIME_METRIC, measureName, (double)garbageCollectionTime);
                
                measureName = WLServerName+"|JVM|JvmProcessorLoad|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JVM_GROUP, WEBLOGIC_JVMPROCESSORLOAD_METRIC, measureName, (double)JVMProcessorLoad);
                
            }
		
		
		} catch (Exception e) {
            log.warning("Record JVM data: " + e);
        } 
	}
}
