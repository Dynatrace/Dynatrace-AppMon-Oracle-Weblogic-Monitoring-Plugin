package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class JMSStats {

    public JMSStats() {
        super();
    }

    public Long getmessageAge() {
        return messageAge;
    }

    public void setmessageAge(Long messageAge) {
        this.messageAge = messageAge;
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getjmsName() {
        return jmsName;
    }

    public void setjmsName(String jmsName) {
        this.jmsName = jmsName;
    }

    public Long getMessagesCurrentCount() {
        return MessagesCurrentCount;
    }

    public void setMessagesCurrentCount(Long MessagesCurrentCount) {
        this.MessagesCurrentCount = MessagesCurrentCount;
    }

    public Long getMessagesPendingCount() {
        return MessagesPendingCount;
    }

    public void setMessagesPendingCount(Long MessagesPendingCount) {
        this.MessagesPendingCount = MessagesPendingCount;
    }

    public Long getMessagesHighCount() {
        return MessagesHighCount;
    }

    public void setMessagesHighCount(Long MessagesHighCount) {
        this.MessagesHighCount = MessagesHighCount;
    }

    public Long getMessageTotalCount() {
        return MessageTotalCount;
    }

    public void setMessageTotalCount(Long MessageTotalCount) {
        this.MessageTotalCount = MessageTotalCount;
    }

    public Long getConsumerCurrent() {
        return ConsumerCurrent;
    }

    public void setConsumerCurrent(Long ConsumerCurrent) {
        this.ConsumerCurrent = ConsumerCurrent;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    Long messageAge;
    String serverName = null;
    String jmsName;
    Long MessagesCurrentCount;
    Long MessagesPendingCount;
    Long MessagesHighCount;
    Long MessageTotalCount;
    Long ConsumerCurrent;
    String Enviro;
}

