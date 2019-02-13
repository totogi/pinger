package pinger.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import pinger.app.config.PingerSettings;

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

        }
    }
}
