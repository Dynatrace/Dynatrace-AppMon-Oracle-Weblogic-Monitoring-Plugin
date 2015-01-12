package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadWorkmanagerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadWorkmanagerTimeHolder;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

import java.util.logging.Logger;
import java.util.concurrent.Callable;

import weblogic.management.runtime.ExecuteThread;

public class ThreadsWork implements Callable<ArrayList<ThreadWorkmanagerStats>>, WeblogicConstants {
	
	private ArrayList<ThreadWorkmanagerStats> test;
	
	public ThreadsWork(ArrayList<ThreadWorkmanagerStats> test){
        this.test = test;
	}	
	
	private static final Logger log = Logger.getLogger(ThreadsWork.class.getName());
    private ArrayList<ThreadWorkmanagerStats> workList = new ArrayList<ThreadWorkmanagerStats>();
    
    ThreadWorkmanagerTimeHolder ad = new ThreadWorkmanagerTimeHolder();
    
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    

    public ThreadsWork(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment) {
        envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
    }

    @Override
    public ArrayList<ThreadWorkmanagerStats> call() throws Exception {
        try {
        	HashMap<String, Properties> threadWorkManagerMap = new HashMap<String, Properties>();
        	String name = (String) connections.getAttribute(destRT, "Name");
        	ObjectName threadRT = (ObjectName) connections.getAttribute(destRT, "ThreadPoolRuntime");
            ExecuteThread[] executeThreads2 = (ExecuteThread[]) connections.getAttribute(threadRT, "ExecuteThreads");
            for (int jj = 0; jj < executeThreads2.length; jj++) {
                String currReq = executeThreads2[jj].getCurrentRequest();
                if (currReq == null) {
                    continue;
                }
                String appName = executeThreads2[jj].getApplicationName();
                String modName = executeThreads2[jj].getModuleName();
                String workManager = executeThreads2[jj].getWorkManagerName();

                if (appName == null) {
                    appName = "undefined";
                }
                if (modName == null) {
                    modName = "undefined";
                }

                if (workManager == null || workManager.equals("")) {
                    workManager = "default";
                }

                if (threadWorkManagerMap.containsKey(workManager)) {
                    Properties workManagerProps = threadWorkManagerMap.get(workManager);
                    threadWorkManagerMap.put(workManager, workManagerProps);
                    int threadCount = Integer.parseInt(workManagerProps.getProperty("threadCount"));
                    threadCount++;
                    workManagerProps.setProperty("threadCount", String.valueOf(threadCount));
                } else {
                    Properties workManagerProps = new Properties();
                    threadWorkManagerMap.put(workManager, workManagerProps);
                    workManagerProps.setProperty("serverName", name);
                    workManagerProps.setProperty("appName", appName);
                    workManagerProps.setProperty("threadCount", "1");
                    workManagerProps.setProperty("modName", modName);
                }
            }

            for (Iterator<String> it = threadWorkManagerMap.keySet().iterator(); it.hasNext();) {
            	ThreadWorkmanagerStats statsWork = new ThreadWorkmanagerStats();
            	
                String workManager1 = (String) it.next();
                Properties workManagerProps = threadWorkManagerMap.get(workManager1);
                int threadCount = Integer.parseInt(workManagerProps.getProperty("threadCount"));
                
                statsWork.setserverName(name);
                statsWork.setEnviro(environments);
                statsWork.setTotalThreads(threadCount);
                statsWork.setWorkManager(workManager1);
                String appName1 = workManagerProps.getProperty("appName");
                statsWork.setApplication(appName1);
                String modName = workManagerProps.getProperty("modName");
                statsWork.setModName(modName);
                
                workList.add(statsWork);
                ad.addServerStatsList(workList);
                workList.clear();
            }

        } catch (Exception e) {
        	log.info("Workmanager Exception: " + e);
        }
        test = ad.getArrayList();
		return test;
    }
}
