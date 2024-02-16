package group.fortil.mapper;

import group.fortil.business.UserBusinessImpl;
import group.fortil.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperModelTest {

    @InjectMocks
    IUserMapperModelImpl mapper;

    @Test
    void whenMapBusinessToModelWithNullObject_returnNull() {
        //given
        UserBusinessImpl dbo = null;

        //when
        UserModel dao = mapper.businessToModel(dbo);

        //then
        assertNull(dao);
    }

    @Test
    void whenMapBusinessToModelWithValidObject_returnBusinessWithSameValue() {
        //given
        UserBusinessImpl dbo = new UserBusinessImpl("test", "test", "test@gmail.com", "test");

        //when
        UserModel dao = mapper.businessToModel(dbo);

        //then
        assertNotNull(dao);
        assertEquals(dbo.getEmailAddress(), dao.getEmail());
        assertEquals(dbo.getFirstName(), dao.getFirstName());
        assertEquals(dbo.getLastName(), dao.getLastName());
        assertEquals(dbo.getLoginCode(), dao.getPassword());
    }

    @Test
    void whenMapModelToBusinessWithNullObject_returnNull() {
        //given
        UserModel dao = null;

        //when
        UserBusinessImpl dbo = mapper.modelToBusiness(dao);

        //then
        assertNull(dbo);
    }

    @Test
    void whenMapModelToBusinessWithValidObject_returnDtoWithSameValue() {
        //given
        UserModel dao = new UserModel("test", "test", "test", "test@gmail.com");

        //when
        UserBusinessImpl dbo = mapper.modelToBusiness(dao);

        //then
        assertNotNull(dbo);
        assertEquals(dbo.getEmailAddress(), dao.getEmail());
        assertEquals(dbo.getFirstName(), dao.getFirstName());
        assertEquals(dbo.getLastName(), dao.getLastName());
        assertEquals(dbo.getLoginCode(), dao.getPassword());
    }
}
