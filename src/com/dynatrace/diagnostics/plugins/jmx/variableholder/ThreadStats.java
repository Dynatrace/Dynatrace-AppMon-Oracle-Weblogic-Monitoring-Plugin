package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ThreadStats {

    public ThreadStats() {
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

    public Integer getOpenSocketsCurrentCount() {
        return OpenSocketsCurrentCount;
    }

    public void setOpenSocketsCurrentCount(Integer OpenSocketsCurrentCount) {
        this.OpenSocketsCurrentCount = OpenSocketsCurrentCount;
    }

    public Integer getExecuteThreadTotalCount() {
        return ExecuteThreadTotalCount;
    }

    public void setExecuteThreadTotalCount(Integer ExecuteThreadTotalCount) {
        this.ExecuteThreadTotalCount = ExecuteThreadTotalCount;
    }

    public Integer getExecuteThreadIdleCount() {
        return ExecuteThreadIdleCount;
    }

    public void setExecuteThreadIdleCount(Integer ExecuteThreadIdleCount) {
        this.ExecuteThreadIdleCount = ExecuteThreadIdleCount;
    }

    public Integer getHoggingThreadCount() {
        return HoggingThreadCount;
    }

    public void setHoggingThreadCount(Integer HoggingThreadCount) {
        this.HoggingThreadCount = HoggingThreadCount;
    }

    public Integer getQueueLength() {
        return QueueLength;
    }

    public void setQueueLength(Integer QueueLength) {
        this.QueueLength = QueueLength;
    }

    public Integer getStandbyThreadCount() {
        return StandbyThreadCount;
    }

    public void setStandbyThreadCount(Integer StandbyThreadCount) {
        this.StandbyThreadCount = StandbyThreadCount;
    }

    public Integer gethealth() {
        return health;
    }

    public void sethealth(Integer health) {
        this.health = health;
    }

    public Long getCompletedRequestCount() {
        return CompletedRequestCount;
    }

    public void setCompletedRequestCount(Long CompletedRequestCount) {
        this.CompletedRequestCount = CompletedRequestCount;
    }

    public Double getPresentThroughput() {
        return PresentThroughput;
    }

    public void setPresentThroughput(Double PresentThroughput) {
        this.PresentThroughput = PresentThroughput;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    String timeDateConverted;
    String serverName = null;
    Integer OpenSocketsCurrentCount;
    Integer ExecuteThreadTotalCount;
    Integer ExecuteThreadIdleCount;
    Integer HoggingThreadCount;
    Integer QueueLength;
    Integer StandbyThreadCount;
    Integer health;
    Long CompletedRequestCount;
    Double PresentThroughput;
    String Enviro;
}

