package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class TransactionTimeHolder {

    private ArrayList<TransactionStats> holder = new ArrayList<TransactionStats>();

    public synchronized ArrayList<TransactionStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<TransactionStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(TransactionStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<TransactionStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}

