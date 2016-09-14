package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class ApplicationDataTimeHolder {

    private ArrayList<ApplicationDataStats> holder = new ArrayList<ApplicationDataStats>();

    public synchronized ArrayList<ApplicationDataStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ApplicationDataStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ApplicationDataStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ApplicationDataStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
