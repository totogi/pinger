package pinger.core;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;

public class PingerBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(PingerBuilder.class);

    private URI url;
    private String phoneNumber;
    private Duration interval = Duration.ofMinutes(1);
    private Integer threshold = 3;

    public PingerBuilder(URI url, String phoneNumber) throws NumberParseException {
        this.url = url;
        this.phoneNumber = normalizePhoneNumber(phoneNumber);
    }

    public PingerBuilder withInterval(Duration interval) {
        this.interval = interval;
        return this;
    }

    public PingerBuilder withThreshold(Integer threshold) {
        this.threshold = threshold;
        return this;
    }

    public Pinger build() {
        return null;
    }

    private String normalizePhoneNumber(String phoneNumber) throws NumberParseException {
        return PhoneNumberUtil.getInstance().parse(phoneNumber, "US").toString();
    }
}
