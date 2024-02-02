package group.fortil;

import group.fortil.business.MessageBusinessImpl;
import group.fortil.business.UserBusinessImpl;
import group.fortil.service.MessageServiceImpl;
import group.fortil.service.TagServiceImpl;
import group.fortil.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EntityScan("group.fortil.model")
@EnableJpaRepositories(value = "group.fortil.repository")
@ComponentScan
public class Main {

    private static Service1 service1;
    private static UserServiceImpl userService;
    private static MessageServiceImpl messageService;
    private static TagServiceImpl tagService;

    public Main(
        @Autowired Service1 service1,
        @Autowired UserServiceImpl userService,
        @Autowired MessageServiceImpl messageService,
        @Autowired TagServiceImpl tagService
    ) {
        this.service1 = service1;
        this.userService = userService;
        this.messageService = messageService;
        this.tagService = tagService;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        /* Instantiate database with some data */
        initiateBdd();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static void initiateBdd() {
        /* maybe need to clear schema */

        Optional<UserBusinessImpl> user = userService.findById(Long.valueOf(1));

        MessageBusinessImpl message = new MessageBusinessImpl();

        message.setText("test custom validation");

        message.setChangeDate(new Date());
        message.setPublishDate(new Date());

        user.ifPresent(message::setOwner);

//        messageService.create(message);

    }

    private static <T> void logTableListToString(List<T> list) {
        for (T e : list) {
            LOGGER.info("{} TO STRING() {}", e.getClass().toString(), e.toString());
        }
    }


}
