package com.learnhub.playgound.user;


import com.learnhub.playgound.user.domain.User;
import com.learnhub.playgound.user.dto.CreateUserRequest;
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
    void whenCreateUserThenSuccess(){

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


