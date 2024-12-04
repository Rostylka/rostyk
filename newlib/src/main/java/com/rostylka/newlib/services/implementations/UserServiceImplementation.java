package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User createdUser = userRepository.save(user);
        return UserMapper.mapToUserDto(createdUser);
    }
}
