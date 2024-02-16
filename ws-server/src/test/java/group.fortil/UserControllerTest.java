package group.fortil;

import group.fortil.controller.UserControllerImpl;
import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EmailAlreadyUseException;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserControllerImpl controller;

    @Mock
    private UserServiceImpl<UserDtoImpl> service;

    @Test
    void whenUserGetAll_returnEmptyUSerList() {
        //given

        //when
        when(service.findAll()).thenReturn(List.of());

        //then
        assertSame(List.of(), controller.getUser((String) null));
    }

    @Test
    void givenNoUserInBase_whenGetAllWithEmailFilter_ThrowEntityNotFoundException() {
        //given

        //when
        when(service.findAll()).thenReturn(List.of());

        //then
        assertThrows(EntityNotFoundException.class, () -> {
            controller.getUser("test@test.com");
        });
    }

    @Test
    void givenUser_whenGetAll_returnListSize1() {
        //given
        UserDtoImpl user = new UserDtoImpl("test", "test", "test@gmail.com", "test");
        List<UserDtoImpl> attemptToBe = new ArrayList<>();
        attemptToBe.add(user);

        //when
        when(service.findAll()).thenReturn(List.of(user));

        //then
        List<UserDtoImpl> list = controller.getUser((String) null);
        assertFalse(list.isEmpty()); //list not empty
        assertEquals(list.size(), attemptToBe.size()); //list.size same as attemptToBe.size
        assertTrue(list.contains(user)); //result contains user we want to
    }

    @Test
    void givenUser_whenGetAllEmailFiltered_ThrowEntityNotFoundException() {
        //given
        UserDtoImpl user = new UserDtoImpl("test", "test", "test@gmail.com", "test");

        //when
        when(service.findAll()).thenReturn(List.of(user));

        //then
        assertThrows(EntityNotFoundException.class, () -> {
            controller.getUser("test@test.com");
        });
    }

    @Test
    void givenUser_whenGetAllEmailFiltered_returnListWithUser() {
        //given
        UserDtoImpl user = new UserDtoImpl("test", "test", "test@gmail.com", "test");
        List<UserDtoImpl> attemptToBe = new ArrayList<>();
        attemptToBe.add(user);

        //when
        when(service.findAll()).thenReturn(List.of(user));

        //then
        List<UserDtoImpl> list = controller.getUser("test@gmail.com");
        assertFalse(list.isEmpty()); //list not empty
        assertEquals(list.size(), attemptToBe.size()); //list.size same as attemptToBe.size
        assertTrue(list.contains(user)); //result contains user we want to
    }

    @Test
    void whenGetById_ThrowEntityNotFoundException() {
        //given when
        when(service.findById(1L)).thenThrow(EntityNotFoundException.class);

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            controller.getUser(1L);
        });
    }

    @Test
    void whenGetByIdNegativeValue_ThrowEntityNotFoundException() {
        //given when
        when(service.findById(-1L)).thenThrow(EntityNotFoundException.class);

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            controller.getUser(-1L);
        });
    }

    @Test
    void givenUser_whenGetById_returnUser() {
        //given
        UserDtoImpl attemptToBe = new UserDtoImpl(1L, "test", "test", "test@gmail.com", "test");

        // when
        when(service.findById(1L)).thenReturn(attemptToBe);
        UserDtoImpl result = controller.getUser(1L);

        // then
        assertSame(attemptToBe, result);
        assertEquals(1L, result.getIndex());
    }

    @Test
    void givenUser_whenCreateUser_returnUserWithIndex() {
        //given
        UserDtoImpl attemptToBe = new UserDtoImpl(1L, "test", "test", "test@gmail.com", "test");
        UserDtoImpl userToCreate = new UserDtoImpl("test", "test", "test@gmail.com", "test");

        // when
        when(service.create(userToCreate)).thenReturn(attemptToBe);
        UserDtoImpl result = controller.createUser(userToCreate);

        // then
        assertSame(attemptToBe, result);
        assertEquals(1L, result.getIndex());
    }

    @Test
    void givenUser_whenCreateUser_throwEmailAlreadyUsed() {
        //given
        UserDtoImpl userToCreate = new UserDtoImpl("test", "test", "test@gmail.com", "test");

        // when
        when(service.create(userToCreate)).thenThrow(EmailAlreadyUseException.class);

        // then
        assertThrows(EmailAlreadyUseException.class, () -> {
            controller.createUser(userToCreate);
        });
    }
}
