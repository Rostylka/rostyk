package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.UserService;
import com.rostylka.newlib.utils.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {


    private UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository - User Repository
     */
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method for creation User in DataBase
     *
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
     *
     * @return List of UserDTO from DataBase
     */
    @Override
    public List<UserDto> readAllUsers() {
        return UserMapper.mapToUserDtoList(userRepository.findAll());
    }

    /**
     * Method for reading User by ID from DataBase
     *
     * @param id ID of User
     * @return User by ID
     */
    @Override
    public UserDto readUserById(int id) {
        return UserMapper.mapToUserDto(userRepository.getReferenceById(id));
    }

    /**
     * Method for Updating User in DataBase
     *
     * @param userDto update User in DataBase
     */
    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatedUser = userRepository.getReferenceById(userDto.getId());
        updatedUser.setName(userDto.getName());
        updatedUser.setSurname(userDto.getSurname());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setLogin(userDto.getLogin());
        if(!userDto.getPassword().isEmpty()) {
            updatedUser.setPassword(userDto.getPassword());
        }
        updatedUser.setBirthday(userDto.getBirthday());
        updatedUser.setRole(userDto.getRole());
        return UserMapper.mapToUserDto(userRepository.save(updatedUser));
    }

    /**
     * Method for Deleting User from DataBase
     *
     * @param userDto - User DTO
     */
    @Override
    public void delete(UserDto userDto) {
        userRepository.delete(UserMapper.mapToUser(userDto));
    }

    /**
     * Method for finding all Users with such Role
     *
     * @param role - Role
     * @return List of UserDTO with such Role
     */
    public List<UserDto> findByRole(Role role) {
        return UserMapper.mapToUserDtoList(userRepository.findByRole(role));
    }

    /**
     * Method for finding User by login
     *
     * @param login - User's login
     * @return UserDTO with such login
     */
    public UserDto findByLogin(String login) {
        return UserMapper.mapToUserDto(userRepository.findByLogin(login));
    }

    /**
     * Method for creation Users Details for Spring Security Login
     *
     * @param login User's Login
     * @return Users Details if User Login is found
     * @throws UsernameNotFoundException - Exception when User is not found
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto userByLogin = findByLogin(login);
        if (userByLogin != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails(
                    userByLogin.getId(),
                    userByLogin.getLogin(),
                    userByLogin.getPassword(),
                    userByLogin.getRole().getRoleName(),
                    true,  // accountNonExpired
                    true,  // accountNonLocked
                    true,  // credentialsNonExpired
                    true   // enabled
            );
            return customUserDetails;
        }
        throw new UsernameNotFoundException("User not found with login: " + login);
    }


    /**
     * Method for getting Custom User detail of User
     * @return CustomUserDetails or null if User isn't authorized;
     */
    public CustomUserDetails getUserDetail() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetail != "anonymousUser") {
            return (CustomUserDetails) userDetail;
        }
        else return null;
    }

    /**
     * Method for creating Link of Main Page of User
     * @return Link /{user_role}/{id} or /home
     */
    public String createLink() {
        CustomUserDetails userDetails = getUserDetail();
        if (userDetails != null) {
            String role = userDetails.getRole().toLowerCase() + "s";
            int id = userDetails.getId();
            return "/" + role + "/" + id;
        } else return "/home";
    }
    /*@Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto userByLogin = findByLogin(login);
        if (userByLogin != null) {
            var springUser = org.springframework.security.core.userdetails.User
                    .withUsername(userByLogin.getLogin())
                    .password(userByLogin.getPassword())
                    .roles(userByLogin.getRole().getRoleName())
                    .build();
            return springUser;
        }
        return null;
    }*/

}
