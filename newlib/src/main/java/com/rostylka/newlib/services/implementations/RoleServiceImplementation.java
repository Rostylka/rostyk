package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.repositories.RoleRepository;
import com.rostylka.newlib.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    /**
     * Constructor
     * @param roleRepository
     */
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Method for creation Role in DataBase
     * @param roleDto
     * @return Role create new Role in DataBase
     */
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
        Role createdRole = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(createdRole);
    }

    /**
     * Method for reading all Roles from DataBase
     * @return List of RoleDTO from DataBase
     */
    @Override
    public List<RoleDto> readAllRoles() {
        return RoleMapper.mapToRoleDtoList(roleRepository.findAll());
    }


    /**
     * Method for reading Role by ID from DataBase
     * @param id ID of Role
     * @return Role by ID
     */
    @Override
    public RoleDto readRoleById(int id) {
        return RoleMapper.mapToRoleDto(roleRepository.getReferenceById(id));
    }

    /**
     * Method for Updating Role in DataBase
     * @param roleDto
     * update Role in DataBase
     */
    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        Role updatedRole = roleRepository.getReferenceById(roleDto.getId());
        updatedRole.setRoleName(roleDto.getRoleName());
        return RoleMapper.mapToRoleDto(roleRepository.save(updatedRole));
    }

    /**
     * Method for Deleting Role from DataBase
     * @param roleDto
     */
    @Override
    public void delete(RoleDto roleDto) {
        roleRepository.delete(RoleMapper.mapToRole(roleDto));
    }
}
