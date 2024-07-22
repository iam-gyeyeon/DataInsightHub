package com.project.datainsight.user.service;

import com.project.datainsight.user.domain.User;
import com.project.datainsight.user.dto.UserRequest;
import com.project.datainsight.user.dto.UserResponse;
import com.project.datainsight.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registUser(UserRequest requestUser) {
        User registUser = new User(requestUser);
        User resultUser = userRepository.save(registUser);

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
