package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class ThreadWorkmanagerTimeHolder {

    private ArrayList<ThreadWorkmanagerStats> holder = new ArrayList<ThreadWorkmanagerStats>();

    public synchronized ArrayList<ThreadWorkmanagerStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ThreadWorkmanagerStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ThreadWorkmanagerStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ThreadWorkmanagerStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
