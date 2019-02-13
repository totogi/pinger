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
    private Duration duration = Duration.ofMinutes(1);
    private Integer errorCount = 3;

    public PingerBuilder(URI url, String phoneNumber) throws NumberParseException {
        this.url = url;
        this.phoneNumber = normalizePhoneNumber(phoneNumber);
    }

    public PingerBuilder withInterval(Duration duration) {
        this.duration = duration;
        return this;
    }

    public PingerBuilder withErrorCountBeforeAlert(Integer errorCount) {
        this.errorCount = errorCount;
        return this;
    }

    public Pinger build() {
        return null;
    }

    private String normalizePhoneNumber(String phoneNumber) throws NumberParseException {
        return PhoneNumberUtil.getInstance().parse(phoneNumber, "US").toString();
    }
}
