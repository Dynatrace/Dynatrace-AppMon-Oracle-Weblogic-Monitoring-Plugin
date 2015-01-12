package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class FileStoreTimeHolder {

    private ArrayList<FileStoreStats> holder = new ArrayList<FileStoreStats>();

    public synchronized ArrayList<FileStoreStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<FileStoreStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(FileStoreStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<FileStoreStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
