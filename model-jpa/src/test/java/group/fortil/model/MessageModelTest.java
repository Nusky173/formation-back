package group.fortil.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {group.fortil.PersistenceConfig.class})
public class MessageModelTest {

    private UserModel user;

    private TagModel tag;

    @Before
    public void setUp() {
        user = new UserModel();
        tag = new TagModel();

        user.setLastName("test");
        user.setFirstName("test");
        user.setPassword("test");
        user.setEmail("test@email.com");

        tag.setName("java");
    }


    @Test
    public void whenInstantiateMessageWithProperties_retrieveWithCorrectValue() {
        //when & given
        Date date = new Date(Instant.now().toEpochMilli());
        MessageModel message = new MessageModel();

        message.setContent("test");
        message.setPublicationDate(new Date(Instant.now().toEpochMilli()));
        message.setModificationDate(new Date(Instant.now().toEpochMilli()));
        message.setUser(user);
        message.setTags(List.of(tag));

        //then
        assertEquals(message.getContent(), "test");
        assertEquals(message.getPublicationDate(), date);
        assertEquals(message.getModificationDate(), date);

        assertEquals(message.getUser(), user);
        assertEquals(message.getTags().size(), 1);
    }

}
