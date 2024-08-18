package com.project.datainsight.login.controller;

import com.project.datainsight.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
@Controller
public class LoginController {
    /*
         로그인 api
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        String MESSAGE = "";
        return null;
    }
}
