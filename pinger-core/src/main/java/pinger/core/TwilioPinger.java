package pinger.core;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
     * @param twilioAccount
     * @param twilioAuthToken
     * @param twilioPhoneNumber
     * @return
     */
    public static Builder newBuilder(String twilioAccount, String twilioAuthToken, String twilioPhoneNumber) {
        return new Builder(twilioAccount, twilioAuthToken, new PhoneNumber(twilioPhoneNumber));
    }

    private final String twilioAccount;
    private final String twilioAuthToken;
    private final PhoneNumber twilioPhoneNumber;
    private final String url;
    private final PhoneNumber phoneNumber;
    private final Duration interval;
    private final Integer threshold;

    private final AtomicInteger errorCnt = new AtomicInteger(0);

    private TwilioPinger(final String twilioAccount,
                         final String twilioAuthToken,
                         final PhoneNumber twilioPhoneNumber,
                         final String url,
                         final PhoneNumber phoneNumber,
                         final Duration interval,
                         final Integer threshold) {
        this.twilioAccount = twilioAccount;
        this.twilioAuthToken = twilioAuthToken;
        this.twilioPhoneNumber = twilioPhoneNumber;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.interval = interval;
        this.threshold = threshold;
    }

    @Override
    public void run() {
        LOG.info("Starting to monitor URL: {}", url);

        Twilio.init(twilioAccount, twilioAuthToken);

        Timer timer = new Timer();
        timer.schedule(new PingTask(url, errorCnt, twilioPhoneNumber, phoneNumber), interval.getSeconds());
    }

    /**
     *
     */
    static class PingTask extends TimerTask {
        private static final Logger LOG = LoggerFactory.getLogger(PingTask.class);

        private final String url;
        private final AtomicInteger errorCnt;
        private final PhoneNumber twilioPhoneNumber;
        private final PhoneNumber phoneNumber;

        public PingTask(String url,
                        AtomicInteger errorCnt,
                        PhoneNumber twilioPhoneNumber,
                        PhoneNumber phoneNumber) {
            this.url = url;
            this.errorCnt = errorCnt;
            this.twilioPhoneNumber = twilioPhoneNumber;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    LOG.info("FAILURE: {}", url);

                    if (errorCnt.incrementAndGet() >= 3) {
                        sendAlert();
                        errorCnt.set(0);
                    }
                } else {
                    LOG.info("SUCCESS: {}", url);
                }
            } catch (IOException e) {
                LOG.error("Exception occurred while sending http ping", e);
            }
        }

        private void sendAlert() {
            Message.creator(phoneNumber, twilioPhoneNumber, String.format("Pinger - FAILURE: %s", url))
                    .create();
        }
    }

    /**
     * Builds new instances of {@link TwilioPinger}
     */
    public static class Builder {
        private final String twilioAccount;
        private final String twilioAuthToken;
        private final PhoneNumber twilioPhoneNumber;
        private String url;
        private String phoneNumber;
        private Long interval;
        private Integer threshold;

        /**
         * Creates a new TwilioPinger builder.
         *
         * @param twilioAccount twilio twilioAccount sid
         * @param twilioAuthToken twilio auth token
         * @param twilioPhoneNumber twilio phone number
         */
        public Builder(final String twilioAccount,
                       final String twilioAuthToken,
                       PhoneNumber twilioPhoneNumber) {
            this.twilioAccount = twilioAccount;
            this.twilioAuthToken = twilioAuthToken;
            this.twilioPhoneNumber = twilioPhoneNumber;
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
            return new TwilioPinger(twilioAccount,
                    twilioAuthToken,
                    twilioPhoneNumber,
                    url,
                    new PhoneNumber(phoneNumber),
                    Duration.ofMinutes(interval),
                    threshold);
        }
    }
}
