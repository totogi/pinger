package pinger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;

public class DefaultPinger implements Pinger {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultPinger.class);

    private final URI url;
    private final String phoneNumber;
    private final Duration checkInterval;
    private final Integer errorCount;

    public DefaultPinger(URI url,
                         String phoneNumber,
                         Duration checkInterval,
                         Integer errorCount) {
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.checkInterval = checkInterval;
        this.errorCount = errorCount;
    }
}
