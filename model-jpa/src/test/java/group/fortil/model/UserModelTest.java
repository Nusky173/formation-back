package group.fortil.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {group.fortil.PersistenceConfig.class})
public class UserModelTest {

    @Test
    public void whenInstantiateUserWithProperties_retrieveWithCorrectValue() {
        //when & given
        UserModel user = new UserModel();

        user.setEmail("test@email.com");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");

        assertEquals(user.getEmail(), "test@email.com");
        assertEquals(user.getFirstName(), "test");
        assertEquals(user.getLastName(), "test");
        assertEquals(user.getPassword(), "test");
    }
}
