package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.SAFStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordSAFMeasure implements WeblogicConstants {
	
	private static final Logger log = Logger.getLogger(RecordSAFMeasure.class.getName());
	
	
	public void WriteToDT (MonitorEnvironment envs,ArrayList<SAFStats> ad)  throws Exception {
		
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<SAFStats> list = ad;
            for (SAFStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                String mbName = fromStatic.getSAFAgent();
                Long FailedMessagesTotal= fromStatic.getFailedMessagesTotal();
                Long MessagesCurrentCount= fromStatic.getMessagesCurrentCount();
                Long MessagesPendingCount= fromStatic.getMessagesPendingCount();
                Long MessagesHighCount= fromStatic.getMessagesHighCount();
                Long RemoteEndpointsCurrentCount= fromStatic.getRemoteEndpointsCurrentCount();
                Long RemoteEndpointsHighCount= fromStatic.getRemoteEndpointsHighCount();
                Long RemoteEndpointsTotalCount= fromStatic.getRemoteEndpointsTotalCount();
                Long ConversationsCurrentCount = fromStatic.getConversationsCurrentCount();
                Long ConversationsHighCount = fromStatic.getConversationsHighCount();
                Long ConversationsTotalCount = fromStatic.getConversationsTotalCount();
                Integer HealthState= fromStatic.getHealthState();
                Integer PausedForForwarding= fromStatic.getPausedForForwarding();
                Integer PausedForIncoming= fromStatic.getPausedForIncoming();
                Integer PausedForReceiving= fromStatic.getPausedForReceiving();
               
                String measureName = WLServerName + "|SAF|" + mbName + "|FailedMessagesTotal|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFFAILEDMESSAGETOTAL_METRIC, measureName, (double)FailedMessagesTotal);
                
                measureName = WLServerName + "|SAF|" + mbName + "|ConversationsCurrentCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFCONVERSATIONSCURRENT_METRIC, measureName, (double)ConversationsCurrentCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|ConversationsHighCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFCONVERSATIONSHIGH_METRIC, measureName, (double)ConversationsHighCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|ConversationsTotalCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFCONVERSATIONSTOTAL_METRIC, measureName, (double)ConversationsTotalCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|MessagesCurrentCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFMESSAGECURRENTCOUNT_METRIC, measureName, (double)MessagesCurrentCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|MessagesPendingCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFMESSAGEPENDINGCOUNT_METRIC, measureName, (double)MessagesPendingCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|MessagesHighCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFMESSAGEHIGHCOUNT_METRIC, measureName, (double)MessagesHighCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|RemoteEndpointsCurrentCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFREMOTEENDPOINTSCURRENT_METRIC, measureName, (double)RemoteEndpointsCurrentCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|RemoteEndpointsHighCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFREMOTEENDPOINTSHIGH_METRIC, measureName, (double)RemoteEndpointsHighCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|RemoteEndpointsTotalCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFREMOTEENDPOINTSTOTAL_METRIC, measureName, (double)RemoteEndpointsTotalCount);
                
                measureName = WLServerName + "|SAF|" + mbName + "|HealthState|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFHEALTHSTATE_METRIC, measureName, (double)HealthState);
                
                measureName = WLServerName + "|SAF|" + mbName + "|PausedForForwarding|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFPAUSEDFORWARD_METRIC, measureName, (double)PausedForForwarding);
                
                measureName = WLServerName + "|SAF|" + mbName + "|PausedForIncoming|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFPAUSEDINCOMING_METRIC, measureName, (double)PausedForIncoming);
                
                measureName = WLServerName + "|SAF|" + mbName + "|PausedForReceiving|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_SAF_GROUP, WEBLOGIC_SAFPAUSEDRECIEVING_METRIC, measureName, (double)PausedForReceiving);
            }
		} catch (Exception e) {
            log.warning("Record RecordSAFMeasure data: " + e);
        } 		
	}	
}
