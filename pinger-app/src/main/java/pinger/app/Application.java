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
import pinger.core.Pinger;
import pinger.core.PingerBuilder;

import java.net.URI;
import java.time.Duration;

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
     *
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

            if (config.interval == null) {
                config.interval = settings.getCheckInterval();
            }

            if (config.threshold == null) {
                config.threshold = settings.getCheckThreshold();
            }

            Pinger pinger = new PingerBuilder(new URI(config.url), config.phone)
                    .withInterval(Duration.ofMinutes(config.interval))
                    .withThreshold(config.threshold)
                    .build();
        }
    }
}
