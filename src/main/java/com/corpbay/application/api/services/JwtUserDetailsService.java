package com.corpbay.application.api.services;

import com.corpbay.application.api.entity.Admin;
import com.corpbay.application.api.entity.Users;
import com.corpbay.application.api.repositories.AdminDao;
import com.corpbay.application.api.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userRepository;

    @Autowired
    private AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminDao.findByUsername(email);
        if(admin != null)
            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
                    new ArrayList<>());
        else {
            Users user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    new ArrayList<>());
        }
    }
}
