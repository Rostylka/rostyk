package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.dto.UserDto;

import java.util.List;

/** Role Service interface
 *
 */
public interface RoleService {
    /**
     * Method for creation Role in DataBase
     * @param roleDto
     * @return Role() create new Role in DataBase
     */
    RoleDto createRole(RoleDto roleDto);

    /**
     * Method for reading all Roles from DataBase
     * @return List of Roles DTO from DataBase
     */
    List<RoleDto> readAllRoles();

    /**
     * Method for reading Role by ID from DataBase
     * @param id ID of Role
     * @return Role by ID
     */
    RoleDto readRoleById(int id);

    /**
     * Method for Updating Role in DataBase
     * @param roleDto
     * @return updated Role
     */
    RoleDto updateRole(RoleDto roleDto);

    /**
     * Method for Deleting Role from DataBase
     * @param roleDto
     */
    void delete(RoleDto roleDto);
}
