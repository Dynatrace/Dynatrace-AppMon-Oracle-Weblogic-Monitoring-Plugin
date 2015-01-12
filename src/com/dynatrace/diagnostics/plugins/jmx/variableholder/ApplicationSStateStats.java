package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ApplicationSStateStats {
	public ApplicationSStateStats() {
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
		    
	    public String getEnviro() {
	        return Enviro;
	    }

	    public void setEnviro(String Enviro) {
	        this.Enviro = Enviro;
	    }	

	    public Integer getsingleDeploy() {
	        return singleDeploy;
	    }

	    public void setsingleDeploy(Integer singleDeploy) {
	        this.singleDeploy = singleDeploy;
	    }
	    
	    String appName;
	    String serverName = null;	
	    String Enviro;
	    Integer singleDeploy;

}
