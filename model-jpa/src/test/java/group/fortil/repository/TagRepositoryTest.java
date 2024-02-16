package group.fortil.repository;

import group.fortil.model.TagModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {group.fortil.PersistenceConfig.class})
@ExtendWith(MockitoExtension.class)
class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TestEntityManager entityManager;


    @Test
    public void whenFindAll_retrieveNoEntity() {
        //given when
        List<TagModel> tags = tagRepository.findAll();

        Assertions.assertEquals(0, tags.size());
    }

    @Test
    public void whenFindAll_RetrieveOneEntityWithSameObjectAsThisTag() {
        TagModel tag = new TagModel();

        tag.setName("java");

        tagRepository.save(tag);

        List<TagModel> tags = tagRepository.findAll();

        Assertions.assertEquals(1, tags.size());
        Assertions.assertTrue(tags.contains(tag));
    }

}