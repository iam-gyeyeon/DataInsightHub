package com.project.datainsight.user.service;

import com.project.datainsight.user.domain.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registUser(UserRequest requestUser) {
        User registUser = new User(requestUser);

        //중복 확인
        User checkUser = userRepository.findByUserId(registUser.getUserId());

        if (checkUser != null && checkUser.getUserId().equals(registUser.getUserId())) {
            logger.debug("User with userId {} already exists", registUser.getUserId());
            return null;
        }
        User resultUser = userRepository.save(registUser);

       return new UserResponse(resultUser);
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
        return null;
    }
}
