package group.fortil.service;

import group.fortil.business.UserBusinessImpl;
import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EmailAlreadyUseException;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.mapper.IUserMapperDto;
import group.fortil.mapper.IUserMapperModel;
import group.fortil.model.UserModel;
import group.fortil.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    int i = 0;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private IUserMapperDto mapperDto;
    @Mock
    private IUserMapperModel mapperDao;

    @Test
    public void whenFindAll_returnEmptyList() {
        //given
        List<UserModel> list = new ArrayList<UserModel>();

        //when
        when(userRepository.findAll()).thenReturn(list);
        List<UserDtoImpl> users = userService.findAll();

        //then
        assertTrue(users.isEmpty());
    }

    @Test
    public void whenFindById_retrieveEmpty() {
        //given when
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(1L);
        });
    }

    @Test
    public void whenFindByIdNotOnBase_retrieveEntityNotFoundException() {
        //given when
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(1L);
        });
    }

    @Test
    public void whenFindByIdOnBase_retrieveEntity() {
        //given
        UserModel model = new UserModel();
        model.setFirstName("test");
        model.setPassword("test");
        model.setLastName("test");
        model.setEmail("test@email.com");
        model.setUserIndex(1L);

        UserBusinessImpl business = new UserBusinessImpl();
        business.setLoginCode(model.getPassword());
        business.setEmailAddress(model.getEmail());
        business.setLastName(model.getLastName());
        business.setFirstName(model.getFirstName());
        business.setIndex(1L);

        UserDtoImpl dto = new UserDtoImpl();
        dto.setEmailAddress(business.getEmailAddress());
        dto.setLoginCode(business.getLoginCode());
        dto.setLastName(business.getLastName());
        dto.setFirstName(business.getFirstName());
        dto.setIndex(1L);

        //when
        when(userRepository.findById(1L)).thenReturn(Optional.of(model));
        when(mapperDao.modelToBusiness(model)).thenReturn(business);
        when(mapperDto.businessToDto(business)).thenReturn(dto);

        dto = userService.findById(1L);

        assertNotNull(dto);
        assertEquals(1L, dto.getIndex());
        assertEquals(model.getEmail(), dto.getEmailAddress());
        assertEquals(business.getEmailAddress(), dto.getEmailAddress());
    }

    @Test
    public void whenSaveEntity_returnEntity() {
        //given
        UserModel model = new UserModel();
        model.setFirstName("test");
        model.setPassword("test");
        model.setLastName("test");
        model.setEmail("test@email.com");
        model.setUserIndex(1L);

        UserBusinessImpl business = new UserBusinessImpl();
        business.setLoginCode(model.getPassword());
        business.setEmailAddress(model.getEmail());
        business.setLastName(model.getLastName());
        business.setFirstName(model.getFirstName());
        business.setIndex(1L);

        UserDtoImpl dto = new UserDtoImpl();
        dto.setEmailAddress(business.getEmailAddress());
        dto.setLoginCode(business.getLoginCode());
        dto.setLastName(business.getLastName());
        dto.setFirstName(business.getFirstName());
        dto.setIndex(1L);

        //when
        when(userRepository.save(model)).thenReturn(model);

        when(mapperDao.modelToBusiness(model)).thenReturn(business);
        when(mapperDao.businessToModel(business)).thenReturn(model);

        when(mapperDto.businessToDto(business)).thenReturn(dto);
        when(mapperDto.dtoToBusiness(dto)).thenReturn(business);

        dto = userService.create(dto);

        assertNotNull(dto);
        assertEquals(1L, dto.getIndex());
        assertEquals(model.getEmail(), dto.getEmailAddress());
        assertEquals(business.getEmailAddress(), dto.getEmailAddress());
    }

    @Test
    public void whenFindAllWithUserOnBase_returnAUserList() {
        //given
        UserModel model = new UserModel();
        model.setFirstName("test");
        model.setPassword("test");
        model.setLastName("test");
        model.setEmail("test@email.com");
        model.setUserIndex(1L);

        UserBusinessImpl business = new UserBusinessImpl();
        business.setLoginCode(model.getPassword());
        business.setEmailAddress(model.getEmail());
        business.setLastName(model.getLastName());
        business.setFirstName(model.getFirstName());
        business.setIndex(1L);

        UserDtoImpl dto = new UserDtoImpl();
        dto.setEmailAddress(business.getEmailAddress());
        dto.setLoginCode(business.getLoginCode());
        dto.setLastName(business.getLastName());
        dto.setFirstName(business.getFirstName());
        dto.setIndex(1L);

        List<UserModel> list = List.of(model);

        //when
        when(userRepository.findAll()).thenReturn(list);

        when(mapperDao.modelToBusiness(model)).thenReturn(business);
        when(mapperDto.businessToDto(business)).thenReturn(dto);


        List<UserBusinessImpl> users = userService.findAll();

        //then
        assertEquals(1, users.size());
        assertTrue(users.contains(dto));
    }

    @Test
    public void whenSaveUserWithEmailAlreadyUsedForAnotherUser_throwException() {
        //given
        UserModel model = new UserModel("test", "test", "test", "test@email.com");
        UserModel model1 = new UserModel("test", "test", "test", "test@email.com");

        UserBusinessImpl business = new UserBusinessImpl("test", "test", "test@email.com", "test");
        UserBusinessImpl business1 = new UserBusinessImpl("test", "test", "test@email.com", "test");

        UserDtoImpl dto = new UserDtoImpl("test", "test", "test@email.com", "test");
        UserDtoImpl dto1 = new UserDtoImpl("test", "test", "test@email.com", "test");

        //when
        when(userRepository.save(model)).thenReturn(model);
        when(userRepository.save(model1)).thenThrow(DataIntegrityViolationException.class);

        when(mapperDao.modelToBusiness(model)).thenReturn(business);
        when(mapperDao.businessToModel(business)).thenReturn(model);

        when(mapperDao.businessToModel(business1)).thenReturn(model1);

        when(mapperDto.businessToDto(business)).thenReturn(dto);
        when(mapperDto.dtoToBusiness(dto)).thenReturn(business);

        when(mapperDto.dtoToBusiness(dto1)).thenReturn(business1);


        dto = userService.create(dto);

        //then
        assertThrows(EmailAlreadyUseException.class, () -> {
            userService.create(dto1);
        });
    }
}
