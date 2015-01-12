package com.dynatrace.diagnostics.plugins.jmx.monitor;


import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.FileStoreStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.FileStoreTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;


public class FileStore implements Callable<ArrayList<FileStoreStats>>, WeblogicConstants {
	
	private ArrayList<FileStoreStats> test;
	
	public FileStore(ArrayList<FileStoreStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(FileStore.class.getName());
	private ArrayList<FileStoreStats> statsList = new ArrayList<FileStoreStats>();
	
	FileStoreTimeHolder ad = new FileStoreTimeHolder();
	
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    
    public FileStore(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<FileStoreStats> call() throws Exception {
        try {
            String name = (String) connections.getAttribute(destRT, "Name");
            ObjectName[] appRT = (ObjectName[]) connections.getAttribute(destRT, "PersistentStoreRuntimes");
            for (ObjectName app : appRT) {
            	FileStoreStats stats = new FileStoreStats();
               
            	stats.setserverName(name);
            	stats.setEnviro(environments);
            	
                String StoreName = connections.getAttribute(app, "Name").toString();
                stats.setStoreName(StoreName);
                
                String CreateCount = connections.getAttribute(app, "CreateCount").toString();
                long l = Long.parseLong(CreateCount);
                
                stats.setCreateCount(l);
                String DeleteCount = connections.getAttribute(app, "DeleteCount").toString();
                
                l = Long.parseLong(DeleteCount);
                
                stats.setDeleteCount(l);
                String PhysicalWriteCount = connections.getAttribute(app, "PhysicalWriteCount").toString();
                
                l = Long.parseLong(PhysicalWriteCount);
                
                stats.setPhysicalWriteCount(l);
                String ReadCount = connections.getAttribute(app, "ReadCount").toString();
                
                l = Long.parseLong(ReadCount);
                
                stats.setReadCount(l);
                String UpdateCount = connections.getAttribute(app, "UpdateCount").toString();
                
                l = Long.parseLong(UpdateCount);
                
                stats.setUpdateCount(l);
                statsList.add(stats);
                ad.addServerStatsList(statsList);
                statsList.clear();
            }
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error File Store data: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}