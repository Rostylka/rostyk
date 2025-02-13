package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.repositories.RoleRepository;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserServiceImplementation userServiceImplementation;

    /**
     * Constructor
     * @param roleRepository - role Repository
     */
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Method for creation Role in DataBase
     * @param roleDto - Role DTO
     * @return Role create new Role in DataBase
     */
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        if(!checkIfRolePresent(roleDto)) {
        Role role = RoleMapper.mapToRole(roleDto);
        Role createdRole = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(createdRole);
        }
        else {
            return getByRoleName(roleDto.getRoleName());
        }
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
     * @param roleDto - Role DTO
     */
    @Override
    public void delete(RoleDto roleDto) {
        for (UserDto userDto: userServiceImplementation.findByRole(RoleMapper.mapToRole(roleDto))            ) {
            userDto.setRole(null);
            userRepository.save(UserMapper.mapToUser(userDto));
        }
        roleRepository.delete(RoleMapper.mapToRole(roleDto));
    }

    /**
     * Method for getting Role by Role name
     * @param name - Role name
     * @return - Role DTO with this name
     */
    public RoleDto getByRoleName(String name) {
        return RoleMapper.mapToRoleDto(roleRepository.getRoleByRoleName(name));
    }

    /**
     * Method for checking if role is present in Database
     * @param roleDto - Role DTO
     * @return true if Role is present in Data Base
     */
    public boolean checkIfRolePresent(RoleDto roleDto) {
        for(RoleDto role: readAllRoles()) {
            if (role.equals(roleDto)) {
                return true;
            }
        }
        return false;
    }



    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
