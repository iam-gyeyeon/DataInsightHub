package com.project.datainsight.user.service;

import com.project.datainsight.user.domain.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void registUser(){
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("admin");
        userRequest.setPassword("admin");
        userRequest.setId("admin");

        //when
        UserResponse user = userService.registUser(userRequest);

        //then
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo("admin");
    }

}