package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class EJBTimeHolder {
    
	private ArrayList<EJBStats> holder = new ArrayList<EJBStats>();

    public synchronized ArrayList<EJBStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<EJBStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addEJBStats(EJBStats parm) {
        holder.add(parm);
    }

    public synchronized void addEJBStatsList(ArrayList<EJBStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
