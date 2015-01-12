package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class ClusterTimeHolder {

    private ArrayList<ClusterStats> holder = new ArrayList<ClusterStats>();

    public synchronized ArrayList<ClusterStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ClusterStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ClusterStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ClusterStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
