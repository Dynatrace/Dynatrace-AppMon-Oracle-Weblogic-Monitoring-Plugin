package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JMSStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordJMSMeasure implements WeblogicConstants {
	private static final Logger log = Logger.getLogger(RecordJVMMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<JMSStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
            ArrayList<JMSStats> list = ad;
            for (JMSStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String nameJMS = fromStatic.getjmsName();
                Long MessagesCurrentCount = fromStatic.getMessagesCurrentCount();
                Long MessagesPendingCount = fromStatic.getMessagesPendingCount();
                Long MessagesHighCount = fromStatic.getMessagesHighCount();
                Long MessageTotalCount = fromStatic.getMessageTotalCount();
                Long ConsumerCurrent = fromStatic.getConsumerCurrent();
                String Enviro = fromStatic.getEnviro();
                Long mesageAge = fromStatic.getmessageAge();
                
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|MessagesCurrentCount|"+Enviro;
            	testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_MESSAGESCURRENT_METRIC, measureName, (double)MessagesCurrentCount);
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|MessagesPendingCount|"+Enviro;
            	testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_MESSAGESPENDING_METRIC, measureName, (double)MessagesPendingCount);
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|MessagesHighCount|"+Enviro;
            	testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_MESSAGESHIGH_METRIC, measureName, (double)MessagesHighCount);
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|MessagesReceivedCount|"+Enviro;
            	testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_MESSAGESRECIEVED_METRIC, measureName, (double)MessageTotalCount);
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|ConsumersCurrentCount|"+Enviro;
            	testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_CONSUMERCURRENT_METRIC, measureName, (double)ConsumerCurrent);
                
                measureName = WLServerName+"|JMS|" + nameJMS +"|OldestMessageAge|"+Enviro;
        		testing.populateDynamicMeasure(envs, WEBLOGIC_JMS_GROUP, WEBLOGIC_OLDESTMESSAGEAGE_METRIC, measureName, (double)mesageAge);
                
            }
		
        } catch (Exception e) {
            log.warning("Record JMS data: " + e);
        } 
	}
}
