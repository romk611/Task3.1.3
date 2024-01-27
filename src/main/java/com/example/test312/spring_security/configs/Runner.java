package com.example.test312.spring_security.configs;

import com.example.test312.spring_security.model.Role;
import com.example.test312.spring_security.model.User;
import com.example.test312.spring_security.service.RoleService;
import com.example.test312.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder encoder;

    @Autowired
    public Runner(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleService.getRole("ROLE_ADMIN") == null) {
            roleService.addRole(new Role("ROLE_ADMIN"));
        }

        if (roleService.getRole("USER") == null) {
            roleService.addRole(new Role("ROLE_USER"));
        }

        if (userService.getUserByUserName("admin") == null) {
            User sergey = new User("admin", encoder.encode("admin"), "Roman",
                "Shamsiev", (byte) 18, "admin@mail.ru");
            sergey.setRolesList(roleService.getRole("ROLE_ADMIN"));
            sergey.setRolesList(roleService.getRole("ROLE_USER"));
            userService.saveUser(sergey);
        }

        if (userService.getUserByUserName("user") == null) {
            User ivan = new User("user", encoder.encode("user"), "Dima",
                    "Petrov", (byte) 43, "user@mail.ru");
            ivan.setRolesList(roleService.getRole("ROLE_USER"));
            userService.saveUser(ivan);
        }

    }
}
