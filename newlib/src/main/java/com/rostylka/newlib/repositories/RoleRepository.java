package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
