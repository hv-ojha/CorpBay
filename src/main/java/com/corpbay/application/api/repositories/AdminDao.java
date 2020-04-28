package com.corpbay.application.api.repositories;

import com.corpbay.application.api.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin, String> {
    Admin findByUsername(String username);
}
