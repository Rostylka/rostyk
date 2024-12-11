package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.models.Role;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for transforming Role into RoleDTO and  vice versa
 */
public class RoleMapper {
    /**
     * Method for transforming Role into UserDTo
     * @param role - Role
     * @return roleDTO - Role DTO
     */
    public static RoleDto mapToRoleDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getRoleName());
    }

    /**
     * Method for transforming roleDTO into Role
     * @param roleDto - Role DTO
     * @return role - Role
     */
    public static Role mapToRole(RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getRoleName());
    }

    /**
     * Method for transforming Role list into Role DTO list
     * @param roles list of Roles
     * @return list of Roles DTO
     */
    public static List<RoleDto> mapToRoleDtoList(List<Role> roles) {
        List<RoleDto> dtoRoles = new ArrayList<>();
        for (Role role : roles) {
            dtoRoles.add(RoleMapper.mapToRoleDto(role));
        }
        return dtoRoles;
    }

    /**
     * Method for transforming Role DTO list into Role list
     * @param dtoRoles list of Roles
     * @return list of Roles
     */
    public static List<Role> mapToRoleList(List<RoleDto> dtoRoles) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto dtoRole : dtoRoles) {
            roles.add(RoleMapper.mapToRole(dtoRole));
        }
        return roles;
    }
}
