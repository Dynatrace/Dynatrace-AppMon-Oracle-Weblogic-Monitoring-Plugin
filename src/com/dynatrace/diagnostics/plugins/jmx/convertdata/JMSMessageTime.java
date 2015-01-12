package com.dynatrace.diagnostics.plugins.jmx.convertdata;

public class JMSMessageTime {

    public static Long getMessageTime(long x, long y) throws Exception {
        long min = x - y;
        long minTime = (min / 1000) / 60;
        return minTime;
    }
}
