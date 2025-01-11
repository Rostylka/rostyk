package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(Role role);
    User findByLogin(String login);
}
