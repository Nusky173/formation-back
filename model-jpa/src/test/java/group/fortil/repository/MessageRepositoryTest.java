package group.fortil.repository;

import group.fortil.PersistenceConfig;
import group.fortil.model.MessageModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@ExtendWith(MockitoExtension.class)
class MessageRepositoryTest {

    MessageRepository messageRepository;

    TestEntityManager entityManager;

    @MockBean
    UserRepository userRepository;

    public MessageRepositoryTest(
        @Autowired MessageRepository messageRepository,
        @Autowired TestEntityManager entityManager,
        @Autowired UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.messageRepository = messageRepository;
    }

    @Test
    void whenFindAll_ReturnEmptyList() {
        List<MessageModel> list = messageRepository.findAll();

        assertTrue(list.isEmpty());
    }

}
