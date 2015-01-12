package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class JVMTimeHolder {

    private ArrayList<JVMServerStats> holder = new ArrayList<JVMServerStats>();

    public synchronized ArrayList<JVMServerStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<JVMServerStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(JVMServerStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<JVMServerStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
