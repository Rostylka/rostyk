package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.models.Role;


/**
 * Class for transforming Role into RoleDTO and  vice versa
 */
public class RoleMapper {
    /**
     * Method for transforming Role into UserDTo
     * @param role
     * @return roleDTO
     */
    public static RoleDto mapToRoleDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getRoleName());
    }

    /**
     * Method for transforming roleDTO into Role
     * @param roleDto
     * @return role
     */
    public static Role mapToRole(RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getRoleName());
    }
}
