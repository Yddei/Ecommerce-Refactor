package com.portfolio.ebookshop.repos;

import com.portfolio.ebookshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameContainingIgnoreCase(String keyword);
    User findByEmail(String email);
}