package com.dynatrace.diagnostics.plugins.jmx;

public interface WeblogicConstants {
	// Plugin's configuration parameter's constants
	public static final String CONFIG_DT_SERVER = "dtServer";
	public static final String CONFIG_JMX_PORT = "jmxPort";
/*	public static final String CONFIG_SYSTEM_PROFILE = "systemProfile";
	public static final String CONFIG_METRICS = "metrics";
	public static final String CONFIG_AGENT_GROUPS = "agentGroups";*/
	public static final String CONFIG_REGEX_APPDATA = "regexAppData";
	public static final String CONFIG_REGEX_APPSTATE = "regexAppState";
	public static final String CONFIG_WEBLOGIC_PASSWORD = "JmxPassword";
	public static final String CONFIG_WEBLOGIC_USERNAME = "JmxUserName";
	public static final String CONFIG_WEBLOGIC_ENVIRONMENT = "Environment";
	public static final String CONFIG_WEBLOGIC_SSL = "sslTrust";
	public static final String CONFIG_WEBLOGIC_SSLPATH = "customTrust";
	public static final String CONFIG_WEBLOGIC_THREADS = "Threads";
	public static final String CONFIG_WEBLOGIC_JVM = "JVM";
	public static final String CONFIG_WEBLOGIC_WLSERVER = "WLServer";
	public static final String CONFIG_WEBLOGIC_CLUSTER = "Cluster";
	public static final String CONFIG_WEBLOGIC_TRANSACTION = "Transaction";
	public static final String CONFIG_WEBLOGIC_FILESTORE = "FileStore";
	public static final String CONFIG_WEBLOGIC_JMS = "JMS";
	public static final String CONFIG_WEBLOGIC_SAFAGENT = "SAF";
	public static final String CONFIG_WEBLOGIC_APPLICATIONDATA = "ApplicationData";
	public static final String CONFIG_WEBLOGIC_EJBDATA = "EJBData";
	public static final String CONFIG_WEBLOGIC_APPLICATIONSTATE = "ApplicationState";
	public static final String CONFIG_WEBLOGIC_JDBC = "JDBC";
	public static final String CONFIG_WEBLOGIC_MDB = "MessageBridge";
	public static final String CONFIG_WEBLOGIC_MESSAGEBRIDGESERVER = "MessageBridgeServer";
	public static final String CONFIG_THREADCOUNT_MONITOR = "threadCount";
	public static final String CONFIG_WEBLOGIC_APPDEPLOYEDSINGLE = "serverDeploy";
	public static final String CONFIG_WEBLOGIC_APPDATAMONITOR = "AppDataMonitor";
	public static final String CONFIG_WEBLOGIC_APPSTATE = "AppStateMonitor";
	public static final String CONFIG_WEBLOGIC_SAF = "safAgents";
	public static final String CONFIG_WEBLOGIC_ADMINNAME = "adminName";
	public static final String CONFIG_WEBLOGIC_EJBDATAMONITOR = "EJBDataMonitor";
	public static final String CONFIG_WEBLOGIC_JMSMODS = "jmsMods";
	public static final String CONFIG_WEBLOGIC_JMSQUEUE = "jmsQueues";
	public static final String CONFIG_TIMEOUT_MONITOR = "metricTimeOut";
	
	// dynaTrace based metric groups
	public static final String JMX_MEASURE_SPLIT_NAME = "Measure Name";

	// Weblogic Connection Group
	public static final String WEBLOGIC_CONNECTION_GROUP = "Weblogic Connection Group";
	public static final String WEBLOGIC_CONNECTION_METRIC = "Connection Status";
	public static final String WEBLOGIC_MONITORTIME_METRIC = "Weblogic Monitor Time";
	
	// Message Bridge Group
	public static final String WEBLOGIC_MESSAGEBRIDGE_GROUP = "Weblogic Message Bridge Group";
	public static final String WEBLOGIC_STATE_METRIC = "Message Bridge Status";
	public static final String WEBLOGIC_FORWARD_METRIC = "Message Bridge Forwarding";
	
	// Weblogic Thread Group
	public static final String WEBLOGIC_THREAD_GROUP = "Weblogic Thread Group";
	public static final String WEBLOGIC_EXECUTE_THREAD_TOTAL_METRIC = "Execute Thread Total Counts";
	public static final String WEBLOGIC_EXECUTE_THREAD_IDLE_METRIC = "Execute Thread Idle Counts";
	public static final String WEBLOGIC_HOGGING_THREAD_METRIC = "Hogging Thread Counts";
	public static final String WEBLOGIC_QUEUE_LENGTH_METRIC = "Queue Lengths";
	public static final String WEBLOGIC_STANDBY_THREAD_METRIC = "Standby Thread Counts";
	public static final String WEBLOGIC_HEALTH_METRIC = "Thread Health";
	public static final String WEBLOGIC_COMPLETED_REQUEST_METRIC = "Completed Request Count";
	public static final String WEBLOGIC_THROUGHPUT_METRIC = "Present Throughput";
	public static final String WEBLOGIC_WORKMANAGER_METRIC = "Workmanager Thread Usage";
	
	// Weblogic Server Group
	public static final String WEBLOGIC_SERVER_GROUP = "Weblogic Server Group";	
	public static final String WEBLOGIC_OPENSOCKETSERVER_METRIC = "Open Sockets";
	public static final String WEBLOGIC_HEALTHSERVER_METRIC = "Server Health";
	public static final String WEBLOGIC_LISTENADDRESS_METRIC = "Listen Address";
	public static final String WEBLOGIC_SERVERCOUNT_METRIC = "Server Count";
	public static final String WEBLOGIC_SERVERRUNNINGSTATE_METRIC = "Server Running State";
	public static final String WEBLOGIC_RUNNING_METRIC = "Server Running Time";

	// Weblogic JDBC Group
	public static final String WEBLOGIC_JDBC_GROUP = "Weblogic JDBC Group";
	public static final String WEBLOGIC_ACTIVECONNECTIONHIGH_METRIC = "Active Connections High";
	public static final String WEBLOGIC_ACTIVECONNECTIONCURRENT_METRIC = "Active Connections Current";
	public static final String WEBLOGIC_FAILEDRESERVEREQUEST_METRIC = "Failed Reserve Request";
	public static final String WEBLOGIC_FAILURESTORECONNECT_METRIC = "Failures To Reconnect";
	public static final String WEBLOGIC_LEAKEDCONNECTION_METRIC = "Leaked Connection";
	public static final String WEBLOGIC_CURRENTCAPACITY_METRIC = "Current Capacity";
	public static final String WEBLOGIC_CURRENTCAPACITYHIGH_METRIC = "Current Capacity High";
	public static final String WEBLOGIC_CONNECTIONDELAYTIME_METRIC = "Connection Delay Time";
	public static final String WEBLOGIC_PREPSTMTCACHEHIT_METRIC = "Prep Stmt Cache Hit";
	public static final String WEBLOGIC_PREPSTMTCACHEMISS_METRIC = "Prep Stmt Cache Miss";
	public static final String WEBLOGIC_PREPSTMTCACHEDELETE_METRIC = "Prep Stmt Cache Delete";
	public static final String WEBLOGIC_JDBCSTATE_METRIC = "JDBC State";
	
	// Weblogic Cluster Group
	public static final String WEBLOGIC_CLUSTER_GROUP = "Weblogic Cluster Group";	
	public static final String WEBLOGIC_MULTICASTMESSAGESLOST_METRIC = "Multicast Messages Lost";
	public static final String WEBLOGIC_ALIVESERVERCOUNT_METRIC = "Alive Server Count";
	
	// Weblogic Transaction Group
	public static final String WEBLOGIC_TRANSACTION_GROUP = "Weblogic Transaction Group";	
	public static final String WEBLOGIC_ACTIVETRANSACTION_METRIC = "Active Transactions Total";
	public static final String WEBLOGIC_TRANSACTIONABANDONED_METRIC = "Transaction Abandoned Total";
	public static final String WEBLOGIC_TRANSACTIONCOMMITTED_METRIC = "Transaction Committed Total";
	public static final String WEBLOGIC_TRANSACTIONHEURISTICS_METRIC = "Transaction Heuristics Total";
	public static final String WEBLOGIC_TRANSACTIONROLLEDBACKAPP_METRIC = "Transaction RolledBack App Total";
	public static final String WEBLOGIC_TRANSACTIONROLLEDBACKRESOURCE_METRIC = "Transaction RolledBack Resource Total";
	public static final String WEBLOGIC_TRANSACTIONTROLLEDBACKSYSTEM_METRIC = "Transaction RolledBack System Total";
	public static final String WEBLOGIC_TRANSACTIONTROLLEDBACKTIMEOUT_METRIC = "Transaction RolledBack Timeout Total";
	public static final String WEBLOGIC_TRANSACTIONROLLEDBACK_METRIC = "Transaction RolledBack Total";
	public static final String WEBLOGIC_TRANSACTIONTOTAL_METRIC = "Transaction Total";
	
	// Weblogic FileStore Group
	public static final String WEBLOGIC_FILESTORE_GROUP = "Weblogic FileStore Group";	
	public static final String WEBLOGIC_CREATECOUNT_METRIC = "Create Count";
	public static final String WEBLOGIC_DELETECOUNT_METRIC = "Delete Count";
	public static final String WEBLOGIC_PHYSICALWRITECOUNT_METRIC = "Physical Write Count";
	public static final String WEBLOGIC_READCOUNT_METRIC = "Read Count";
	public static final String WEBLOGIC_UPDATECOUNT_METRIC = "Update Count";
	
	// Weblogic JMS Group
	public static final String WEBLOGIC_JMS_GROUP = "Weblogic JMS Group";	
	public static final String WEBLOGIC_MESSAGESCURRENT_METRIC = "Messages Current";
	public static final String WEBLOGIC_MESSAGESPENDING_METRIC = "Messages Pending";
	public static final String WEBLOGIC_MESSAGESHIGH_METRIC = "Messages High";
	public static final String WEBLOGIC_MESSAGESRECIEVED_METRIC = "Messages Received";
	public static final String WEBLOGIC_CONSUMERCURRENT_METRIC = "Consumers Current";
	public static final String WEBLOGIC_OLDESTMESSAGEAGE_METRIC = "Oldest Message Age";

	
	// Weblogic SAF Group
	public static final String WEBLOGIC_SAF_GROUP = "Weblogic SAF Group";	
	public static final String WEBLOGIC_SAFHEALTHSTATE_METRIC = "SAF Health State";
	public static final String WEBLOGIC_SAFFAILEDMESSAGETOTAL_METRIC = "SAF Failed Messages Total";
	public static final String WEBLOGIC_SAFMESSAGECURRENTCOUNT_METRIC = "SAF Messages Current Count";
	public static final String WEBLOGIC_SAFMESSAGEPENDINGCOUNT_METRIC = "SAF Messages Pending Count";
	public static final String WEBLOGIC_SAFMESSAGEHIGHCOUNT_METRIC = "SAF Messages High Count";
	public static final String WEBLOGIC_SAFPAUSEDFORWARD_METRIC = "SAF Paused For Forwarding";
	public static final String WEBLOGIC_SAFPAUSEDINCOMING_METRIC = "SAF Paused For Incoming";
	public static final String WEBLOGIC_SAFPAUSEDRECIEVING_METRIC = "SAF Paused For Receiving";
	public static final String WEBLOGIC_SAFREMOTEENDPOINTSCURRENT_METRIC = "SAF Remote Endpoints Current Count";
	public static final String WEBLOGIC_SAFREMOTEENDPOINTSHIGH_METRIC = "SAF Remote Endpoints High Count";
	public static final String WEBLOGIC_SAFREMOTEENDPOINTSTOTAL_METRIC = "SAF Remote Endpoints Total Count";
	public static final String WEBLOGIC_SAFCONVERSATIONSCURRENT_METRIC = "SAF Conversations Current Count";
	public static final String WEBLOGIC_SAFCONVERSATIONSHIGH_METRIC = "SAF Conversations High Count";
	public static final String WEBLOGIC_SAFCONVERSATIONSTOTAL_METRIC = "SAF Conversations Total Count";
	
	
	// Weblogic JVM Group
	public static final String WEBLOGIC_JVM_GROUP = "Weblogic JVM Group";	
	public static final String WEBLOGIC_HEAPSIZECURRENT_METRIC = "Heap Size Current";
	public static final String WEBLOGIC_HEAPFREECURRENT_METRIC = "Heap Free Current";
	public static final String WEBLOGIC_HEAPUSEDCURRENT_METRIC = "Heap Used Current";
	public static final String WEBLOGIC_NUMBEROFPROCESSORS_METRIC = "Number Of Processors";
	public static final String WEBLOGIC_ALLPROCESSORSAVERAGELOAD_METRIC = "All Processors Average Load";
	public static final String WEBLOGIC_TOTALGARBAGECOLLECTIONCOUNT_METRIC = "Total Garbage Collection Count";
	public static final String WEBLOGIC_TOTALGARBAGECOLLECTIONTIME_METRIC = "Total Garbage Collection Time";
	public static final String WEBLOGIC_JVMPROCESSORLOAD_METRIC = "JVM Processor Load";
	
	// Weblogic Application Data Group
	public static final String WEBLOGIC_APPLICATIONDATA_GROUP = "Weblogic Application Data Group";	
	public static final String WEBLOGIC_OPENSESSIONSCURRENT_METRIC = "Open Sessions Current";
	public static final String WEBLOGIC_OPENSESSIONSHIGH_METRIC = "Open Sessions High";
	public static final String WEBLOGIC_SESSIONSOPENEDTOTAL_METRIC = "Sessions Opened Total";
	
	// Weblogic Application State Group
	public static final String WEBLOGIC_APPLICATIONSTATE_GROUP = "Weblogic Application State Group";	
	public static final String WEBLOGIC_APPLICATIONHEALTH_METRIC = "Application Health";
	public static final String WEBLOGIC_APPLICATIONRUNSTATE_METRIC = "Application Run State";
	public static final String WEBLOGIC_APPLICATIONDEPLOYEDSINGLESTATE_METRIC = "Application Deployed Single State";
	
	// Weblogic EJB Data Group
	public static final String WEBLOGIC_EJBDATA_GROUP = "Weblogic EJB Data Group";	
	public static final String WEBLOGIC_TRANSACTIONSCOMMITTEDTOTAL_METRIC = "Transactions Committed Total";
	public static final String WEBLOGIC_TRANSACTIONSROLLEDBACKTOTAL_METRIC = "Transactions RolledBack Total";
	public static final String WEBLOGIC_TRANSACTIONSTIMEOUTTOTAL_METRIC = "Transactions TimedOut Total";
	public static final String WEBLOGIC_BEANSINUSECURRENT_METRIC = "Beans In Use Current";
	public static final String WEBLOGIC_POOLEDBEANSCURRENT_METRIC = "Pooled Beans Current";
	public static final String WEBLOGIC_WAITERCURRENT_METRIC = "Waiter Current";
	public static final String WEBLOGIC_TIMEOUTTOTAL_METRIC = "Timeout Total";

}
