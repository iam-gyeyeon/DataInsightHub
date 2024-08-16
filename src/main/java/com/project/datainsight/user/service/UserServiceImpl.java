package com.project.datainsight.user.service;

import com.project.datainsight.redis.RedisUtil;
import com.project.datainsight.security.RSAUtil;
import com.project.datainsight.user.dao.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RedisUtil redis;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registUser(UserRequest requestUser) throws Exception {

        User registUser = new User(requestUser);

        // 중복 확인
        User checkUser = userRepository.findByUserId(registUser.getUserId());
        if (checkUser != null && checkUser.getUserId().equals(registUser.getUserId())) {
            logger.debug("User with userId {} already exists", registUser.getUserId());
            return null;
        }

        //비밀번호 암호화
        registUser.setPassword(encryptPassword(registUser.getPassword()));

        User resultUser = userRepository.save(registUser);
       return new UserResponse(resultUser);
    }

    private String encryptPassword(String password) {
        //추후 salt key 추가
        return passwordEncoder.encode(password);
    }

    private PublicKey initKey(){

        String uuid = UUID.randomUUID().toString();
        KeyPair generator = null;
        PublicKey publicKey = null;
        PrivateKey privateKey = null;
        try{
            generator = RSAUtil.generateKeyPair(1024);
            publicKey = generator.getPublic();
            privateKey = generator.getPrivate();

            redis.setData(uuid, privateKey.toString(), 1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return publicKey;
    }



    @Override
    public UserResponse checkUser(String userId) {
        User resultUser = userRepository.findByUserId(userId);
        if(resultUser == null) { return null; }
        return new UserResponse(resultUser);
    }

    @Override
    public UserResponse modifyUser(String userId, UserRequest user) {
        User findUser = userRepository.findByUserId(userId);
        if(findUser == null) { return null; }

        findUser.updateFromRequest(user);
        User resultUser = userRepository.save(findUser);

        return new UserResponse(resultUser);
    }

    @Override
    public UserResponse removeUser(String id) {
        User findUser = userRepository.findByUserId(id);
        if(findUser == null) { return null; }

        userRepository.delete(findUser);
        return new UserResponse(findUser);
    }

}
