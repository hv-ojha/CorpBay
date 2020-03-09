package com.corpbay.application.api.services;

import com.corpbay.application.api.repositories.UserDao;
import com.corpbay.application.api.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        userDao.save(user);
        return user;
    }

    public List<Users> getAllUsers() throws Exception {
        List<Users> users =  userDao.findAll();
        if(users.isEmpty())
            throw new Exception("No User Exist");
        return users;
    }

//    public Users getUserById(Long id) throws Exception {
//        Optional<Users> user = userDao.findById(id);
//        if(!user.isPresent()) {
//            throw new Exception("No User exist with this id");
//        }
//        return user.get();
//    }

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
            if(bCryptPasswordEncoder.matches(users.getPassword(), user1.getPassword()))
                return user1;
            else
                throw new Exception("Invalid Password");
        }
    }

    public Users getUserByEmail(String email) throws Exception {
        Users user = userDao.findByEmail(email);
        if(user == null)
            throw new Exception("No user exist");
        else
            return user;
    }
}
