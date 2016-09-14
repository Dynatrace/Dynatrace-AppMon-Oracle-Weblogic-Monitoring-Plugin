package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class ThreadTimeHolder {

    private ArrayList<ThreadStats> holder = new ArrayList<ThreadStats>();

    public synchronized ArrayList<ThreadStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ThreadStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ThreadStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ThreadStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
