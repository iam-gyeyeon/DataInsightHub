package com.project.datainsight.user.service;

import com.project.datainsight.user.domain.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registUser(UserRequest requestUser) throws Exception {
        // RSA 키 쌍 생성
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();

        User registUser = new User(requestUser);

        // 중복 확인
        User checkUser = userRepository.findByUserId(registUser.getUserId());
        if (checkUser != null && checkUser.getUserId().equals(registUser.getUserId())) {
            logger.debug("User with userId {} already exists", registUser.getUserId());
            return null;
        }

        String password = encryptPassword(requestUser.getPassword(), publicKey, 80);
        registUser.setPassword(password);

        User resultUser = userRepository.save(registUser);
       return new UserResponse(resultUser);
    }
    private String encryptPassword(String password, PublicKey publicKey, int length) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(password.getBytes());
        String encryptedString = Base64.getEncoder().encodeToString(encryptedData);
        return encryptedString.substring(0, Math.min(length, encryptedString.length()));
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
