package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class JDBCStats {

    public JDBCStats() {
        super();
    }

    public String gettimeDateConverted() {
        return timeDateConverted;
    }

    public void settimeDateConverted(String timeDateConverted) {
        this.timeDateConverted = timeDateConverted;
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getJDBCName() {
        return JDBCName;
    }

    public void setJDBCName(String JDBCName) {
        this.JDBCName = JDBCName;
    }

    public Integer getstate() {
        return state;
    }

    public void setstate(Integer state) {
        this.state = state;
    }

    public Integer getActiveConnectionsHighCount() {
        return ActiveConnectionsHighCount;
    }

    public void setActiveConnectionsHighCount(Integer ActiveConnectionsHighCount) {
        this.ActiveConnectionsHighCount = ActiveConnectionsHighCount;
    }

    public Integer getCurrCapacity() {
        return CurrCapacity;
    }

    public void setCurrCapacity(Integer CurrCapacity) {
        this.CurrCapacity = CurrCapacity;
    }

    public Integer getCurrCapacityHighCount() {
        return CurrCapacityHighCount;
    }

    public void setCurrCapacityHighCount(Integer CurrCapacityHighCount) {
        this.CurrCapacityHighCount = CurrCapacityHighCount;
    }

    public Integer getConnectionDelayTime() {
        return ConnectionDelayTime;
    }

    public void setConnectionDelayTime(Integer ConnectionDelayTime) {
        this.ConnectionDelayTime = ConnectionDelayTime;
    }

    public Integer getPrepStmtCacheHitCount() {
        return PrepStmtCacheHitCount;
    }

    public void setPrepStmtCacheHitCount(Integer PrepStmtCacheHitCount) {
        this.PrepStmtCacheHitCount = PrepStmtCacheHitCount;
    }

    public Integer getPrepStmtCacheMissCount() {
        return PrepStmtCacheMissCount;
    }

    public void setPrepStmtCacheMissCount(Integer PrepStmtCacheMissCount) {
        this.PrepStmtCacheMissCount = PrepStmtCacheMissCount;
    }

    public Long getPrepStmtCacheDeleteCount() {
        return PrepStmtCacheDeleteCount;
    }

    public void setPrepStmtCacheDeleteCount(Long PrepStmtCacheDeleteCount) {
        this.PrepStmtCacheDeleteCount = PrepStmtCacheDeleteCount;
    }

    public Integer getActiveConnectionsCurrentCount() {
        return ActiveConnectionsCurrentCount;
    }

    public void setActiveConnectionsCurrentCount(Integer ActiveConnectionsCurrentCount) {
        this.ActiveConnectionsCurrentCount = ActiveConnectionsCurrentCount;
    }

    public Long getFailedReserveRequestCount() {
        return FailedReserveRequestCount;
    }

    public void setFailedReserveRequestCount(Long FailedReserveRequestCount) {
        this.FailedReserveRequestCount = FailedReserveRequestCount;
    }

    public Integer getFailuresToReconnectCount() {
        return FailuresToReconnectCount;
    }

    public void setFailuresToReconnectCount(Integer FailuresToReconnectCount) {
        this.FailuresToReconnectCount = FailuresToReconnectCount;
    }

    public Integer getLeakedConnectionCount() {
        return LeakedConnectionCount;
    }

    public void setLeakedConnectionCount(Integer LeakedConnectionCount) {
        this.LeakedConnectionCount = LeakedConnectionCount;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    String timeDateConverted;
    Integer state;
    String serverName = null;
    String JDBCName;
    Integer ActiveConnectionsHighCount;
    Integer CurrCapacity;
    Integer CurrCapacityHighCount;
    Integer ConnectionDelayTime;
    Integer PrepStmtCacheHitCount;
    Integer PrepStmtCacheMissCount;
    Long PrepStmtCacheDeleteCount;
    Integer ActiveConnectionsCurrentCount;
    Long FailedReserveRequestCount;
    Integer FailuresToReconnectCount;
    Integer LeakedConnectionCount;
    String Enviro;
}
