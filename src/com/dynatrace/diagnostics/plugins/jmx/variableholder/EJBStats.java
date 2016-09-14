package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class EJBStats {

    public EJBStats() {
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

    public Long gettctc() {
        return tctc;
    }

    public void settctc(Long tctc) {
        this.tctc = tctc;
    }

    public Long gettrbc() {
        return trbc;
    }

    public void settrbc(Long trbc) {
        this.trbc = trbc;
    }

    public Long getttoc() {
        return ttoc;
    }

    public void setttoc(Long ttoc) {
        this.ttoc = ttoc;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    public String getejbName() {
        return ejbName;
    }

    public void setejbName(String ejbName) {
        this.ejbName = ejbName;
    }    
    
    String serverName = null;
    String appName;
    Long tctc;
    Long trbc;
    Long ttoc;
    String Enviro;
    String ejbName;
}
