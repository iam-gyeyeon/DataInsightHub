package com.project.datainsight.user.controller;

import com.project.datainsight.common.response.CommonResponse;
import com.project.datainsight.user.UserMessages;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
@Controller
public class UserController {
    /*
        회원 가입, 회원 조회, 비밀번호 찾기, 초기화 등 회원 관련 API
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@RequestBody UserRequest userRequest) {
        String MESSAGE = "";
        UserResponse response = userService.registUser(userRequest);
        MESSAGE = response == null ? UserMessages.USER_ALREADY_REGISTERED.getMessage() : UserMessages.USER_REGISTERED_SUCCESS.getMessage();
        CommonResponse<UserResponse> commonResponse = new CommonResponse<>(MESSAGE, 200, response);

        return ResponseEntity.ok(commonResponse);
    }


}
