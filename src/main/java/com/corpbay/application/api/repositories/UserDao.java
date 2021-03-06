package com.corpbay.application.api.repositories;

import com.corpbay.application.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Long> {

    public Users findByEmail(String email);

    public Users findByEmailAndPassword(String email, String password);

}
