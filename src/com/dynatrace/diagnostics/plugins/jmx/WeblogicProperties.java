package com.dynatrace.diagnostics.plugins.jmx;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;

public class WeblogicProperties {
	private String environment;
	private String dtServer;
	private String jmxPort;
	private String JmxUserName;
	private String JmxPassword;
	private String systemProfile;
	private String[] metrics;
	private String[] agentGroups;
	private JMXServiceURL jmxServiceUrl;
	private JMXConnector jmxConnector;
	private MBeanServerConnection mbsConnection;
	private String objectNamePrefix;
	private Boolean threads;
	private Boolean appData;
	private Boolean jms;
	private Boolean jvm;
	private Boolean transaction;
	private Boolean fileStore;
	private Boolean appState;
	private Boolean cluster;
	private Boolean wlServer;
	private Boolean jdbc;
	private Boolean ejb;
	private String appDeployedSingle;
	private String appDataMonitor;
	private String ejbDataMonitor;
	private String appStateDeployed;
	private String safAgents;
	private String adminName;
	private String JMSMods;
	private String JMSQueue;
	private String threadCount;
	private String timeOut;
	
	public String getDtServer() {
		return dtServer;
	}
	public void setDtServer(String dtServer) {
		this.dtServer = dtServer;
	}
	public String getJmxPort() {
		return jmxPort;
	}
	public void setJmxPort(String jmxPort) {
		this.jmxPort = jmxPort;
	}
	public String getthreadCount() {
		return threadCount;
	}
	public void setthreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
	public String gettimeOut() {
		return timeOut;
	}
	public void settimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public String getSystemProfile() {
		return systemProfile;
	}
	public void setSystemProfile(String systemProfile) {
		this.systemProfile = systemProfile;
	}
	public String[] getMetrics() {
		return metrics;
	}
	public void setMetrics(String[] metrics) {
		this.metrics = metrics;
	}
	public String[] getAgentGroups() {
		return agentGroups;
	}
	public void setAgentGroups(String[] agentGroups) {
		this.agentGroups = agentGroups;
	}
	public JMXServiceURL getJmxServiceUrl() {
		return jmxServiceUrl;
	}
	public void setJmxServiceUrl(JMXServiceURL jmxServiceUrl) {
		this.jmxServiceUrl = jmxServiceUrl;
	}
	public JMXConnector getJmxConnector() {
		return jmxConnector;
	}
	public void setJmxConnector(JMXConnector jmxConnector) {
		this.jmxConnector = jmxConnector;
	}
	public MBeanServerConnection getMbsConnection() {
		return mbsConnection;
	}
	public void setMbsConnection(MBeanServerConnection mbsConnection) {
		this.mbsConnection = mbsConnection;
	}
	public String getObjectNamePrefix() {
		return objectNamePrefix;
	}
	public void setObjectNamePrefix(String objectNamePrefix) {
		this.objectNamePrefix = objectNamePrefix;
	}
	public String getJmxUserName() {		
		return JmxUserName;
	}
	public void setJmxUserName(String JmxUserName) {
		this.JmxUserName = JmxUserName;
	}
	public String getJmxPassword() {		
		return JmxPassword;
	}
	public void setJmxPassword(String JmxPassword) {
		this.JmxPassword = JmxPassword;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String Environment) {
		this.environment = Environment;
	}
	public Boolean getThreads() {
		return threads;
	}
	public void setThreads(Boolean Threads) {
		this.threads = Threads;
	}
	public Boolean getAppState() {
		return appState;
	}
	public void setAppState(Boolean AppState) {
		this.appState = AppState;
	}
	public Boolean getJVM() {
		return jvm;
	}
	public void setJVM(Boolean JVM) {
		this.jvm = JVM;
	}
	public Boolean getWLServer() {
		return wlServer;
	}
	public void setWLServer(Boolean WLServer) {
		this.wlServer = WLServer;
	}
	public Boolean getCluster() {
		return cluster;
	}
	public void setCluster(Boolean Cluster) {
		this.cluster = Cluster;
	}
	public Boolean getTransaction() {
		return transaction;
	}
	public void setTransaction(Boolean Transaction) {
		this.transaction = Transaction;
	}
	public Boolean getFileStore() {
		return fileStore;
	}
	public void setFileStore(Boolean FileStore) {
		this.fileStore = FileStore;
	}
	public Boolean getJMS() {
		return jms;
	}
	public void setJMS(Boolean JMS) {
		this.jms = JMS;
	}
	public Boolean getAppData() {
		return appData;
	}
	public void setAppData(Boolean AppData) {
		this.appData = AppData;
	}
	public Boolean getJDBC() {
		return jdbc;
	}
	public void setJDBC(Boolean JDBC) {
		this.jdbc = JDBC;
	}
	public Boolean getEJB() {
		return ejb;
	}
	public void setEJB(Boolean EJB) {
		this.ejb = EJB;
	}	
	public String getAppDeployedSingle() {
		return appDeployedSingle;
	}
	public void setAppDeployedSingle(String AppDeployedSingle) {
		this.appDeployedSingle = AppDeployedSingle;
	}	
	public String getAppDataMonitor() {
		return appDataMonitor;
	}
	public void setAppDataMonitor(String AppDataMonitor) {
		this.appDataMonitor = AppDataMonitor;
	}
	public String getejbDataMonitor() {
		return ejbDataMonitor;
	}
	public void setejbDataMonitor(String ejbDataMonitor) {
		this.ejbDataMonitor = ejbDataMonitor;
	}
	public String getAppStateDeployed() {
		return appStateDeployed;
	}
	public void setAppStateDeployed(String AppStateDeployed) {
		this.appStateDeployed = AppStateDeployed;
	}
	public String getsafAgents() {
		return safAgents;
	}
	public void setsafAgents(String safAgents) {
		this.safAgents = safAgents;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String AdminName) {
		this.adminName = AdminName;
	}
	
	public String getJMSMods() {
		return JMSMods;
	}
	public void setJMSMods(String JMSMods) {
		this.JMSMods = JMSMods;
	}
	public String getJMSQueue() {
		return JMSQueue;
	}
	public void setJMSQueue(String JMSQueue) {
		this.JMSQueue = JMSQueue;
	}
}
