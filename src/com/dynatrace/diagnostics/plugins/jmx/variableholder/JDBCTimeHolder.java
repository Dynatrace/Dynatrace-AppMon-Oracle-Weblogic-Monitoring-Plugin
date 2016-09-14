package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class JDBCTimeHolder {

    private ArrayList<JDBCStats> holder = new ArrayList<JDBCStats>();

    public synchronized ArrayList<JDBCStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<JDBCStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(JDBCStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<JDBCStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}