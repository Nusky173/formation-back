package group.fortil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@SpringBootApplication
@Import({PersistenceConfig.class, BusinessConfig.class, ControllerConfig.class})
public class Main {

    public Main() {}

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        /* Instantiate database with some data */
        //initiateBdd();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

}
