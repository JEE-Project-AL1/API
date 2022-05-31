package com.esgi.jee.apijee.user.infrastructure;

import com.esgi.jee.apijee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
    User findFirstById(Long id);




}
