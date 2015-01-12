package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class JMSTimeHolder {

    private ArrayList<JMSStats> holder = new ArrayList<JMSStats>();

    public synchronized ArrayList<JMSStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<JMSStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(JMSStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<JMSStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}