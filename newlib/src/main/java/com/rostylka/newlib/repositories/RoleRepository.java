package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByRoleName(String roleName);

}
