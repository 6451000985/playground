package com.learnhub.playgound.user;


import com.learnhub.playgound.user.domain.User;
import com.learnhub.playgound.user.dto.CreateUserRequest;
import com.learnhub.playgound.user.dto.UpdateUserRequest;
import com.learnhub.playgound.user.dto.UserResponse;
import com.learnhub.playgound.user.repository.UserRepository;
import com.learnhub.playgound.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.time.LocalDateTime;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void whenCreateUser_ThenSuccess(){

        LocalDateTime expectedTime = LocalDateTime.of(2025, 2, 14, 17, 0);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail("test@mail.com");
        savedUser.setPasswordHash("hashed");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setAvatarUrl("testavatarurl.com");
        savedUser.setActive(true);
        savedUser.setVerified(false);
        savedUser.setLastLogin(expectedTime);
        savedUser.setCreatedAt(expectedTime);
        savedUser.setUpdatedAt(expectedTime);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);


        CreateUserRequest createUserRequest = new CreateUserRequest("test@mail.com","hashed","John",
                "Doe","testavatarurl.com");

        UserResponse userResponse = userService.createUser(createUserRequest);

        assertEquals(1,userResponse.id().intValue());
        assertEquals("test@mail.com",userResponse.email());
        assertEquals("John",userResponse.firstName());
        assertEquals("Doe",userResponse.lastName());
        assertEquals("testavatarurl.com",userResponse.avatarUrl());
        assertFalse(userResponse.isVerified());
        assertTrue(userResponse.isActive());
        assertEquals(expectedTime,userResponse.lastLogin());
        assertEquals(expectedTime,userResponse.createdAt());
        assertEquals(expectedTime,userResponse.updatedAt());



    }

    @Test
    void GetUserById_WhenUserExists_ShouldDeleteSuccessfully(){

        Long id =1L;

        LocalDateTime expectedTime = LocalDateTime.of(2025, 2, 14, 17, 0);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail("test@mail.com");
        savedUser.setPasswordHash("hashed");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setAvatarUrl("testavatarurl.com");
        savedUser.setActive(true);
        savedUser.setVerified(false);
        savedUser.setLastLogin(expectedTime);
        savedUser.setCreatedAt(expectedTime);
        savedUser.setUpdatedAt(expectedTime);

        when(userRepository.findById(id)).thenReturn(Optional.of(savedUser));


        UserResponse userResponse = userService.getUserById(id);

        assertEquals(1,userResponse.id().intValue());
        assertEquals("test@mail.com",userResponse.email());
        assertEquals("John",userResponse.firstName());
        assertEquals("Doe",userResponse.lastName());
        assertEquals("testavatarurl.com",userResponse.avatarUrl());
        assertFalse(userResponse.isVerified());
        assertTrue(userResponse.isActive());
        assertEquals(expectedTime,userResponse.lastLogin());
        assertEquals(expectedTime,userResponse.createdAt());
        assertEquals(expectedTime,userResponse.updatedAt());


    }

    @Test
    void  GetUserById_WhenUserDoesNotExist_ShouldThrowException(){
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {
            userService.getUserById(id);
                }
        );

        assertEquals("User not found with id: " + id,exception.getMessage());
        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);

    }


    @Test
    void getUserByEmail_WhenUserExists_ShouldDeleteSuccessfully(){

        String email = "getUserByEmail";

        LocalDateTime expectedTime = LocalDateTime.of(2025, 2, 14, 17, 0);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail("test@mail.com");
        savedUser.setPasswordHash("hashed");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setAvatarUrl("testavatarurl.com");
        savedUser.setActive(true);
        savedUser.setVerified(false);
        savedUser.setLastLogin(expectedTime);
        savedUser.setCreatedAt(expectedTime);
        savedUser.setUpdatedAt(expectedTime);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(savedUser));


        UserResponse userResponse = userService.getUserByEmail(email);

        assertEquals(1,userResponse.id().intValue());
        assertEquals("test@mail.com",userResponse.email());
        assertEquals("John",userResponse.firstName());
        assertEquals("Doe",userResponse.lastName());
        assertEquals("testavatarurl.com",userResponse.avatarUrl());
        assertFalse(userResponse.isVerified());
        assertTrue(userResponse.isActive());
        assertEquals(expectedTime,userResponse.lastLogin());
        assertEquals(expectedTime,userResponse.createdAt());
        assertEquals(expectedTime,userResponse.updatedAt());


    }

    @Test
    void  getUserByEmail_WhenUserDoesNotExist_ShouldThrowException(){


        String email = "test@mail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {
                    userService.getUserByEmail(email);
                }
        );

        assertEquals("User not found with email: " + email ,exception.getMessage());
        verify(userRepository).findByEmail(email);
        verifyNoMoreInteractions(userRepository);

    }

    @Test
    void opdateUser_WhenUserExists_ShouldDeleteSuccessfully(){
        LocalDateTime expectedTime = LocalDateTime.of(2025, 2, 14, 17, 0);
        Long id = 1L;
        UpdateUserRequest userRequest = new UpdateUserRequest("Testman","UnitBoy","UnitTest.com");


        User existUser = new User();
        existUser.setId(1L);
        existUser.setEmail("test@mail.com");
        existUser.setPasswordHash("hashed");
        existUser.setFirstName("John");
        existUser.setLastName("Doe");
        existUser.setAvatarUrl("testavatarurl.com");
        existUser.setActive(true);
        existUser.setVerified(false);
        existUser.setLastLogin(expectedTime);
        existUser.setCreatedAt(expectedTime);
        existUser.setUpdatedAt(expectedTime);

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setEmail("test@mail.com");
        updatedUser.setPasswordHash("hashed");
        updatedUser.setFirstName("Testman");
        updatedUser.setLastName("UnitBoy");
        updatedUser.setAvatarUrl("UnitTest.com");
        updatedUser.setActive(true);
        updatedUser.setVerified(false);
        updatedUser.setLastLogin(expectedTime);
        updatedUser.setCreatedAt(expectedTime);
        updatedUser.setUpdatedAt(expectedTime);

        when(userRepository.findById(id)).thenReturn(Optional.of(existUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);


        UserResponse userResponse = userService.getUserById(id);

        assertEquals(1,userResponse.id().intValue());
        assertEquals("test@mail.com",userResponse.email());
        assertEquals("John",userResponse.firstName());
        assertEquals("Doe",userResponse.lastName());
        assertEquals("testavatarurl.com",userResponse.avatarUrl());
        assertFalse(userResponse.isVerified());
        assertTrue(userResponse.isActive());
        assertEquals(expectedTime,userResponse.lastLogin());
        assertEquals(expectedTime,userResponse.createdAt());
        assertEquals(expectedTime,userResponse.updatedAt());


        UserResponse updateUserResponse = userService.updateUser(id,userRequest);

        assertEquals(1,updateUserResponse.id().intValue());
        assertEquals("test@mail.com",updateUserResponse.email());
        assertEquals("Testman",updateUserResponse.firstName());
        assertEquals("UnitBoy",updateUserResponse.lastName());
        assertEquals("UnitTest.com",updateUserResponse.avatarUrl());
        assertFalse(updateUserResponse.isVerified());
        assertTrue(updateUserResponse.isActive());
        assertEquals(expectedTime,updateUserResponse.lastLogin());
        assertEquals(expectedTime,updateUserResponse.createdAt());
        assertEquals(expectedTime,updateUserResponse.updatedAt());

    }


    @Test
    void updateUser_WhenUserDoesNotExist_ShouldThrowException(){


        Long id = 1L;
        UpdateUserRequest userRequest = new UpdateUserRequest("Testman","UnitBoy","UnitTest.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {
                    userService.updateUser(id ,userRequest);
                }
        );

        assertEquals("User not found with id: " + id, exception.getMessage());

        verify(userRepository).findById(id);
        verify(userRepository, never()).save(any(User.class));
        verifyNoMoreInteractions(userRepository);

    }



    @Test
    void deleteUser_WhenUserExists_ShouldDeleteSuccessfully() {

        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);


        userService.deleteUser(id);


        verify(userRepository).existsById(id);
        verify(userRepository).deleteById(id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldThrowException() {

        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(id);
        });

        assertEquals("User not found with id: " + id, exception.getMessage());

        verify(userRepository).existsById(id);
        verify(userRepository, never()).deleteById(id);
        verifyNoMoreInteractions(userRepository);
    }
}


