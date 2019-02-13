package pinger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 *
 */
public final class TwilioPinger implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TwilioPinger.class);

    /**
     *
     * @param account
     * @param authToken
     * @return
     */
    public static Builder newBuilder(String account, String authToken) {
        return new Builder(account, authToken);
    }

    private final String account;
    private final String authToken;
    private final String url;
    private final String phoneNumber;
    private final Duration interval;
    private final Integer threshold;

    private TwilioPinger(final String account,
                         final String authToken,
                         final String url,
                         final String phoneNumber,
                         final Duration interval,
                         final Integer threshold) {
        this.account = account;
        this.authToken = authToken;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.interval = interval;
        this.threshold = threshold;
    }

    @Override
    public void run() {
        LOG.info("Monitoring Url: {}", url);
    }

    public static class Builder {
        private final String account;
        private final String authToken;
        private String url;
        private String phoneNumber;
        private Long interval;
        private Integer threshold;

        public Builder(final String account, final String authToken) {
            this.account = account;
            this.authToken = authToken;
        }

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Builder withPhoneNumber(final String phoneNumer) {
            this.phoneNumber = phoneNumer;
            return this;
        }

        public Builder withInterval(final Long interval) {
            this.interval = interval;
            return this;
        }

        public Builder withThreshold(final Integer threshold) {
            this.threshold = threshold;
            return this;
        }

        public TwilioPinger build() {
            return new TwilioPinger(account, authToken, url, phoneNumber, Duration.ofMinutes(interval), threshold);
        }
    }
}
