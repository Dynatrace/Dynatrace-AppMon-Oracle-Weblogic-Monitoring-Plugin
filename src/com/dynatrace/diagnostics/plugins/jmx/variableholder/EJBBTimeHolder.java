package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.ArrayList;

public class EJBBTimeHolder {
    
	private ArrayList<EJBBStats> holder = new ArrayList<EJBBStats>();

    public synchronized ArrayList<EJBBStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<EJBBStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addEJBBStats(EJBBStats parm) {
        holder.add(parm);
    }

    public synchronized void addEJBBStatsList(ArrayList<EJBBStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}