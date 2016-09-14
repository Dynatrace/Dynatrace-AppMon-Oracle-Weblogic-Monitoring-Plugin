package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class SAFStats {
 
	public SAFStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }
    public void setserverName(String serverName) {
        this.serverName = serverName;
    }
    public String getSAFAgent() {
        return SAFAgent;
    }
    public void setSAFAgent(String SAFAgent) {
        this.SAFAgent = SAFAgent;
    }
    public String getEnviro() {
        return Enviro;
    }
    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    public Long getFailedMessagesTotal() {
        return FailedMessagesTotal;
    }
    public void setFailedMessagesTotal(Long FailedMessagesTotal) {
        this.FailedMessagesTotal = FailedMessagesTotal;
    }

    public Long getMessagesCurrentCount() {
        return MessagesCurrentCount;
    }
    public void setMessagesCurrentCount(Long MessagesCurrentCount) {
        this.MessagesCurrentCount = MessagesCurrentCount;
    }        
    public Long getRemoteEndpointsHighCount() {
        return RemoteEndpointsHighCount;
    }
    public void setRemoteEndpointsHighCount(Long RemoteEndpointsHighCount) {
        this.RemoteEndpointsHighCount = RemoteEndpointsHighCount;
    }
    public Long getRemoteEndpointsCurrentCount() {
        return RemoteEndpointsCurrentCount;
    }
    public void setRemoteEndpointsCurrentCount(Long RemoteEndpointsCurrentCount) {
        this.RemoteEndpointsCurrentCount = RemoteEndpointsCurrentCount;
    }
    public Integer getPausedForReceiving() {
        return PausedForReceiving;
    }
    public void setPausedForReceiving(Integer PausedForReceiving) {
        this.PausedForReceiving = PausedForReceiving;
    }
    public Integer getPausedForIncoming() {
        return PausedForIncoming;
    }
    public void setPausedForIncoming(Integer PausedForIncoming) {
        this.PausedForIncoming = PausedForIncoming;
    }
    public Integer getPausedForForwarding() {
        return PausedForForwarding;
    }
    public void setPausedForForwarding(Integer PausedForForwarding) {
        this.PausedForForwarding = PausedForForwarding;
    }
    public Long getMessagesHighCount() {
        return MessagesHighCount;
    }
    public void setMessagesHighCount(Long MessagesHighCount) {
        this.MessagesHighCount = MessagesHighCount;
    }
    public Long getMessagesPendingCount() {
        return MessagesPendingCount;
    }
    public void setMessagesPendingCount(Long MessagesPendingCount) {
        this.MessagesPendingCount = MessagesPendingCount;
    }
    public Integer getHealthState() {
        return HealthState;
    }
    public void setHealthState(Integer HealthState) {
        this.HealthState = HealthState;
    }    
    public Long getRemoteEndpointsTotalCount() {
        return RemoteEndpointsTotalCount;
    }
    public void setRemoteEndpointsTotalCount(Long RemoteEndpointsTotalCount) {
        this.RemoteEndpointsTotalCount = RemoteEndpointsTotalCount;
    }
    public Long getConversationsTotalCount() {
        return ConversationsTotalCount;
    }
    public void setConversationsTotalCount(Long ConversationsTotalCount) {
        this.ConversationsTotalCount = ConversationsTotalCount;
    }
    public Long getConversationsHighCount() {
        return ConversationsHighCount;
    }
    public void setConversationsHighCount(Long ConversationsHighCount) {
        this.ConversationsHighCount = ConversationsHighCount;
    }
    public Long getConversationsCurrentCount() {
        return ConversationsCurrentCount;
    }
    public void setConversationsCurrentCount(Long ConversationsCurrentCount) {
        this.ConversationsCurrentCount = ConversationsCurrentCount;
    }
    
    
    String serverName = null;
    String SAFAgent;
    String Enviro;
    Long FailedMessagesTotal;
    Long MessagesCurrentCount;
    Long MessagesPendingCount;
    Long MessagesHighCount;
    Long RemoteEndpointsCurrentCount;
    Long RemoteEndpointsHighCount;
    Long RemoteEndpointsTotalCount;
    Long ConversationsCurrentCount;
    Long ConversationsHighCount;
    Long ConversationsTotalCount;
    Integer HealthState;
    Integer PausedForForwarding;
    Integer PausedForIncoming;
    Integer PausedForReceiving;
    
}
