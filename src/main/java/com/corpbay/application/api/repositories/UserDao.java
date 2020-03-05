package com.corpbay.application.api.repositories;

import com.corpbay.application.api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, Long> {
}
