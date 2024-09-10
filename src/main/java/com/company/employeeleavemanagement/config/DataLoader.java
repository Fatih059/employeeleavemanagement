package com.company.employeeleavemanagement.config;

import com.company.employeeleavemanagement.model.User;
import com.company.employeeleavemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("1");
            user.setPassword("Password");
            user.setRole("admin");
            userRepository.save(user);
        }
    }
}
