package com.dynatrace.diagnostics.plugins.jmx.convertdata;

import java.util.*;

public class DateTime {

    public static long getDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.getTimeInMillis();
        return cal.getTimeInMillis();
    }

}
