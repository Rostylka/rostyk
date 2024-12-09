package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.dto.UserDto;

import java.util.List;

/** Role Service interface
 *
 */
public interface RoleService {
    /**
     * Method for creation User in DataBase
     * @param roleDto
     * @return User() create new User in DataBase
     */
    RoleDto createRole(RoleDto roleDto);

    /**
     * Method for reading all Users from DataBase
     * @return List of UserDTO from DataBase
     */
    List<RoleDto> readAllRoles();
}
