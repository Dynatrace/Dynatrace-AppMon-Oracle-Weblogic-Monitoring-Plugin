package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class WLServerTimeHolder {

    private ArrayList<WLServerStats> holder = new ArrayList<WLServerStats>();

    public synchronized ArrayList<WLServerStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<WLServerStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(WLServerStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<WLServerStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
