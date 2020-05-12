package com.corpbay.application.api.repositories;

import com.corpbay.application.api.entity.LoggedInDevices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedInDeviceDao extends JpaRepository<LoggedInDevices, String> {
    LoggedInDevices findByRemoteAddress(String remoteAddress);
}
