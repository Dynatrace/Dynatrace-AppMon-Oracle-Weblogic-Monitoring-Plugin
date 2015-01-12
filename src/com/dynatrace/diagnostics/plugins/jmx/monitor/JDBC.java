package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;

public class JDBC implements Callable<ArrayList<JDBCStats>>, WeblogicConstants {

	private ArrayList<JDBCStats> test;
	
	public JDBC(ArrayList<JDBCStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(JDBC.class.getName());
	private ArrayList<JDBCStats> statsList = new ArrayList<JDBCStats>();
    private ObjectName[] appRT;
    private String name;
    private int appLength;
    
    JDBCTimeHolder ad = new JDBCTimeHolder();
    
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String adminServer;

    public JDBC(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment, String adminServerName) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        adminServer=adminServerName;
    }

    @Override
    public ArrayList<JDBCStats> call() throws Exception {
        try {
            name = (String) connections.getAttribute(destRT, "Name");
            appRT = (ObjectName[]) connections.getAttribute(new ObjectName("com.bea:Name=" + name + ",ServerRuntime=" + name + ",Location=" + name + ",Type=JDBCServiceRuntime"), "JDBCDataSourceRuntimeMBeans");
            appLength = (int) appRT.length;
        } catch (Exception e) {
            log.warning("JDBC Error" + name + ": " + e);
            
        }
        for (int x = 0; x < appLength; x++) {
            try {
            	JDBCStats stats = new JDBCStats();
            	
            	stats.setserverName(name);
            	stats.setEnviro(environments);
                String JDBCName = (String) connections.getAttribute(appRT[x], "Name");
                stats.setJDBCName(JDBCName);
                
                int ActiveCHCount = (Integer) connections.getAttribute(appRT[x], "ActiveConnectionsHighCount");
                
                stats.setActiveConnectionsHighCount(ActiveCHCount);
                    
                int ActiveConnectionsCurrentCount = (Integer) connections.getAttribute(appRT[x], "ActiveConnectionsCurrentCount");
                
                stats.setActiveConnectionsCurrentCount(ActiveConnectionsCurrentCount);
                
                long FailedReserveRequestCount = (Long) connections.getAttribute(appRT[x], "FailedReserveRequestCount");
                
                stats.setFailedReserveRequestCount(FailedReserveRequestCount);
                
                int FailuresToReconnectCount = (Integer) connections.getAttribute(appRT[x], "FailuresToReconnectCount");
                
                stats.setFailuresToReconnectCount(FailuresToReconnectCount);
                
                int LeakedConnectionCount = (Integer) connections.getAttribute(appRT[x], "LeakedConnectionCount");
                
                stats.setLeakedConnectionCount(LeakedConnectionCount);
                
                int CurrCap = (Integer) connections.getAttribute(appRT[x], "CurrCapacity");
                
                stats.setCurrCapacity(CurrCap);
                
                int ConnectionsHC = (Integer) connections.getAttribute(appRT[x], "CurrCapacityHighCount");
                
                stats.setCurrCapacityHighCount(ConnectionsHC);
                
                int ConnectionDelayTimes = (Integer) connections.getAttribute(appRT[x], "ConnectionDelayTime");
                
                stats.setConnectionDelayTime(ConnectionDelayTimes);
                
                int PrepStmtCacheHitCounts = (Integer) connections.getAttribute(appRT[x], "PrepStmtCacheHitCount");
                
                stats.setPrepStmtCacheHitCount(PrepStmtCacheHitCounts);
                
                int PrepStmtCacheMissCounts = (Integer) connections.getAttribute(appRT[x], "PrepStmtCacheMissCount");
                
                stats.setPrepStmtCacheMissCount(PrepStmtCacheMissCounts);
                
                long PrepStmtCacheDeleteCounts = (Long) connections.getAttribute(appRT[x], "PrepStmtCacheDeleteCount");
                
                stats.setPrepStmtCacheDeleteCount(PrepStmtCacheDeleteCounts);
                
                String state = (String) connections.getAttribute(appRT[x], "State");
                int State = 0;
                if (!state.toLowerCase().contains("running")) {
                	State = 1;
                }    
                
                stats.setstate(State);
                statsList.add(stats);
                ad.addServerStatsList(statsList);
                
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error JDBC data: " + e);
            	}
            	//Normally there is no JDBC connections on the Admin Server. 
                    if (name.equalsIgnoreCase(adminServer)) {
                    	log.info("JDBC - Error with admin server.");
                        break;
                    }
            }
        }
        test = ad.getArrayList();
		return test;
    }
}
