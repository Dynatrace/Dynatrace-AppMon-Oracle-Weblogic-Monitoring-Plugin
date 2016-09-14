package com.dynatrace.diagnostics.plugins.jmx.convertdata;

import java.text.*;
import java.util.*;

public class ConvertToDateTime {

    String s;
    Format formatter;

    public String ConvertDateTime(Long str) {
        Date epoch = new Date(str);
        formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        s = formatter.format(epoch);
        return s;
    }
}
