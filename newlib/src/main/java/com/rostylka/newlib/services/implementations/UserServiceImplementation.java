package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {


    private UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository
     */
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Method for creation User in DataBase
     * @param userDto
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
        List<User> users = userRepository.findAll();
        List<UserDto> dtoUsers = new ArrayList<>();
        for (User user : users) {
            dtoUsers.add(UserMapper.mapToUserDto(user));
        }
        return dtoUsers;
    }

}
