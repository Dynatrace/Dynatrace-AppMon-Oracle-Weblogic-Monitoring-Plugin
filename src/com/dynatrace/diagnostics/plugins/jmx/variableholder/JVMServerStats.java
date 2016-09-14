package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class JVMServerStats {

    public String gettimeDateConverted() {
        return timeDateConverted;
    }

    public void settimeDateConverted(String timeDateConverted) {
        this.timeDateConverted = timeDateConverted;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Long getGarbageCollectionTime() {
        return garbageCollectionTime;
    }

    public void setGarbageCollectionTime(Long garbageCollectionTime) {
        this.garbageCollectionTime = garbageCollectionTime;
    }

    public Double getAllProcessorsAverageLoad() {
        return AllProcessorsAverageLoad;
    }

    public void setAllProcessorsAverageLoad(Double AllProcessorsAverageLoad) {
        this.AllProcessorsAverageLoad = AllProcessorsAverageLoad;
    }

    public Long getTotalGarbageCollectionCount() {
        return TotalGarbageCollectionCount;
    }

    public void setTotalGarbageCollectionCount(Long TotalGarbageCollectionCount) {
        this.TotalGarbageCollectionCount = TotalGarbageCollectionCount;
    }

    public Double getJVMProcessorLoad() {
        return JVMProcessorLoad;
    }

    public void setJVMProcessorLoad(Double JVMProcessorLoad) {
        this.JVMProcessorLoad = JVMProcessorLoad;
    }

    public Integer getNumberOfProcessors() {
        return NumberOfProcessors;
    }

    public void setNumberOfProcessors(Integer NumberOfProcessors) {
        this.NumberOfProcessors = NumberOfProcessors;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    public Long getHFCurrent() {
        return HFCurrent;
    }

    public void setHFCurrent(Long HFCurrent) {
        this.HFCurrent = HFCurrent;
    }

    public Long getHeapTotalCurrent() {
        return HeapTotalCurrent;
    }

    public void setHeapTotalCurrent(Long HeapTotalCurrent) {
        this.HeapTotalCurrent = HeapTotalCurrent;
    }

    public String getDaysRunning() {
        return DaysRunning;
    }

    public void setDaysRunning(String DaysRunning) {
        this.DaysRunning = DaysRunning;
    }

    public Long getHUCurrent() {
        return HUCurrent;
    }

    public void setHUCurrent(Long HUCurrent) {
        this.HUCurrent = HUCurrent;
    }

    public Integer getCounts() {
        return Counts;
    }

    public void setCounts(Integer Counts) {
        this.Counts = Counts;
    }

    String timeDateConverted;
    String serverName = null;
    Long beginTime;
    Long garbageCollectionTime;
    String hostname;
    Double AllProcessorsAverageLoad;
    Long TotalGarbageCollectionCount;
    Double JVMProcessorLoad;
    Integer NumberOfProcessors;
    String Enviro;
    String DaysRunning;
    Long HeapTotalCurrent;
    Long HFCurrent;
    Long HUCurrent;
    Integer Counts;
	
}
