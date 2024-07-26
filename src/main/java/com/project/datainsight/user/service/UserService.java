package com.project.datainsight.user.service;

import com.project.datainsight.user.domain.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    //회원가입
    public UserResponse registUser(UserRequest user) throws Exception;

    //중복회원 확인
    public UserResponse checkUser(String userId);

    //회원수정
    public UserResponse modifyUser(String userId, UserRequest user);

    //회원삭제
    public UserResponse removeUser(String id);

}
