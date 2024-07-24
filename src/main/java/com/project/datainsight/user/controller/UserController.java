package com.project.datainsight.user.controller;

import com.project.datainsight.common.response.CommonResponse;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.project.datainsight.user.UserMessages.*;

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
        MESSAGE = response == null ? USER_ALREADY_REGISTERED.getMessage() : USER_REGISTERED_SUCCESS.getMessage();

        return createResponse(MESSAGE, response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> checkUser(@PathVariable String userId){
        String MESSAGE = "";
        UserResponse response = userService.checkUser(userId);
        MESSAGE = response == null ? USER_NOT_FOUND.getMessage(): USER_ALREADY_REGISTERED.getMessage();
        return createResponse(MESSAGE, response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest){
        String MESSAGE = "";
        UserResponse response = userService.modifyUser(userId, userRequest);
        MESSAGE = response == null ? USER_NOT_FOUND.getMessage() : USER_MODIFIED_SUCCESS.getMessage();
        return createResponse(MESSAGE, response);
    }

    private ResponseEntity<CommonResponse<UserResponse>> createResponse(String message, UserResponse response) {
        CommonResponse<UserResponse> commonResponse = new CommonResponse<>(message, 200, response);
        return ResponseEntity.ok(commonResponse);
    }


}
