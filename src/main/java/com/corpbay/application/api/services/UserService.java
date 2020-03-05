package com.corpbay.application.api.services;

import com.corpbay.application.api.repositories.UserDao;
import com.corpbay.application.api.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(Users user) {
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
        userDao.save(newUser);
    }
}
