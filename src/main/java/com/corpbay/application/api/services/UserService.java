package com.corpbay.application.api.services;

import com.corpbay.application.api.repositories.UserDao;
import com.corpbay.application.api.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addUser(Users user) {
        user.setCreatedDate(new Date());
        user.setUpdateDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public List<Users> getAllUsers() {
        return new ArrayList<>(userDao.findAll());
    }

    public Optional<Users> getUserById(Long id) {
        return userDao.findById(id);
    }

    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    public void updateUserById(Long id, Users newUser) {
        newUser.setId(id);
        newUser.setUpdateDate(new Date());
        userDao.save(newUser);
    }

    public boolean checkUserLogin(Users users) {
        Users user1 = userDao.findByEmail(users.getEmail());
        return bCryptPasswordEncoder.matches(users.getPassword(), user1.getPassword());
    }
}
