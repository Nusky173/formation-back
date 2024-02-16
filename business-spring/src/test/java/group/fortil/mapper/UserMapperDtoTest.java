package group.fortil.mapper;

import group.fortil.business.UserBusinessImpl;
import group.fortil.dto.UserDtoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperDtoTest {

    @InjectMocks
    IUserMapperDtoImpl mapper;

    @Test
    void whenMapBusinessToDtoWithNullObject_returnNull() {
        //given
        UserBusinessImpl dbo = null;

        //when
        UserDtoImpl dto = mapper.businessToDto(dbo);

        //then
        assertNull(dto);
    }

    @Test
    void whenMapBusinessToDtoWithValidObject_returnDtoWithSameValue() {
        //given
        UserBusinessImpl dbo = new UserBusinessImpl("test", "test", "test@gmail.com", "test");

        //when
        UserDtoImpl dto = mapper.businessToDto(dbo);

        //then
        assertNotNull(dto);
        assertEquals(dbo.getEmailAddress(), dto.getEmailAddress());
        assertEquals(dbo.getFirstName(), dto.getFirstName());
        assertEquals(dbo.getLastName(), dto.getLastName());
        assertEquals(dbo.getLoginCode(), dto.getLoginCode());
    }

    @Test
    void whenMapDtoToBusinessWithNullObject_returnNull() {
        //given
        UserDtoImpl dto = null;

        //when
        UserBusinessImpl dbo = mapper.dtoToBusiness(dto);

        //then
        assertNull(dbo);
    }

    @Test
    void whenMapDtoToBusinessWithValidObject_returnDtoWithSameValue() {
        //given
        UserDtoImpl dto = new UserDtoImpl("test", "test", "test@gmail.com", "test");

        //when
        UserBusinessImpl dbo = mapper.dtoToBusiness(dto);

        //then
        assertNotNull(dbo);
        assertEquals(dbo.getEmailAddress(), dto.getEmailAddress());
        assertEquals(dbo.getFirstName(), dto.getFirstName());
        assertEquals(dbo.getLastName(), dto.getLastName());
        assertEquals(dbo.getLoginCode(), dto.getLoginCode());
    }
}
