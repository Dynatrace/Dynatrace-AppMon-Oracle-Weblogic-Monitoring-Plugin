package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ApplicationStateStats {

	public ApplicationStateStats() {
        super();
    }

	public String getserverName() {
	        return serverName;
	 }

	 public void setserverName(String serverName) {
	        this.serverName = serverName;
	 }
		public String getCompName() {
	        return CompName;
	 }

	 public void setCompName(String CompName) {
	        this.CompName = CompName;
	 }	    
	 public String getappName() {
		    return appName;
	 }

	 public void setappName(String appName) {
		    this.appName = appName;
	 }
		    
	    public String getEnviro() {
	        return Enviro;
	    }

	    public void setEnviro(String Enviro) {
	        this.Enviro = Enviro;
	    }	
	    public Integer getappState() {
	        return appState;
	    }

	    public void setappState(Integer appState) {
	        this.appState = appState;
	    }
	    public Long getappHealth() {
	        return appHealth;
	    }

	    public void setappHealth(Long appHealth) {
	        this.appHealth = appHealth;
	    }
	    
	    String appName;
	    String serverName = null;	
	    String Enviro;
	    Integer appState;
	    Long appHealth;
	    String CompName;
}
