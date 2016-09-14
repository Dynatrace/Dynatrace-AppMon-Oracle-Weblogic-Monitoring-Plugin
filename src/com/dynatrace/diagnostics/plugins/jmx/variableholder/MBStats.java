package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class MBStats {
	
    public MBStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getmessageBridge() {
        return messageBridge;
    }

    public void setmessageBridge(String messageBridge) {
        this.messageBridge = messageBridge;
    }
    
    public Integer getstate() {
        return state;
    }

    public void setstate(Integer state) {
        this.state = state;
    }

    public Integer getforward() {
        return forward;
    }

    public void setforward(Integer forward) {
        this.forward = forward;
    }
    
    
    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    
    String serverName = null;
    String messageBridge;
    Integer state;
    Integer forward;
    String Enviro;

}
