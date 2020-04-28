package com.corpbay.application.api.services;

import com.corpbay.application.api.entity.Admin;
import com.corpbay.application.api.entity.LoggedInDevices;
import com.corpbay.application.api.repositories.LoggedInDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LoggedInDeviceServices {
    private final LoggedInDeviceDao loggedInDeviceDao;

    @Autowired
    public LoggedInDeviceServices(LoggedInDeviceDao loggedInDeviceDao) {
        this.loggedInDeviceDao = loggedInDeviceDao;
    }

    public LoggedInDevices addDevice(HttpServletRequest request, Admin admin) {
        LoggedInDevices logged = new LoggedInDevices();
        logged.setLocalAddress(request.getLocalAddr());
        logged.setAdmin(admin);
        logged.setLocale(request.getLocale().getDisplayCountry());
        logged.setLocalName(request.getLocalName());
        logged.setLocalPort(request.getLocalPort());
        logged.setLoggedInTime(new Date());
        logged.setRemoteAddress(request.getRemoteAddr());
        logged.setRemoteHost(request.getRemoteHost());
        logged.setRemotePort(request.getRemotePort());
        loggedInDeviceDao.save(logged);
        return logged;
    }

    public List<LoggedInDevices> getAllDevices() {
        return loggedInDeviceDao.findAll();
    }
}
