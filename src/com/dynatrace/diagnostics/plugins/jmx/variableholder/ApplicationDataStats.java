package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ApplicationDataStats {

    public ApplicationDataStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getappName() {
        return appName;
    }

    public void setappName(String appName) {
        this.appName = appName;
    }

    public Long getOpenSessionsCurrentCount() {
        return OpenSessionsCurrentCount;
    }

    public void setOpenSessionsCurrentCount(Long OpenSessionsCurrentCount) {
        this.OpenSessionsCurrentCount = OpenSessionsCurrentCount;
    }

    public Long getOpenSessionsHighCount() {
        return OpenSessionsHighCount;
    }

    public void setOpenSessionsHighCount(Long OpenSessionsHighCount) {
        this.OpenSessionsHighCount = OpenSessionsHighCount;
    }

    public Long getSessionsOpenedTotalCount() {
        return SessionsOpenedTotalCount;
    }

    public void setSessionsOpenedTotalCount(Long SessionsOpenedTotalCount) {
        this.SessionsOpenedTotalCount = SessionsOpenedTotalCount;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    public String getCompRuntime() {
        return CompRuntime;
    }

    public void setCompRuntime(String CompRuntime) {
        this.CompRuntime = CompRuntime;
    }    
    
    String serverName = null;
    String appName;
    Long OpenSessionsCurrentCount;
    Long OpenSessionsHighCount;
    Long SessionsOpenedTotalCount;
    String Enviro;
    String CompRuntime;
}
