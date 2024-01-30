package group.fortil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.stream.Stream;

@Configuration
@ComponentScan
public class AppConfig {

    private final Environment environnement;


    public AppConfig(
        @Autowired Environment environnement
    ) {
        this.environnement = environnement;
    }

    @Bean("Service1")
    Service1 getService1() {
        return Stream.of(environnement.getActiveProfiles()).filter(profile -> {
            return profile.equals("impl1") || profile.equals("impl2");
        }).findAny().map(profile -> {
                if (profile.equals("impl1")) {
                    return new Service1Impl1();
                } else if (profile.equals("impl2")) {
                    return new Service1Impl2();
                } else {
                    throw new RuntimeException("Profile not found");
                }
            }

        ).get();
    }
}
