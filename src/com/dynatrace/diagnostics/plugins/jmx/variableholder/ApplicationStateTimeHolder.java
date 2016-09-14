package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class ApplicationStateTimeHolder {

    private ArrayList<ApplicationStateStats> holder = new ArrayList<ApplicationStateStats>();

    public synchronized ArrayList<ApplicationStateStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ApplicationStateStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ApplicationStateStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ApplicationStateStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
