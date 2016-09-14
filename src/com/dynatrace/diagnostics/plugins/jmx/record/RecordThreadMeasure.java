package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;


public class RecordThreadMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordThreadMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ThreadStats> ad)  throws Exception {
		DynamicMeasure testing = new DynamicMeasure();
		try {
            ArrayList<ThreadStats> list = ad;
            for (ThreadStats fromStatic : list) {

                String WLServerName = fromStatic.getserverName();
                int ExecuteThread = fromStatic.getExecuteThreadTotalCount();
                int HoggingThread = fromStatic.getHoggingThreadCount();
                int QueueLength = fromStatic.getQueueLength();
                long CompletedRequest = fromStatic.getCompletedRequestCount();
                int ExecuteThreadIdle = fromStatic.getExecuteThreadIdleCount();
                int StandbyThread = fromStatic.getStandbyThreadCount();
                double Throughput = fromStatic.getPresentThroughput();
                int health = fromStatic.gethealth();
                String Enviro = fromStatic.getEnviro();
                
                measureName = WLServerName+"|ExecuteThreadTotalCounts|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_EXECUTE_THREAD_TOTAL_METRIC, measureName, (double)ExecuteThread);
                measureName = WLServerName+"|ExecuteThreadIdleCounts|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_EXECUTE_THREAD_IDLE_METRIC, measureName, (double)ExecuteThreadIdle);
                measureName = WLServerName+"|HoggingThreadCounts|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_HOGGING_THREAD_METRIC, measureName, (double)HoggingThread);
                measureName = WLServerName+"|QueueLengths|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_QUEUE_LENGTH_METRIC, measureName, (double)QueueLength);
                measureName = WLServerName+"|StandbyThreadCounts|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_STANDBY_THREAD_METRIC, measureName, (double)StandbyThread);
                measureName = WLServerName+"|ThreadHealth|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_HEALTH_METRIC, measureName, (double)health);
                measureName = WLServerName+"|CompletedRequestCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_COMPLETED_REQUEST_METRIC, measureName, (double)CompletedRequest);
                measureName = WLServerName+"|Throughput|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_THREAD_GROUP, WEBLOGIC_THROUGHPUT_METRIC, measureName, (double)Throughput);
                
            }
		} catch (Exception e) {
            log.warning("Record Thread data: " + e);
        }
	}
}
