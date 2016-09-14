package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class EJBBStats {

    public EJBBStats() {
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

    public Integer getbiuc() {
        return biuc;
    }

    public void setbiuc(Integer biuc) {
        this.biuc = biuc;
    }

    public Integer getpbcc() {
        return pbcc;
    }

    public void setpbcc(Integer pbcc) {
        this.pbcc = pbcc;
    }

    public Integer getwcc() {
        return wcc;
    }

    public void setwcc(Integer wcc) {
        this.wcc = wcc;
    }
    public Long getttc() {
        return ttc;
    }

    public void setttc(Long ttc) {
        this.ttc = ttc;
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
    Integer biuc;
    Integer pbcc;
    Integer wcc;
    Long ttc;
    String Enviro;
    String ejbName;
}
