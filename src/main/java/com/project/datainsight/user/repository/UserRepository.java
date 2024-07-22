package com.project.datainsight.user.repository;

import com.project.datainsight.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
