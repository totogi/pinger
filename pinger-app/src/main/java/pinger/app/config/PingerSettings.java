package pinger.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "pinger")
@Primary
public class PingerSettings {

    private String twilioAccount;
    private String twilioAuthToken;
    private String twilioPhoneNumber;
    private Long checkInterval;
    private Integer checkThreshold;

    public String getTwilioAccount() {
        return twilioAccount;
    }

    public void setTwilioAccount(String twilioAccount) {
        this.twilioAccount = twilioAccount;
    }

    public String getTwilioAuthToken() {
        return twilioAuthToken;
    }

    public void setTwilioAuthToken(String twilioAuthToken) {
        this.twilioAuthToken = twilioAuthToken;
    }

    public String getTwilioPhoneNumber() {
        return twilioPhoneNumber;
    }

    public void setTwilioPhoneNumber(String twilioPhoneNumber) {
        this.twilioPhoneNumber = twilioPhoneNumber;
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
