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
    public UserResponse checkUser(UserRequest userRequest) {
        User checkUser = new User(userRequest);
        User resultUser = userRepository.findByUserId(checkUser.getUserId());

        return new UserResponse(resultUser);
    }

    @Override
    public UserResponse modifyUser(UserRequest user) {
        return null;
    }

    @Override
    public UserResponse removeUser(String id) {
        return null;
    }
}
