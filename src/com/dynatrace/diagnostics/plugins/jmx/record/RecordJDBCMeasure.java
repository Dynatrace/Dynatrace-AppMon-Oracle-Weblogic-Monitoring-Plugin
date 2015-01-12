package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordJDBCMeasure implements WeblogicConstants {

	private static final Logger log = Logger.getLogger(RecordJDBCMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<JDBCStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
        	ArrayList<JDBCStats> list = ad;
            for (JDBCStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String JDBCName = fromStatic.getJDBCName();
                Integer ActiveConnectionsHighCount = fromStatic.getActiveConnectionsHighCount();
                Integer ActiveConnectionsCurrentCount = fromStatic.getActiveConnectionsCurrentCount();
                Long FailedReserveRequestCount = fromStatic.getFailedReserveRequestCount();
                Integer FailuresToReconnectCount = fromStatic.getFailuresToReconnectCount();
                Integer LeakedConnectionCount = fromStatic.getLeakedConnectionCount();
                Integer CurrCapacity = fromStatic.getCurrCapacity();
                Integer CurrCapacityHighCount = fromStatic.getCurrCapacityHighCount();
                Integer ConnectionDelayTime = fromStatic.getConnectionDelayTime();
                Integer PrepStmtCacheHitCount = fromStatic.getPrepStmtCacheHitCount();
                Integer PrepStmtCacheMissCount = fromStatic.getPrepStmtCacheMissCount();
                Long PrepStmtCacheDeleteCount = fromStatic.getPrepStmtCacheDeleteCount();
                String Enviro = fromStatic.getEnviro();
                Integer state = fromStatic.getstate();
		
                measureName = WLServerName+"|JDBC|" + JDBCName + "|ActiveConnectionsHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_ACTIVECONNECTIONHIGH_METRIC, measureName, (double)ActiveConnectionsHighCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|ActiveConnectionsCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_ACTIVECONNECTIONCURRENT_METRIC, measureName, (double)ActiveConnectionsCurrentCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FailedReserveRequest|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_FAILEDRESERVEREQUEST_METRIC, measureName, (double)FailedReserveRequestCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FailuresToReconnect|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_FAILURESTORECONNECT_METRIC, measureName, (double)FailuresToReconnectCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|LeakedConnection|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_LEAKEDCONNECTION_METRIC, measureName, (double)LeakedConnectionCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|CurrentCapacity|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_CURRENTCAPACITY_METRIC, measureName, (double)CurrCapacity);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|ConnectionHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_CURRENTCAPACITYHIGH_METRIC, measureName, (double)CurrCapacityHighCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|ConnectionDelayTimes|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_CONNECTIONDELAYTIME_METRIC, measureName, (double)ConnectionDelayTime);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PrepStmtCacheHit|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_PREPSTMTCACHEHIT_METRIC, measureName, (double)PrepStmtCacheHitCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PrepStmtCacheMiss|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_PREPSTMTCACHEMISS_METRIC, measureName, (double)PrepStmtCacheMissCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PrepStmtCacheDelete|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_PREPSTMTCACHEDELETE_METRIC, measureName, (double)PrepStmtCacheDeleteCount);
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|RunningState|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_JDBC_GROUP, WEBLOGIC_JDBCSTATE_METRIC, measureName, (double)state);
                
            }
            
          } catch (Exception e) {
              log.warning("Record JDBC data: " + e);
          } 
	}
	
}
