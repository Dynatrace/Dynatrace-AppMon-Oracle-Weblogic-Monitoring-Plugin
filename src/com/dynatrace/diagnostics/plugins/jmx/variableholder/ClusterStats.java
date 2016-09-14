package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ClusterStats {

    public ClusterStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getclusterName() {
        return clusterName;
    }

    public void setclusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Long getMultiLostCount() {
        return MultiLostCount;
    }

    public void setMultiLostCount(Long MultiLostCount) {
        this.MultiLostCount = MultiLostCount;
    }

    public Integer getServerAliveCount() {
        return ServerAliveCount;
    }

    public void setServerAliveCount(Integer ServerAliveCount) {
        this.ServerAliveCount = ServerAliveCount;
    }

    public String getCurrentMachine() {
        return CurrentMachine;
    }

    public void setCurrentMachine(String CurrentMachine) {
        this.CurrentMachine = CurrentMachine;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    String clusterName;
    String serverName = null;
    String CurrentMachine;
    Long MultiLostCount;
    Integer ServerAliveCount;
    String Enviro;

}

