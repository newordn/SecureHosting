package com.neword.demo.services;

import com.neword.demo.models.Role;
import com.neword.demo.models.User;
import com.neword.demo.repositories.RoleRepository;
import com.neword.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {



    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        if (userRole==null){
            Role role=new Role();
            role.setRole("ADMIN");
            roleRepository.save(role);
            user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ADMIN"))));

        }else {
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }
        userRepository.save(user);
    }

}
