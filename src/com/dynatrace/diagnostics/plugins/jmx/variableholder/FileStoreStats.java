package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class FileStoreStats {

    public FileStoreStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public Long getCreateCount() {
        return CreateCount;
    }

    public void setCreateCount(Long CreateCount) {
        this.CreateCount = CreateCount;
    }

    public Long getDeleteCount() {
        return DeleteCount;
    }

    public void setDeleteCount(Long DeleteCount) {
        this.DeleteCount = DeleteCount;
    }

    public Long getPhysicalWriteCount() {
        return PhysicalWriteCount;
    }

    public void setPhysicalWriteCount(Long PhysicalWriteCount) {
        this.PhysicalWriteCount = PhysicalWriteCount;
    }

    public Long getReadCount() {
        return ReadCount;
    }

    public void setReadCount(Long ReadCount) {
        this.ReadCount = ReadCount;
    }

    public Long getUpdateCount() {
        return UpdateCount;
    }

    public void setUpdateCount(Long UpdateCount) {
        this.UpdateCount = UpdateCount;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    String serverName = null;
    String StoreName;
    Long CreateCount;
    Long DeleteCount;
    Long PhysicalWriteCount;
    Long ReadCount;
    Long UpdateCount;
    String Enviro;
}
