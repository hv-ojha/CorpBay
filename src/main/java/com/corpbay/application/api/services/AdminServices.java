package com.corpbay.application.api.services;

import com.corpbay.application.api.entity.Admin;
import com.corpbay.application.api.repositories.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    private final AdminDao adminDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminServices(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Admin addAdmin(Admin admin) throws Exception {
        try {
            Admin admin1 = getAdmin(admin.getUsername());
            throw new Exception("Username already exist. Please try other one");
        } catch(Exception ex) {
            admin.setCreatedDate(new Date());
            admin.setUpdatedDate(new Date());
            admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            adminDao.save(admin);
            return admin;
        }
    }

    public Admin getAdmin(String username) {
        Optional<Admin> optionalAdmin = adminDao.findById(username);
        //            throw new Exception("No Admin found with this username");
        return optionalAdmin.orElse(null);
    }

    public List<Admin> getAllAdmin() {
        return adminDao.findAll();
    }

    public Admin adminLogin(String username, String password) throws Exception {
        Admin admin = getAdmin(username);
        if(bCryptPasswordEncoder.matches(password,admin.getPassword())) {
            return admin;
        } else {
            throw new Exception("Incorrect password");
        }
    }
}
