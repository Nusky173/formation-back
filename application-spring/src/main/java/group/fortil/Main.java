package group.fortil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EntityScan("group.fortil.model")
@EnableJpaRepositories(value = "group.fortil.repository")
@ComponentScan
public class Main {

    public Main(
    ) {
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        /* Instantiate database with some data */
        //initiateBdd();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static void initiateBdd() {
        /* maybe need to clear schema */
    }

    private static <T> void logTableListToString(List<T> list) {
        for (T e : list) {
            LOGGER.info("{} TO STRING() {}", e.getClass().toString(), e.toString());
        }
    }


}
