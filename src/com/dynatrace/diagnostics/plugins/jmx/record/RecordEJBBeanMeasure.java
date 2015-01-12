package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.EJBBStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordEJBBeanMeasure implements WeblogicConstants {
	private static final Logger log = Logger.getLogger(RecordEJBBeanMeasure.class.getName());
	String measureName;
	
public void WriteToDT (MonitorEnvironment envs, ArrayList<EJBBStats> ad)  throws Exception {
	DynamicMeasure testing = new DynamicMeasure();

		try {
			for (EJBBStats fromStatic : ad) {
				String serverName = fromStatic.getserverName();
				String appName = fromStatic.getappName();
				String ejbName = fromStatic.getejbName();
				String Enviro = fromStatic.getEnviro();
				Long ttc = fromStatic.getttc();
				Integer wcc = fromStatic.getwcc();
				Integer pbcc = fromStatic.getpbcc();
				Integer biuc = fromStatic.getbiuc();
				
				
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|BeansInUseCurrentCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_BEANSINUSECURRENT_METRIC, measureName, (double)biuc);
    
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|PooledBeansCurrentCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_POOLEDBEANSCURRENT_METRIC, measureName, (double)pbcc);
    
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|WaiterCurrentCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_WAITERCURRENT_METRIC, measureName, (double)wcc);
			
				measureName = serverName+"|EJBData|" + appName + "|" + ejbName + "|TimeoutTotalCount|"+Enviro;
				testing.populateDynamicMeasure(envs, WEBLOGIC_EJBDATA_GROUP, WEBLOGIC_TIMEOUTTOTAL_METRIC, measureName, (double)ttc);
			
			}
			
		} catch (Exception e) {
		    log.warning("Record EJB Bean data: " + e);
		} 
	}		
}