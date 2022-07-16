package com.esgi.jee.apijee.apiComment.apiJee.user.repository;


import com.esgi.jee.apijee.apiComment.apiJee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
