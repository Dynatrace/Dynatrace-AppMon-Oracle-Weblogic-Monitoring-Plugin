package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class WLServerStats {

    public WLServerStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getOpenSocketsCurrentCount() {
        return OpenSocketsCurrentCount;
    }

    public void setOpenSocketsCurrentCount(Integer OpenSocketsCurrentCount) {
        this.OpenSocketsCurrentCount = OpenSocketsCurrentCount;
    }

    public Integer gethealthState() {
        return healthState;
    }

    public void sethealthState(Integer healthState) {
        this.healthState = healthState;
    }

    public Integer getListenAddress() {
        return ListenAddress;
    }

    public void setListenAddress(Integer ListenAddress) {
        this.ListenAddress = ListenAddress;
    }

    public Long getActivationTime() {
        return ActivationTime;
    }

    public void setActivationTime(Long ActivationTime) {
        this.ActivationTime = ActivationTime;
    }

    public Integer getserverState() {
        return serverState;
    }

    public void setserverState(Integer serverState) {
        this.serverState = serverState;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    String serverName = null;
    Integer serverState;
    Integer OpenSocketsCurrentCount;
    Integer healthState;
    Integer ListenAddress;
    Long ActivationTime;
    String Enviro;
}
