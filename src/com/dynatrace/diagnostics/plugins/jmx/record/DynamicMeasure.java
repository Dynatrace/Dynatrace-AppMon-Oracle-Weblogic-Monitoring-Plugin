package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.pdk.MonitorMeasure;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;

public class DynamicMeasure implements WeblogicConstants{
	
	private static final Logger log = Logger.getLogger(DynamicMeasure.class.getName());
	
	public synchronized void populateDynamicMeasure(MonitorEnvironment env, String measureGroup, String baseMeasure, String measureName, Double d) {
		if (log.isLoggable(Level.INFO)) {
			log.info("Entering populateDynamicMeasure method");
		}
		Collection<MonitorMeasure> monitorMeasures = env.getMonitorMeasures(measureGroup, baseMeasure);
		for (MonitorMeasure subscribedMonitorMeasure : monitorMeasures) {
			MonitorMeasure dynamicMeasure = env.createDynamicMeasure(subscribedMonitorMeasure, JMX_MEASURE_SPLIT_NAME, measureName);
			dynamicMeasure.setValue(d);
			log.info("populateDynamicMeasure method: measure group: " + measureGroup + ". BaseMeasure: " + baseMeasure + ". Measure Nmae: " + measureName + ". Value is: " + d);
		}
		log.info("populateDynamicMeasure method done");
	}
}
