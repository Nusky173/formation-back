package group.fortil.repository;

import group.fortil.model.UserModel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {group.fortil.PersistenceConfig.class})
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserRepositoryTest {

    int i = 0;
    TestEntityManager testEntityManager;

    UserRepository userRepository;

    public UserRepositoryTest(
        @Autowired TestEntityManager testEntityManager,
        @Autowired UserRepository userRepository
    ) {
        this.testEntityManager = testEntityManager;
        this.userRepository = userRepository;
    }


    @Test
    public void awhenFindAll_RetrieveNoEntity() {
        //given when
        List<UserModel> users = userRepository.findAll();

        assertEquals(0, users.size());
    }

    @Test
    public void bwhenFindById_RetrieveOptionalNull() {
        //given when
        Optional<UserModel> user = userRepository.findById(1L);

        assertSame(Optional.ofNullable(null), user);
    }

    @Test
    public void cwhenSaveUser_RetrieveUSer() {
        //given when
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setLastName("test");
        user.setFirstName("test");

        user = userRepository.save(user);

        //then
        assertNotNull(user);
        List<UserModel> list = userRepository.findAll();
        assertEquals(user.getUserIndex(), Long.valueOf(1));
    }

    @Test
    public void dwhenSaveUserAlreadyExistWithDifferentValue() {
        //given
        UserModel user = new UserModel();
        user.setFirstName("test");
        user.setPassword("test");
        user.setLastName("test");
        user.setEmail("test@test.com");

        user = userRepository.save(user);

        //when
        Long index = 2L;
        user.setLastName("Lecordier");
        user.setFirstName("Simon");
        user = userRepository.save(user);

        //then
        List<UserModel> list = userRepository.findAll();
        assertEquals(index, user.getUserIndex());
        assertEquals("Lecordier", user.getLastName());
        assertEquals("Simon", user.getFirstName());
    }

    @Test
    public void ewhenDeleteNewEntity_NotOnFindAll() {
        //given
        UserModel user = new UserModel();
        user.setFirstName("jonh");
        user.setLastName("doe");
        user.setPassword("test");
        user.setEmail("jonh.doe@email.com");

        user = userRepository.save(user);

        //check user is in base
        assertTrue(userRepository.findById(user.getUserIndex()).isPresent());

        //when
        userRepository.delete(user);

        //then
        List<UserModel> list = userRepository.findAll();
        assertFalse(list.contains(user));
    }

    @Test
    public void fwhenFindByEmail_retrieveUser() {
        //given


    }
}
