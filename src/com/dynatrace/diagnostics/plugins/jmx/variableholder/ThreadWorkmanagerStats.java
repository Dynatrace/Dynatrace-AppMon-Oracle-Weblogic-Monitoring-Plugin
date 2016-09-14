package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ThreadWorkmanagerStats {

    public ThreadWorkmanagerStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public String gettimeDateConverted() {
        return timeDateConverted;
    }

    public void settimeDateConverted(String timeDateConverted) {
        this.timeDateConverted = timeDateConverted;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getWorkManager() {
        return WorkManager;
    }

    public void setWorkManager(String WorkManager) {
        this.WorkManager = WorkManager;
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String Application) {
        this.Application = Application;
    }

    public String getModName() {
        return ModName;
    }

    public void setModName(String ModName) {
        this.ModName = ModName;
    }

    public Integer getTotalThreads() {
        return TotalThreads;
    }

    public void setTotalThreads(Integer TotalThreads) {
        this.TotalThreads = TotalThreads;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    String WorkManager;
    String serverName = null;
    String Application;
    String ModName;
    Integer TotalThreads;
    String timeDateConverted;
    String Enviro;
}

