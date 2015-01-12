package com.dynatrace.diagnostics.plugins.jmx;

import com.dynatrace.diagnostics.pdk.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Iterator;

import com.dynatrace.diagnostics.plugins.jmx.connection.WeblogicConnection;
import com.dynatrace.diagnostics.plugins.jmx.convertdata.DateTime;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ApplicationData;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ApplicationState;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ApplicationStateSingle;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ClusterCommunication;
import com.dynatrace.diagnostics.plugins.jmx.monitor.EJBBean;
import com.dynatrace.diagnostics.plugins.jmx.monitor.FileStore;
import com.dynatrace.diagnostics.plugins.jmx.monitor.JDBC;
import com.dynatrace.diagnostics.plugins.jmx.monitor.JMS;
import com.dynatrace.diagnostics.plugins.jmx.monitor.JVM;
import com.dynatrace.diagnostics.plugins.jmx.monitor.Threads;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ThreadsWork;
import com.dynatrace.diagnostics.plugins.jmx.monitor.Transaction;
import com.dynatrace.diagnostics.plugins.jmx.monitor.WLServer;
import com.dynatrace.diagnostics.plugins.jmx.monitor.EJB;
import com.dynatrace.diagnostics.plugins.jmx.record.DynamicMeasure;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationSStateStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationStateStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ClusterStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBBStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.FileStoreStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JMSStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadWorkmanagerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;

import javax.management.ObjectInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class WeblogicMonitoring implements Monitor, WeblogicConstants {

	private static final Logger log = Logger.getLogger(WeblogicMonitoring.class.getName());
	
	private ObjectName service=null;
	private WeblogicConnection wc = null;
	private MBeanServerConnection connection=null;
	private String adminServerName;
	String environment;
	String measureName;
	Boolean threads;
	Boolean jvm;
	Boolean wlserver;
	Boolean transaction;
	Boolean applicationData;
	Boolean ejb;
	Boolean applicationState;
	Boolean fileStore;
	Boolean jms;
	Boolean cluster;
	Boolean jdbc;
	String[] dataMonitor;
	String[] stateIgnore;
	String[] agents;
	String[] deployedSingle;
	String[] jmsMod;
	String[] jmsQueue;
	String[] ejbMonitor;
	String host;
	String port;
	String username;
	String password;
	int threadPoolCount;
	int timeOut;
	
	ExecutorService serviceThread;
	
	public static boolean isEmptyOrBlank(String str) {
		return str == null || str.trim().isEmpty();
		}

	
	private WeblogicProperties properties;

	@Override
	public Status setup(MonitorEnvironment env) throws Exception {
		
		String s;
		properties = new WeblogicProperties();
		
		// set plugin's configuration parameters
		properties.setDtServer(env.getConfigString(CONFIG_DT_SERVER).trim());
		properties.setJmxPort(env.getConfigString(CONFIG_JMX_PORT).trim());
		properties.setJmxUserName(env.getConfigString(CONFIG_WEBLOGIC_USERNAME).trim());
		properties.setJmxPassword(env.getConfigPassword(CONFIG_WEBLOGIC_PASSWORD).trim());
		properties.setEnvironment(env.getConfigString(CONFIG_WEBLOGIC_ENVIRONMENT).trim());
		properties.setSystemProfile(env.getConfigString(CONFIG_SYSTEM_PROFILE).trim());
		properties.setMetrics((s = env.getConfigString(CONFIG_METRICS)) == null ? new String[0] : s.trim().split(";"));
		properties.setAgentGroups((s = env.getConfigString(CONFIG_AGENT_GROUPS)) == null ? new String[0] : s.trim().split(";"));
		properties.setThreads(env.getConfigBoolean(CONFIG_WEBLOGIC_THREADS));
		properties.setAppState(env.getConfigBoolean(CONFIG_WEBLOGIC_APPLICATIONSTATE));
		properties.setJVM(env.getConfigBoolean(CONFIG_WEBLOGIC_JVM));
		properties.setWLServer(env.getConfigBoolean(CONFIG_WEBLOGIC_WLSERVER));
		properties.setCluster(env.getConfigBoolean(CONFIG_WEBLOGIC_CLUSTER));
		properties.setTransaction(env.getConfigBoolean(CONFIG_WEBLOGIC_TRANSACTION));
		properties.setFileStore(env.getConfigBoolean(CONFIG_WEBLOGIC_FILESTORE));
		properties.setJMS(env.getConfigBoolean(CONFIG_WEBLOGIC_JMS));
		properties.setAppData(env.getConfigBoolean(CONFIG_WEBLOGIC_APPLICATIONDATA));
		properties.setJDBC(env.getConfigBoolean(CONFIG_WEBLOGIC_JDBC));
		properties.setEJB(env.getConfigBoolean(CONFIG_WEBLOGIC_EJBDATA));
		properties.setAppDeployedSingle(env.getConfigString(CONFIG_WEBLOGIC_APPDEPLOYEDSINGLE).trim());
		properties.setAppDataMonitor(env.getConfigString(CONFIG_WEBLOGIC_APPDATAMONITOR).trim());
		properties.setAppStateDeployed(env.getConfigString(CONFIG_WEBLOGIC_APPSTATE).trim());
		properties.setsafAgents(env.getConfigString(CONFIG_WEBLOGIC_SAF).trim());
		properties.setAdminName(env.getConfigString(CONFIG_WEBLOGIC_ADMINNAME).trim());
		properties.setejbDataMonitor(env.getConfigString(CONFIG_WEBLOGIC_EJBDATAMONITOR).trim());
		properties.setJMSMods(env.getConfigString(CONFIG_WEBLOGIC_JMSMODS).trim());
		properties.setJMSQueue(env.getConfigString(CONFIG_WEBLOGIC_JMSQUEUE).trim());
		properties.setthreadCount(env.getConfigString(CONFIG_THREADCOUNT_MONITOR).trim());
		properties.settimeOut(env.getConfigString(CONFIG_TIMEOUT_MONITOR).trim());
		
		//add to properties so user can adjust.
		String tCount=properties.getthreadCount(); 
		threadPoolCount=Integer.valueOf(tCount);
		String tout=properties.gettimeOut();
		//How long we are willing to wait for a response.
		timeOut=Integer.valueOf(tout);
		
		if (threadPoolCount < 1) {
            threadPoolCount = 1;
        } 
		if (threadPoolCount > 10) {
            threadPoolCount = 10;
        }
		
		
		log.info("Monitoring Thread count: " + threadPoolCount);
		
		//Need to add timeout....
		serviceThread =  Executors.newFixedThreadPool(threadPoolCount);
		
		host = properties.getDtServer();
		port =properties.getJmxPort();
		username=properties.getJmxUserName();
		password=properties.getJmxPassword();
		environment=properties.getEnvironment();
		adminServerName=properties.getAdminName();
		
		if (isEmptyOrBlank(host)){
			host="nothingWasEntered";
		}
		if (isEmptyOrBlank(port)){
			port="nothingWasEntered";
		}
		if (isEmptyOrBlank(username)){
			username="nothingWasEntered";
		}
		if (isEmptyOrBlank(password)){
			password="nothingWasEntered";
		}
		if (isEmptyOrBlank(environment)){
			environment="nothingWasEntered";
		}
		if (isEmptyOrBlank(adminServerName)){
			adminServerName="nothingWasEntered";
		}

		String deployedSingleApps=properties.getAppDeployedSingle();
		String monitorAppData=properties.getAppDataMonitor();
		String monitorEJBs=properties.getejbDataMonitor();
		String ignoreAppState=properties.getAppStateDeployed();
		String safAgent=properties.getsafAgents();
		String jmsMods=properties.getJMSMods();
		String jmsQueues=properties.getJMSQueue();
		
		threads=properties.getThreads();
		jvm=properties.getJVM();
		wlserver=properties.getWLServer();
		transaction=properties.getTransaction();
		applicationData=properties.getAppData();
		applicationState=properties.getAppState();
		fileStore=properties.getFileStore();
		jms=properties.getJMS();;
		cluster=properties.getCluster();
		jdbc=properties.getJDBC();
		ejb=properties.getEJB();
		
		
		if (isEmptyOrBlank(jmsMods)){
			jmsMods="nothingWasEntered";
		}
		if (isEmptyOrBlank(jmsQueues)){
			jmsQueues="nothingWasEntered";
		}
		if (isEmptyOrBlank(deployedSingleApps)){
			deployedSingleApps="nothingWasEntered";
		}
		if (isEmptyOrBlank(monitorAppData)){
			monitorAppData="nothingWasEntered";
		}
		if (isEmptyOrBlank(ignoreAppState)){
			ignoreAppState="nothingWasEntered";
		}
		if (isEmptyOrBlank(safAgent)){
			safAgent="nothingWasEntered";
		}
		if (isEmptyOrBlank(monitorEJBs)){
			monitorEJBs="nothingWasEntered";
		}
		
		
		jmsMod=jmsMods.split("\\s+");
		jmsQueue=jmsQueues.split("\\s+");

		//Single DEPLOYED APP STUFF
		deployedSingle = deployedSingleApps.split("\\s+");
		
		//APP DATA IGNORE STUFF
		dataMonitor = monitorAppData.split("\\s+");
		
		//EJBs to Monitor ejbeans
		ejbMonitor=monitorEJBs.split("\\s+");
		
		//APP STATE IGNORE STUFF
		stateIgnore = ignoreAppState.split("\\s+");
		
		//SAF AGENT STUFF
		agents = safAgent.split("\\s+");
		
		service = new ObjectName("com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");

		wc = new WeblogicConnection();
		connection = wc.initConnection(host, port, username, password);
		
		if(connection==null){
			DynamicMeasure testing = new DynamicMeasure();
			log.warning("Connection is null. We will try again. Server connection information = " + host + " " + port + " " + username);
			measureName = "AdminConnection|"+environment;
			Float f = (float) 0;
			testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
			return new Status(Status.StatusCode.PartialSuccess, "Connection Failure. Weblogic Admin Server: " + host + ":" + port 
					+ " with user: " + username);
		} else {
			return new Status(Status.StatusCode.Success);
		}
	}


	@Override
	public Status execute(MonitorEnvironment env) throws Exception {
		
		DynamicMeasure testing = new DynamicMeasure();
		if(connection==null){
			wc.closeConnection();
			connection = wc.initConnection(host, port, username, password);
			if(connection==null){
				measureName = "AdminConnection|"+environment;
				Float f = (float) 0;
				testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
				return new Status(Status.StatusCode.PartialSuccess, "Unable to connect to the Admin Server: " + properties.getDtServer() + ":" + properties.getJmxPort());
			}
		}
		
        String environment=properties.getEnvironment();
		String server_name="";
		
        ObjectName[] server_list=null;
        try{
        	server_list = (ObjectName[]) connection.getAttribute(service, "ServerRuntimes");
        	log.info("Server List Size = " + server_list.length);
        	Integer size = server_list.length;
        	measureName = "DomainServerCount|"+environment;
            testing.populateDynamicMeasure(env, WEBLOGIC_SERVER_GROUP, WEBLOGIC_SERVERCOUNT_METRIC, measureName, (double)size);
        }catch (IOException e) {
        	if(connection != null){
        		wc.closeConnection();
        		connection = wc.initConnection(host, port, username, password);
        		if(connection == null){
                	measureName = "AdminConnection|"+environment;
        			Float f = (float) 0;
        			testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
        			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBLOGIC_CONNECTION_GROUP + " " +  WEBLOGIC_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
        			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime " + host + ":" + port + ":" + username); //stops the execute method...
        		} else {
        			try{
        	        	server_list = (ObjectName[]) connection.getAttribute(service, "ServerRuntimes");
        	        	log.info("Server List Size = " + server_list.length);
        	        	Integer size = server_list.length;
        	        	measureName = "DomainServerCount|"+environment;
        	            testing.populateDynamicMeasure(env, WEBLOGIC_SERVER_GROUP, WEBLOGIC_SERVERCOUNT_METRIC, measureName, (double)size);
        	        }catch (IOException g) {
        	        	wc.closeConnection();
        	        	connection=null;
                    	measureName = "AdminConnection|"+environment;
            			Float f = (float) 0;
            			testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
            			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBLOGIC_CONNECTION_GROUP + " " +  WEBLOGIC_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
            			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime: " + host + ":" + port + ":" + username); 
        	        }
        		}
        	}
        }
        
        long webMonitorStartTime = DateTime.getDateTime();
        
        for (ObjectName sl : server_list) {
            try {
                server_name = (String) connection.getAttribute(sl, "Name");
                Set<ObjectInstance> mbeans = connection.queryMBeans(new ObjectName("*:Location=" + server_name + ",Type=ServerRuntime,*"), null);
                Iterator<ObjectInstance> iter = mbeans.iterator();
                while (iter.hasNext()) {
                	String cat = (iter.next().getObjectName().getCanonicalName());
                	ObjectName destR = new ObjectName(cat);
                	log.info("Sending Environment: " + environment);
                	Future<ArrayList<ThreadStats>> thd = null;
                	Future<ArrayList<ThreadWorkmanagerStats>> thwd = null;
                	if(threads){
                		log.info("Entering Thread Monitoring " + server_name);
                		Threads sumTask = new Threads(destR, connection, env, environment);
                		thd = serviceThread.submit(sumTask);
                    	log.info("Thread - Job was submitted");
                    	
                    	log.info("Entering Thread Workmanager Monitoring " + server_name);
                		ThreadsWork sumTasks = new ThreadsWork(destR, connection, env, environment);
                		thwd = serviceThread.submit(sumTasks);
                    	log.info("Workmanager - Job was submitted");
                	}
                	Future<ArrayList<WLServerStats>> wld = null;
                	if(wlserver){
                		log.info("Entering WLServer Monitoring " + server_name);
                		WLServer sumTask = new WLServer(destR, connection, env, environment);
                		wld = serviceThread.submit(sumTask);
                    	log.info("WLServer - Job was submitted");
                	}
                	Future<ArrayList<JDBCStats>> dbd = null;
                	if(jdbc){
                		log.info("Entering JDBC Monitoring " + server_name);
                		JDBC sumTask = new JDBC(destR, connection, env, environment, adminServerName);
                		dbd = serviceThread.submit(sumTask);
                    	log.info("JDBC - Job was submitted");
                	}
                	Future<ArrayList<ClusterStats>> cld = null;
                	if(cluster){
                		log.info("Entering Cluster Monitoring " + server_name);	
                		ClusterCommunication sumTask = new ClusterCommunication(destR, connection, env, environment, adminServerName);
                		cld = serviceThread.submit(sumTask);
                    	log.info("Cluster - Job was submitted");
                	}
                	Future<ArrayList<TransactionStats>> trd = null;
                	if(transaction){
                		log.info("Entering Transaction Monitoring " + server_name);
                		Transaction sumTask = new Transaction(destR, connection, env, environment);
                		trd = serviceThread.submit(sumTask);
                    	log.info("Transaction - Job was submitted");
                		
                	}
                	Future<ArrayList<FileStoreStats>> fsd = null;
                	if(fileStore){
                		log.info("Entering FileStore Monitoring " + server_name);
                		FileStore sumTask = new FileStore(destR, connection, env, environment);
                		fsd = serviceThread.submit(sumTask);
                    	log.info("File Store - Job was submitted");
                		
                	}
                	Future<ArrayList<JMSStats>> jmsd = null;
                	if(jms){
                		log.info("Entering JMS Monitoring " + server_name);
                		JMS sumTask = new JMS(destR, connection, env, environment, agents, jmsMod, jmsQueue);
                		jmsd = serviceThread.submit(sumTask);
                    	log.info("JMS - Job was submitted");
                	}
                	Future<ArrayList<JVMServerStats>> jvmd = null;
                	if(jvm){
                		log.info("Entering JVM Monitoring " + server_name);
                		JVM sumTask = new JVM(destR, connection, env, environment);
                		jvmd = serviceThread.submit(sumTask);
                    	log.info("JVM - Job was submitted");
                		
                	}
                	Future<ArrayList<ApplicationDataStats>> ads = null;
                    if(applicationData){
                    	log.info("In Application Data collection" + server_name);
                    	ApplicationData sumTask = new ApplicationData(destR, connection, env, environment, dataMonitor);
                    	ads = serviceThread.submit(sumTask);
                    	log.info("Application Data - Job was submitted");
                    }
                    Future<ArrayList<ApplicationStateStats>> as = null;
                    Future<ArrayList<ApplicationSStateStats>> adss = null;
                	if(applicationState){
                		log.info("Entering Application State Monitoring " + server_name);
                		
                    	ApplicationState sumTask = new ApplicationState(destR, connection, env, environment, deployedSingle, stateIgnore);
                    	as = serviceThread.submit(sumTask);
                    	
                    	ApplicationStateSingle sumTasks = new ApplicationStateSingle(destR, connection, env, environment, deployedSingle, stateIgnore);
                    	adss = serviceThread.submit(sumTasks);
                    	
                    	log.info("Application State - Jobs were submitted");
                	}                    
                	Future<ArrayList<EJBStats>> ejbt = null;
                	Future<ArrayList<EJBBStats>> ejbb = null;
                	if(ejb){
                		log.info("Entering EJB Monitoring " + server_name);
                		//Trans Data
                		EJB sumTask = new EJB(destR, connection, env, environment, dataMonitor, ejbMonitor);
                		ejbt = serviceThread.submit(sumTask);
                		
                		//Bean Data
                		EJBBean sumTasks = new EJBBean(destR, connection, env, environment, dataMonitor, ejbMonitor);
                		ejbb = serviceThread.submit(sumTasks);
                		log.info("EJB - Jobs were submitted");
                	}                    

// populate gathered dynamic measures in PWH
                	if(threads){
                		try{
                			ArrayList<ThreadStats> result = thd.get(timeOut, TimeUnit.MILLISECONDS);
                			log.info("Threads - Result size: " + result.size());
                			if (result.size() > 0) {
                            	com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadMeasure();
                            	DTAS.WriteToDT(env, result);
                			}
                		}catch(TimeoutException e){
                	        log.warning("Threads - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        thd.cancel(true);
                		}
                		try{
                			ArrayList<ThreadWorkmanagerStats> results = thwd.get(timeOut, TimeUnit.MILLISECONDS);
                			log.info("Workmanager - Result size: " + results.size());
                			if (results.size() > 0) {
                				com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadWorkMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadWorkMeasure();
                				DTAS.WriteToDT(env, results);
                			}
                		}catch(TimeoutException e){
                			log.warning("Workmanager - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        thwd.cancel(true);
                		}
                	}
            		if(wlserver){
            			try{
            				ArrayList<WLServerStats> results = wld.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("WLServer - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordWLServerMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordWLServerMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("WLServer - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        wld.cancel(true);
                		}
                	}
            		if(jdbc){
            			try{
            				ArrayList<JDBCStats> results = dbd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JDBC - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordJDBCMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordJDBCMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("JDBC - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        dbd.cancel(true);
                		}
                	}
            		if(cluster){
            			try{
            				ArrayList<ClusterStats> results = cld.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Cluster - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordClusterMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordClusterMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("Cluster - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        cld.cancel(true);
                		}
                	}
            		if(transaction){
            			try{
            				ArrayList<TransactionStats> results = trd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Transaction - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordTransactionMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordTransactionMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("Transaction - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        trd.cancel(true);
                		}
                	}
            		if(fileStore){
            			try{
            				ArrayList<FileStoreStats> results = fsd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("File Store - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordFileStoreMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordFileStoreMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("File Store - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        fsd.cancel(true);
                		}
            		}
            		if (jms){
            			try{
            				ArrayList<JMSStats> results = jmsd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JMS - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordJMSMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordJMSMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("JMS - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        jmsd.cancel(true);
                		}
            		}
            		if(jvm){
            			try{
            				ArrayList<JVMServerStats> results = jvmd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JVM - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordJVMMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordJVMMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("JVM - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        jvmd.cancel(true);
                		}
            		}
            		if(applicationData){
            			try{
            				ArrayList<ApplicationDataStats> result = ads.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Application Data - Result size: " + result.size());
            				if (result.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordApplicationDataMeasure DTAD = new com.dynatrace.diagnostics.plugins.jmx.record.RecordApplicationDataMeasure();
            					DTAD.WriteToDT(env, result);
            					log.info("AppData was sent to PWH");
            				}
            			}catch(TimeoutException e){
            				log.warning("Application Data - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        ads.cancel(true);
                		}
            		}
            		if(applicationState){
            			try{
            				ArrayList<ApplicationStateStats> result = as.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Application State - Result size: " + result.size());
            				if (result.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordStateMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordStateMeasure();
            					DTAS.WriteToDT(env, result);
            				}
            			}catch(TimeoutException e){
            				log.warning("Application State - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        as.cancel(true);
                		}
            			try{
            				ArrayList<ApplicationSStateStats> results = adss.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Application State - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordSingleStateMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordSingleStateMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("Application State - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        adss.cancel(true);
                		}
                    }
            		if(ejb){
            			try{
            				ArrayList<EJBStats> result = ejbt.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("EJB - Result size: " + result.size());
            				if (result.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordEJBMeasure DTEJB = new com.dynatrace.diagnostics.plugins.jmx.record.RecordEJBMeasure();
            					DTEJB.WriteToDT(env,result);
            				}
            			}catch(TimeoutException e){
            				log.warning("EJB - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        ejbt.cancel(true);
                		}
            			try{
            				ArrayList<EJBBStats> results = ejbb.get(timeOut, TimeUnit.MILLISECONDS);
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordEJBBeanMeasure DTEJB = new com.dynatrace.diagnostics.plugins.jmx.record.RecordEJBBeanMeasure();
            					DTEJB.WriteToDT(env,results);
            				}
            			}catch(TimeoutException e){
            				log.warning("EJB - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        ejbb.cancel(true);
                		}
            		}
                }
            } catch (IOException e) {
            	log.info("Exception in Monitoring " + environment + ": " + e);
            	if(connection != null){
            		wc.closeConnection();
            		connection = wc.initConnection(host, port, username, password);
            		if(connection == null){
            			measureName = "AdminConnection|"+environment;
            			Float f = (float) 0;
            			testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
            			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBLOGIC_CONNECTION_GROUP + " " +  WEBLOGIC_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
            			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime: " + host + ":" + port + ":" + username + e);
            		} else{
            			return new Status(Status.StatusCode.PartialSuccess, "Connection reestablished: " + host + ":" + port + ":" + username);
            		}
            	}
            }  
        }
		
		//How long it took to monitor the domain.
        long webPerfEndTime = DateTime.getDateTime();
		long WebtotalPerfTime = (webPerfEndTime - webMonitorStartTime); 
		measureName = "MonitorDomainTime|"+environment;
		testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_MONITORTIME_METRIC, measureName, (double)WebtotalPerfTime);
		
	    measureName = "AdminConnection|"+environment;
		Float f = (float) 1;
		testing.populateDynamicMeasure(env, WEBLOGIC_CONNECTION_GROUP, WEBLOGIC_CONNECTION_METRIC, measureName, (double)f);
		log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBLOGIC_CONNECTION_GROUP + " " +  WEBLOGIC_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
		return new Status(Status.StatusCode.Success);
	}

	@Override
	 public void teardown(MonitorEnvironment env) throws Exception {
		if (log.isLoggable(Level.INFO)) {
			log.info("Entering teardown method");
		}
		if ((connection != null)) {
			wc.closeConnection();
		}
		//On plugin failure we want to ensure the old threads are shutdown.
		try {
			serviceThread.shutdown(); // Disable new tasks from being submitted
			   try {
			     // Wait a while for existing tasks to terminate
			     if (!serviceThread.awaitTermination(5, TimeUnit.SECONDS)) {
			    	 serviceThread.shutdownNow(); // Cancel currently executing tasks
			       // Wait a while for tasks to respond to being cancelled
			       if (!serviceThread.awaitTermination(30, TimeUnit.SECONDS))
			    	   log.warning("Pool did not terminate");
			     }
			   } catch (InterruptedException ie) {
			     // (Re-)Cancel if current thread also interrupted
				   serviceThread.shutdownNow();
			     // Preserve interrupt status
			     Thread.currentThread().interrupt();
			   }

		} catch (Exception e){
			log.warning("Exception with shutting down threads: " + e);
		}
	}
}