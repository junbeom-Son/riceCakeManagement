package com.passage.management.repository;

import com.passage.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByLoginIdAndPassword(String loginId, String password);
    public boolean existsByLoginId(String loginId);
}
