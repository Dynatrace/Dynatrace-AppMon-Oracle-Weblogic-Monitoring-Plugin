package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class SAFTimeHolder {
	
	private ArrayList<SAFStats> holder = new ArrayList<SAFStats>();

    public synchronized ArrayList<SAFStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<SAFStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addSAFStats(SAFStats parm) {
        holder.add(parm);
    }

    public synchronized void addSAFStatsList(ArrayList<SAFStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
