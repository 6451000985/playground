package com.learnhub.playgound.user;


import com.learnhub.playgound.config.JwtService;
import com.learnhub.playgound.user.controller.UserController;
import com.learnhub.playgound.user.domain.User;
import com.learnhub.playgound.user.dto.UserResponse;
import com.learnhub.playgound.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UserController.class)
 public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    void WhenGetUserById_ThenSuccessfully() throws Exception{

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

        when(userService.getUserById(id))
                .thenReturn(UserResponse.fromEntity(savedUser));
        mockMvc.perform(
                get(String.format("/api/users/%d",1))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@mail.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.avatarUrl").value("testavatarurl.com"))
                .andExpect(jsonPath("$.isActive").value("true"))
                .andExpect(jsonPath("$.isVerified").value("false"));

        verify(userService, times(1)).getUserById(id);
    }

    @Test
    @WithMockUser
    void WhenGetUserByEmail_ThenSuccessfully() throws Exception{

        String email = "test@mail.com";

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

        when(userService.getUserByEmail(email))
                .thenReturn(UserResponse.fromEntity(savedUser));
        mockMvc.perform(
                        get(String.format("/api/users/email/%s","test@mail.com"))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@mail.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.avatarUrl").value("testavatarurl.com"))
                .andExpect(jsonPath("$.isActive").value("true"))
                .andExpect(jsonPath("$.isVerified").value("false"));

        verify(userService, times(1)).getUserByEmail(email);


    }


    @Test
    @WithMockUser
    void DeleteUser_ThenSuccessfully() throws Exception{
        Long id = 1L;

        mockMvc.perform(
                        delete(String.format("/api/users/%d",id))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(id);

    }
}
