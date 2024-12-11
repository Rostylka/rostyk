package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {


    private UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository - User Repository
     */
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Method for creation User in DataBase
     * @param userDto - user DTO
     * @return User() create new User in DataBase
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User createdUser = userRepository.save(user);
        return UserMapper.mapToUserDto(createdUser);
    }

    /**
     * Method for reading all Users from DataBase
     * @return List of UserDTO from DataBase
     */
    @Override
    public List<UserDto> readAllUsers() {
        return UserMapper.mapToUserDtoList(userRepository.findAll());
    }

    /**
     * Method for reading User by ID from DataBase
     * @param id ID of User
     * @return User by ID
     */
    @Override
    public UserDto readUserById(int id) {
        return UserMapper.mapToUserDto(userRepository.getReferenceById(id));
    }

    /**
     * Method for Updating User in DataBase
     * @param userDto
     * update User in DataBase
     */
    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatedUser = userRepository.getReferenceById(userDto.getId());
        updatedUser.setName(userDto.getName());
        updatedUser.setSurname(userDto.getSurname());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setLogin(userDto.getLogin());
        updatedUser.setPassword(userDto.getPassword());
        updatedUser.setBirthday(userDto.getBirthday());
        updatedUser.setRole(userDto.getRole());
        updatedUser.setBooks(userDto.getBooks());
        return UserMapper.mapToUserDto(userRepository.save(updatedUser));
    }

    /**
     * Method for Deleting User from DataBase
     * @param userDto - User DTO
     */
    @Override
    public void delete(UserDto userDto) {
        userRepository.delete(UserMapper.mapToUser(userDto));
    }


}
