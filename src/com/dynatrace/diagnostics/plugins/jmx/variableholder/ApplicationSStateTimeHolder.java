package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class ApplicationSStateTimeHolder {

    private ArrayList<ApplicationSStateStats> holder = new ArrayList<ApplicationSStateStats>();

    public synchronized ArrayList<ApplicationSStateStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ApplicationSStateStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerSStats(ApplicationSStateStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerSStatsList(ArrayList<ApplicationSStateStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}