package com.dynatrace.diagnostics.plugins.jmx.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;



import java.util.logging.Level;
import java.util.logging.Logger;


public class WeblogicConnection {

	private static final Logger log = Logger.getLogger(WeblogicConnection.class.getName());
	private MBeanServerConnection connection;
    private JMXConnector connector;

    public MBeanServerConnection initConnection(String hostname, String portString, String username, String password, Boolean sslMonitor, String sslPath) throws IOException, MalformedURLException {
    	String protocol = null;
    	if(sslMonitor){
    		System.setProperty("weblogic.security.CustomTrustKeyStoreFileName", sslPath);
    		System.setProperty("weblogic.security.TrustKeyStore","CustomTrust");
    		System.setProperty("weblogic.security.SSL.ignoreHostnameVerification","true");
    		protocol = "t3s";
    	} else{
    		protocol = "t3";
    	}
        
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        
        String jndiroot = "/jndi/";
        String mserver;
        
        mserver = "weblogic.management.mbeanservers.domainruntime";

        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
        HashMap<String, String> h = new HashMap<String, String>();
        long requestTimeout = 1000;
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
        //If the MBean server is unable to complete an invocation, the JMX client will hang indefinitely. This is to prevent this from happening.
        //Value is in ms
        h.put("jmx.remote.x.request.waiting.timeout", Long.toString(requestTimeout));
        try {
            connector = JMXConnectorFactory.connect(serviceURL, h);
        } catch (Exception e) {
        		log.warning("ConnectionFailure - Validate Admin server is running and connection details are correct: "
                    + protocol + " " + hostname + " " + port + " " + username + ". Error: " + e);
        		if(sslMonitor){
        			log.warning("If you are using ssl, verify you have the correct path: " + sslPath + ". Verify Weblogic is configured with Custom "
        				+ "Identity and Custom Trust and host name verification is turned off. Also, validate you are loading (in this application) "
        				+ "the Custom Trust Keystore. This application has only been tested with Keystore Type: JKS. Also, verify you are trying to connect"
        				+ " to the correct SSL port. For help on keystores, Oracle"
        				+ " has a nice write up: https://docs.oracle.com/cd/E19509-01/820-3503/6nf1il6er/index.html. Another nice site on how to configure"
        				+ " Weblogic: https://blogs.oracle.com/blogbypuneeth/entry/steps_to_create_a_self");
        		}
             return null;
        }
        try {
        	connection = connector.getMBeanServerConnection();
        } catch (Exception p) {
        	return null;
        } 
        
        
        if (log.isLoggable(Level.INFO)){
            log.info("Returning Weblogic Connection");
        }
        return connection;
    }
    public void closeConnection() throws IOException {
    	if (connector != null) {
    		try{
    		connector.close();
    		}catch (Exception e){
    			log.warning("Error closing connection: "+ e);
    		}
    		connector=null;
    	}
    }
}