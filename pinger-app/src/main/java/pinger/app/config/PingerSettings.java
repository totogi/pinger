package pinger.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pinger")
public class PingerSettings {

    private Long checkInterval;
    private Integer checkThreshold;

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
