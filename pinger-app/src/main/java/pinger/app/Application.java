package pinger.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

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

        @Override
        public void run(String... args) throws Exception {
            System.out.println("This thing work?");
        }
    }
}
