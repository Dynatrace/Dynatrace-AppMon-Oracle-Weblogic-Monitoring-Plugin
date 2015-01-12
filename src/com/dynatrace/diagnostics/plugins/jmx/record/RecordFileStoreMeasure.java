package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.FileStoreStats;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordFileStoreMeasure implements WeblogicConstants {
	private static final Logger log = Logger.getLogger(RecordFileStoreMeasure.class.getName());
	String measureName;
	public void WriteToDT (MonitorEnvironment envs, ArrayList<FileStoreStats> ad)  throws Exception {
		
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<FileStoreStats> list = ad;
            for (FileStoreStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String StoreName = fromStatic.getStoreName();
                Long CreateCount = fromStatic.getCreateCount();
                Long DeleteCount = fromStatic.getDeleteCount();
                Long PhysicalWriteCount = fromStatic.getPhysicalWriteCount();
                Long ReadCount = fromStatic.getReadCount();
                Long UpdateCount = fromStatic.getUpdateCount();
                String Enviro = fromStatic.getEnviro();
                
                measureName = WLServerName+"|FileStore|" + StoreName +"|CreateCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_FILESTORE_GROUP, WEBLOGIC_CREATECOUNT_METRIC, measureName, (double)CreateCount);
                
                measureName = WLServerName+"|FileStore|" + StoreName +"|DeleteCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_FILESTORE_GROUP, WEBLOGIC_DELETECOUNT_METRIC, measureName, (double)DeleteCount);
                
                measureName = WLServerName+"|FileStore|" + StoreName +"|PhysicalWriteCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_FILESTORE_GROUP, WEBLOGIC_PHYSICALWRITECOUNT_METRIC, measureName, (double)PhysicalWriteCount);
                
                measureName = WLServerName+"|FileStore|" + StoreName +"|ReadCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_FILESTORE_GROUP, WEBLOGIC_READCOUNT_METRIC, measureName, (double)ReadCount);
                
                measureName = WLServerName+"|FileStore|" + StoreName +"|UpdateCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBLOGIC_FILESTORE_GROUP, WEBLOGIC_UPDATECOUNT_METRIC, measureName, (double)UpdateCount);
                
            }
		
		} catch (Exception e) {
            log.info("Record File Store Data: " + e);
        } 
	}
}
