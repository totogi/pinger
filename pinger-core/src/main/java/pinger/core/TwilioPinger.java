package pinger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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

    private final AtomicInteger errorCnt = new AtomicInteger(0);

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
        LOG.info("Starting to monitor URL: {}", url);

        Timer timer = new Timer();
        timer.schedule(new PingTask(url, errorCnt), interval.getSeconds());
    }

    /**
     *
     */
    static class PingTask extends TimerTask {
        private static final Logger LOG = LoggerFactory.getLogger(PingTask.class);

        private final String url;
        private final AtomicInteger errorCnt;

        public PingTask(String url, AtomicInteger errorCnt) {
            this.url = url;
            this.errorCnt = errorCnt;
        }

        @Override
        public void run() {
            
        }
    }

    /**
     * Builds new instances of {@link TwilioPinger}
     */
    public static class Builder {
        private final String account;
        private final String authToken;
        private String url;
        private String phoneNumber;
        private Long interval;
        private Integer threshold;

        /**
         * Creates a new TwilioPinger builder.
         *
         * @param account twilio account sid
         * @param authToken twilio auth token
         */
        public Builder(final String account, final String authToken) {
            this.account = account;
            this.authToken = authToken;
        }

        /**
         * Sets the url to monitor on the builder.
         *
         * @param url url to monitor
         * @return this builder
         */
        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the phone number to alert in the event of an outage on the builder.
         *
         * @param phoneNumer phone number to alert
         * @return this builder
         */
        public Builder withPhoneNumber(final String phoneNumer) {
            this.phoneNumber = phoneNumer;
            return this;
        }

        /**
         * Sets the interval, in minutes, between health checks on the builder.
         *
         * @param interval interval between health checks in minutes
         * @return this builder
         */
        public Builder withInterval(final Long interval) {
            this.interval = interval;
            return this;
        }

        /**
         * Sets the number of error health checks to encounter before sending alert on the builder.
         *
         * @param threshold number of error health checks before alert
         * @return this builder
         */
        public Builder withThreshold(final Integer threshold) {
            this.threshold = threshold;
            return this;
        }

        /**
         * Builds a new {@link TwilioPinger} instance.
         *
         * @return a configured {@link TwilioPinger}
         */
        public TwilioPinger build() {
            return new TwilioPinger(account, authToken, url, phoneNumber, Duration.ofMinutes(interval), threshold);
        }
    }
}
