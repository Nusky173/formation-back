package group.fortil;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan("group.fortil.model")
@EnableJpaRepositories(value = "group.fortil.repository")
public class PersistenceConfig {
}