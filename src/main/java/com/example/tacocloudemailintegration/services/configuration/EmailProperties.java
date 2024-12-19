package com.example.tacocloudemailintegration.services.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "tacocloud.email")
@Component
public class EmailProperties {

    private String username;
    private String password;
    private String host;
    private String mailbox;
    private long pollRate = 10000;

    public String getImapUrl() {
        return String.format("imap://%s:%s@%s/%s",
                this.username, this.password, this.host, this.mailbox);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getMailbox() {
        return mailbox;
    }

    public long getPollRate() {
        return pollRate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public void setPollRate(long pollRate) {
        this.pollRate = pollRate;
    }
}
