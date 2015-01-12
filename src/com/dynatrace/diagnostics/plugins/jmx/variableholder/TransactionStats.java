package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class TransactionStats {

    public TransactionStats() {
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

    public Long getActiveTransactionsTotalCount() {
        return ActiveTransactionsTotalCount;
    }

    public void setActiveTransactionsTotalCount(Long ActiveTransactionsTotalCount) {
        this.ActiveTransactionsTotalCount = ActiveTransactionsTotalCount;
    }

    public Long getTransactionAbandonedTotalCount() {
        return TransactionAbandonedTotalCount;
    }

    public void setTransactionAbandonedTotalCount(Long TransactionAbandonedTotalCount) {
        this.TransactionAbandonedTotalCount = TransactionAbandonedTotalCount;
    }

    public Long getTransactionCommittedTotalCount() {
        return TransactionCommittedTotalCount;
    }

    public void setTransactionCommittedTotalCount(Long TransactionCommittedTotalCount) {
        this.TransactionCommittedTotalCount = TransactionCommittedTotalCount;
    }

    public Long getTransactionHeuristicsTotalCount() {
        return TransactionHeuristicsTotalCount;
    }

    public void setTransactionHeuristicsTotalCount(Long TransactionHeuristicsTotalCount) {
        this.TransactionHeuristicsTotalCount = TransactionHeuristicsTotalCount;
    }

    public Long getTransactionRolledBackAppTotalCount() {
        return TransactionRolledBackAppTotalCount;
    }

    public void setTransactionRolledBackAppTotalCount(Long TransactionRolledBackAppTotalCount) {
        this.TransactionRolledBackAppTotalCount = TransactionRolledBackAppTotalCount;
    }

    public Long getTransactionRolledBackResourceTotalCount() {
        return TransactionRolledBackAppTotalCount;
    }

    public void setTransactionRolledBackResourceTotalCount(Long TransactionRolledBackResourceTotalCount) {
        this.TransactionRolledBackResourceTotalCount = TransactionRolledBackResourceTotalCount;
    }

    public Long getTransactionRolledBackSystemTotalCount() {
        return TransactionRolledBackSystemTotalCount;
    }

    public void setTransactionRolledBackSystemTotalCount(Long TransactionRolledBackSystemTotalCount) {
        this.TransactionRolledBackSystemTotalCount = TransactionRolledBackSystemTotalCount;
    }

    public Long getTransactionRolledBackTimeoutTotalCount() {
        return TransactionRolledBackTimeoutTotalCount;
    }

    public void setTransactionRolledBackTimeoutTotalCount(Long TransactionRolledBackTimeoutTotalCount) {
        this.TransactionRolledBackTimeoutTotalCount = TransactionRolledBackTimeoutTotalCount;
    }

    public Long getTransactionRolledBackTotalCount() {
        return TransactionRolledBackTotalCount;
    }

    public void setTransactionRolledBackTotalCount(Long TransactionRolledBackTotalCount) {
        this.TransactionRolledBackTotalCount = TransactionRolledBackTotalCount;
    }

    public Long getTransactionTotalCount() {
        return TransactionTotalCount;
    }

    public void setTransactionTotalCount(Long TransactionTotalCount) {
        this.TransactionTotalCount = TransactionTotalCount;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    String timeDateConverted;
    String serverName = null;
    Long ActiveTransactionsTotalCount;
    Long TransactionAbandonedTotalCount;
    Long TransactionCommittedTotalCount;
    Long TransactionHeuristicsTotalCount;
    Long TransactionRolledBackAppTotalCount;
    Long TransactionRolledBackResourceTotalCount;
    Long TransactionRolledBackSystemTotalCount;
    Long TransactionRolledBackTimeoutTotalCount;
    Long TransactionRolledBackTotalCount;
    Long TransactionTotalCount;
    String Enviro;
}

