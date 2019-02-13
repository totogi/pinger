package pinger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwilioPinger implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TwilioPinger.class);

    public static TwilioPingerBuilder newBuilder(String account, String authToken) {
        return new TwilioPingerBuilder(account, authToken);
    }

    private TwilioPinger() {

    }

    @Override
    public void run() {

    }

    public static class TwilioPingerBuilder {
        private String account;
        private String authToken;
        private String url;
        private String phoneNumber;
        private Long interval;
        private Integer threshold;

        public TwilioPingerBuilder(String account, String authToken) {
            this.account = account;
            this.authToken = authToken;
        }

        public TwilioPingerBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public TwilioPingerBuilder withPhoneNumber(String phoneNumer) {
            this.phoneNumber = phoneNumer;
            return this;
        }

        public TwilioPingerBuilder withInterval(Long interval) {
            this.interval = interval;
            return this;
        }

        public TwilioPingerBuilder withThreshold(Integer threshold) {
            this.threshold = threshold;
            return this;
        }

        public TwilioPinger build() {
            return new TwilioPinger();
        }
    }
}
