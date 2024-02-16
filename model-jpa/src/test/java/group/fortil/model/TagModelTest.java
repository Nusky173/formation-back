package group.fortil.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {group.fortil.PersistenceConfig.class})
public class TagModelTest {

    @Test
    public void whenInstantiateTestWithProperties_retrieveWithCorrectValue() {
        TagModel tag = new TagModel();

        tag.setName("test");

        Assert.assertEquals(tag.getName(), "test");
    }
}
