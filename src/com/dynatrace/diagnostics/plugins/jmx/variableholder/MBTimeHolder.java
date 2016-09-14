package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class MBTimeHolder {

	private ArrayList<MBStats> holder = new ArrayList<MBStats>();

    public synchronized ArrayList<MBStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<MBStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addMBStats(MBStats parm) {
        holder.add(parm);
    }

    public synchronized void addMBStatsList(ArrayList<MBStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
