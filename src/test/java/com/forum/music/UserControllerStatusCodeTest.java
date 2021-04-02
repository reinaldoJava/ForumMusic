package com.forum.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.music.controller.UserController;
import com.forum.music.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@ComponentScan
public class UserControllerStatusCodeTest {

    private static final String URI = "http://localhost:8080/user/";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName());
    }

    @Test
    @Rollback(value = true)
    public void createUserStatusCodeCreate() throws Exception {
        mockMvc.perform(post("/user/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto("Joao da Silva","111","USER",null,"JS","a@a"))))
                .andExpect(status().isCreated());
    }
    @Test
    public void createUserStatusCodeBadRequest() throws Exception {
        mockMvc.perform(post("/user/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto(null,"111","ADMIN",null, null, null))))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void listUserStatusCodeIsOK() throws Exception {
        mockMvc.perform(get("/user/users")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    public void listUserStatusCodeInternalServerError() throws Exception {
        mockMvc.perform(get("/user/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto(null,"111","ADMIN",null, null, null))))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateUserStatusCodeBadRequest() throws Exception {
        mockMvc.perform(patch("/user/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto("Reinaldo Santos","1111111","ADMIN",null,null,null))))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Rollback(value = true)
    public void updateUserStatusCodeIsOk() throws Exception {
        mockMvc.perform(patch("/user/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto("Reinaldo Santos","1111111","ADMIN",1L,"JS","a@a"))))
                .andExpect(status().isOk());
    }

    @Test
    public void deteleUserStatusCodeBadRequest() throws Exception {
        mockMvc.perform(delete("/user/delete")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto("Reinaldo Santos","1111111","ADMIN",null, null,null))))
                .andExpect(status().isBadRequest());
    }
    @Test
    @Rollback(value = true)
    public void deteleUserStatusCodeIsOk() throws Exception {
        mockMvc.perform(delete("/user/delete")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserDto("Reinaldo Santos","1111111","ADMIN",1L,"JS","a@a"))))
                .andExpect(status().isOk());
    }
}
