package com.rostylka.newlib;

import com.rostylka.newlib.dao.UserDao;
import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.UserService;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewlibApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewlibApplication.class, args);

    }

}
