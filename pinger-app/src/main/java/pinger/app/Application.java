package pinger.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import pinger.app.config.PingerSettings;
import pinger.core.TwilioPinger;

@SpringBootApplication
@EnableConfigurationProperties({
        PingerSettings.class
})
public class Application {

    /**
     * Starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Runs the pinger.
     */
    @Component
    public class ApplicationRunner implements CommandLineRunner {
        private final Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

        private final PingerSettings settings;

        @Autowired
        public ApplicationRunner(PingerSettings settings) {
            this.settings = settings;
        }

        @Override
        public void run(String... args) throws Exception {
            PingerArgs config = CommandLine.populateCommand(new PingerArgs(), args);

            // Twilio Account Id
            if (config.account == null) {
                config.account = settings.getTwilioAccount();
            }

            // Twilio Auth Token
            if (config.authToken == null) {
                config.authToken = settings.getTwilioAuthToken();
            }

            if (config.fromPhone == null) {
                config.fromPhone = settings.getTwilioPhoneNumber();
            }

            // Interval Between Checks (minutes)
            if (config.interval == null) {
                config.interval = settings.getCheckInterval();
            }

            // Number of Errors Encountered Before Alert Sent
            if (config.threshold == null) {
                config.threshold = settings.getCheckThreshold();
            }

            TwilioPinger pinger = TwilioPinger.newBuilder(config.account, config.authToken, config.fromPhone)
                    .withUrl(config.url)
                    .withPhoneNumber(config.toPhone)
                    .withInterval(config.interval)
                    .withThreshold(config.threshold)
                    .build();

            pinger.run();

            Thread.currentThread().join();
        }
    }
}
