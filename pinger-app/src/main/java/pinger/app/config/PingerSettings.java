package pinger.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pinger")
public class PingerSettings {

    private String account;
    private String authToken;
    private Long checkInterval;
    private Integer checkThreshold;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getCheckInterval() {
        return checkInterval;
    }

    public void setCheckInterval(Long checkInterval) {
        this.checkInterval = checkInterval;
    }

    public Integer getCheckThreshold() {
        return checkThreshold;
    }

    public void setCheckThreshold(Integer checkThreshold) {
        this.checkThreshold = checkThreshold;
    }
}
