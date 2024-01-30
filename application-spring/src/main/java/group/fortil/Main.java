package group.fortil;

import group.fortil.model.MessageModel;
import group.fortil.model.TagModel;
import group.fortil.model.UserModel;
import group.fortil.repository.MessageRepository;
import group.fortil.repository.TagRepository;
import group.fortil.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootApplication
@EntityScan("group.fortil.model")
@EnableJpaRepositories("group.fortil.repository")
@ComponentScan
public class Main {

    private static Service1 service1;
    private static UserRepository userRepository;
    private static MessageRepository messageRepository;
    private static TagRepository tagRepository;

    public Main(
        @Autowired Service1 service1,
        @Autowired UserRepository userRepository,
        @Autowired MessageRepository messageRepository,
        @Autowired TagRepository tagRepository
    ) {
        this.service1 = service1;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.tagRepository = tagRepository;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        /* Instantiate database with some data */
        initiateBdd();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static void initiateBdd() {
        /* maybe need to clear schema */

        UserModel user1 = new UserModel();
        user1.setEmail("user@user.com");
        user1.setFirstName("user");
        user1.setLastName("user");
        user1.setPassword("user");

        UserModel user2 = new UserModel();
        user2.setEmail("root@root.com");
        user2.setFirstName("root");
        user2.setLastName("root");
        user2.setPassword("root");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        TagModel tag1 = new TagModel();
        tag1.setName("Java");

        tag1 = tagRepository.save(tag1);

        MessageModel message1 = new MessageModel();
        message1.setContent("Hello World !");
        message1.setUser(user1);
        message1.setTags(List.of(tag1));

        message1 = messageRepository.save(message1);

        List<UserModel> users = StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
        List<MessageModel> messages = StreamSupport.stream(messageRepository.findAll().spliterator(), false).toList();
        List<TagModel> tags = StreamSupport.stream(tagRepository.findAll().spliterator(), false).toList();

        LOGGER.info("USER REPOSITORY COUNT() {}", userRepository.count());
        LOGGER.info("MESSAGE REPOSITORY COUNT() {}", messageRepository.count());
        LOGGER.info("USER REPOSITORY COUNT() {}", tagRepository.count());

        logTableListToString(users);
        logTableListToString(messages);
        logTableListToString(tags);
    }

    private static <T> void logTableListToString(List<T> list) {
        for (T e : list) {
            LOGGER.info("{} TO STRING() {}", e.getClass().toString(), e.toString());
        }
    }


}
