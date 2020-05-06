package com.corpbay.application.api.services;

import com.corpbay.application.api.repositories.UserDao;
import com.corpbay.application.api.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Users addUser(Users user) throws Exception {
        if(userDao.findByEmail(user.getEmail()) != null)
            throw new Exception("User already exist");
        user.setCreatedDate(new Date());
        user.setUpdateDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        userDao.save(user);
        return user;
    }

    public List<Users> getAllUsers() throws Exception {
        List<Users> users =  userDao.findAll();
        if(users.isEmpty())
            throw new Exception("No User Exist");
        return users;
    }

    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    public void updateUserById(Long id, Users newUser) {
        newUser.setId(id);
        newUser.setUpdateDate(new Date());
        userDao.save(newUser);
    }

    public Users checkUserLogin(Users users) throws Exception {
        Users user1 = userDao.findByEmail(users.getEmail());
        if(user1 == null)
            throw new Exception("No user exist");
        else {
            if(bCryptPasswordEncoder.matches(users.getPassword(), user1.getPassword()) && user1.getVerified())
                return user1;
            else {
                if(!user1.getVerified())
                    throw new Exception("Please Verify your email to login in the app");
                else
                    throw new Exception("Invalid Password");
            }
        }
    }

    public Users getUserByEmail(String email) throws Exception {
        Users user = userDao.findByEmail(email);
        if(user == null)
            throw new Exception("No user exist");
        else
            return user;
    }

    public Users getUserByEmailAndPassword(String email, String password) throws Exception {
        Users user = userDao.findByEmailAndPassword(email,password);
        if(user == null)
            throw new Exception("No user exist");
        else {
            user.setVerified(true);
            userDao.save(user);
            return user;
        }
    }
}
