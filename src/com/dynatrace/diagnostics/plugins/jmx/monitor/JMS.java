package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JMSStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JMSTimeHolder;

import java.util.ArrayList;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WeblogicConstants;
import com.dynatrace.diagnostics.plugins.jmx.convertdata.DateTime;
import com.dynatrace.diagnostics.plugins.jmx.convertdata.JMSMessageTime;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.concurrent.Callable;


public class JMS implements Callable<ArrayList<JMSStats>>, WeblogicConstants {

	
	private ArrayList<JMSStats> test;
	
	public JMS(ArrayList<JMSStats> test){
        this.test = test;
	}
	
	JMSTimeHolder ad = new JMSTimeHolder();

	private static final Logger log = Logger.getLogger(JMS.class.getName());
    private String name;
    private Set<ObjectInstance> mbeans;
    private Iterator<ObjectInstance> iter;
    private ObjectName[] destRTT = null;
    private String JMSName = "";
    private String curs;
    private ArrayList<JMSStats> statsList = new ArrayList<JMSStats>();
    
    MBeanServerConnection connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String [] safAgents;
    String [] mods;
    String [] queue;
   

    public JMS(ObjectName destR, MBeanServerConnection connection, MonitorEnvironment env, String environment, String[] jmsMod, String[] jmsQueue) {
    	envs = env;
        destRT = destR;
        connections = connection;
        environments=environment;
        mods=jmsMod;
        queue=jmsQueue;
    }

    @Override
    public ArrayList<JMSStats> call() throws Exception {
        try {
            name = (String) connections.getAttribute(destRT, "Name");
        } catch (Exception e) {
            log.info("" + e);
        }
        
        try {
            mbeans = connections.queryMBeans(new ObjectName("*:Location=" + name + ",Type=JMSServerRuntime,*"), null);
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("JMS error: " + e);
        	}
            
        }

        iter = mbeans.iterator();

        while (iter.hasNext()) {
            String jms = (iter.next().getObjectName().getCanonicalKeyPropertyListString());
            int intIndex = -1;
            if (intIndex == -1) {
                try {
                    destRTT = (ObjectName[]) connections.getAttribute(new ObjectName("com.bea:" + jms), "Destinations");

                } catch (Exception e) {
                	if (log.isLoggable(Level.WARNING)) {
                		log.warning("JMS error: " + e);
                	}
                    
                }
                int appLength = (int) destRTT.length;
                int pc = 1;
                boolean a;
                int val = 0;
                for (int x = 0; x < appLength; x++) {
                	pc = 1;
                    try {
                        if (destRTT[x] == null) {
                            break;
                        }
                        JMSName = (String) connections.getAttribute(destRTT[x], "Name");
                        try {
                        	val = mods.length;
                        } catch(Exception e){
                        	val = 0;
                        	continue;
                        }
                        int z = 0;            	
                    	while (z < val){
                    		String pattern = ".*" + mods[z].toString() + ".*";
                            a = Pattern.matches(pattern, JMSName);
                    		if (a){
                    			pc = 0;
                    			break;
                    		}else {
                    			//Do nothing.
                    		}
                    		z++;
                    	}
                    	try {
                    		val = queue.length;
                    	} catch(Exception e){
                        	val = 0;
                        	continue;
                        }
                        z = 0;            	
                    	while (z < val && pc == 1){
                    		String pattern = ".*" + queue[z].toString() + ".*";
                            a = Pattern.matches(pattern, JMSName);
                    		if (a){
                    			pc = 0;
                    			break;
                    		}else {
                    			//Do nothing.
                    		}
                    		z++;
                    	} 
                        if(pc == 1){
                        	JMSStats stats = new JMSStats();
                        	
                        	stats.setserverName(name);
                        	stats.setEnviro(environments);
                        	stats.setjmsName(JMSName);
                        
                        	Long MessC = (Long) connections.getAttribute(destRTT[x], "MessagesCurrentCount");
                        	stats.setMessagesCurrentCount(MessC);
                        
                        	Long MessP = (Long) connections.getAttribute(destRTT[x], "MessagesPendingCount");
                        	stats.setMessagesPendingCount(MessP);
                        
                        	Long MessHighCount = (Long) connections.getAttribute(destRTT[x], "MessagesHighCount");
                        	stats.setMessagesHighCount(MessHighCount);
                        
                        	Long MessageTotalCount = (Long) connections.getAttribute(destRTT[x], "MessagesReceivedCount");
                        	stats.setMessageTotalCount(MessageTotalCount);
                        
                        	Long ConsumerCurrent = (Long) connections.getAttribute(destRTT[x], "ConsumersCurrentCount");
                        	stats.setConsumerCurrent(ConsumerCurrent);
                        
                        	int hi = 0;
                        	//This will error with a topic message..
                        	if (hi < MessC || hi < MessP) {
                        		try{
                        			curs = (String) connections.invoke(destRTT[x], "getMessages", new Object[]{null, new Integer(0)}, new String[]{"java.lang.String", "java.lang.Integer"});
                        			long timeDate = DateTime.getDateTime();
                        			while (hi < MessC || hi < MessP && hi < 1) {
                            			CompositeData[] curs4 = (CompositeData[]) connections.invoke(destRTT[x], "getNext", new Object[]{curs, new Integer(1)}, new String[]{"java.lang.String", "java.lang.Integer"});
                            			Collection<?> col = curs4[0].values();
                            			String test = col.toString();
                            			//Find the info we want :)
                            			int begin = test.indexOf("<mes:JMSTimestamp>") + "<mes:JMSTimestamp>".length();
                            			int end = test.indexOf("</mes:JMSTimestamp>");
                            			test = test.substring(begin, end);
                            			long comp = Long.parseLong(test);
                            			long totalMin = JMSMessageTime.getMessageTime(timeDate, comp);
                            			stats.setmessageAge(totalMin);
                            			hi = hi + 1;
                                    	break;
                        			}
                        			
                        		} catch(Exception e){
                        			//Messages Processed Before we could get the age.
                        			long age = 0;
                        			stats.setmessageAge(age);
                        			continue;
                        		}
                            	
                        	} else {
                        		//Set Message age to 0 and record.
                        		long l = 0;
                        		stats.setmessageAge(l);
                        	}
                        	statsList.add(stats);
                        	ad.addServerStatsList(statsList);
                            statsList.clear();
                        }
                    } catch (ReflectionException e) {
                    	if (log.isLoggable(Level.WARNING)) {
                    		log.warning("Reflection Exception: " + e.getCause());
                            log.warning("Reflection Exception: " + e.getLocalizedMessage());
                            log.warning("Reflection Exception: " + e.getStackTrace().toString());
                            log.warning("Reflection Exception: " + e.getTargetException());
                    	}
                    } catch (NullPointerException e) {
                    	if (log.isLoggable(Level.WARNING)) {
                    		log.warning("" + e);
                    	}
                    } catch (Exception e) {
                    	if (log.isLoggable(Level.INFO)) {
                    		log.info("Exception in JMS: " + e);
                    	}
                    }
                }
            }
        }
        test = ad.getArrayList();
		return test;
    }
}
