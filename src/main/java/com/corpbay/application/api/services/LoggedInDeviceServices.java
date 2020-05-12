package com.corpbay.application.api.services;

import com.corpbay.application.api.entity.LoggedInDevices;
import com.corpbay.application.api.repositories.LoggedInDeviceDao;
import eu.bitwalker.useragentutils.UserAgent;
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

    public LoggedInDevices addDevice(HttpServletRequest request, String user) {
        LoggedInDevices logged = new LoggedInDevices();

        //Device Details
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        if(request.getHeader("user-agent").toString().contains("Android 10"))
            logged.setOperatingSystem("Android 10");
        else if(request.getHeader("user-agent").toString().contains("Android 9"))
            logged.setOperatingSystem("Android 9");
        else
            logged.setOperatingSystem(userAgent.getOperatingSystem().getName());
        logged.setOperatingSystemType(userAgent.getOperatingSystem().getGroup().getName());
        logged.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());
        logged.setBrowserType(userAgent.getBrowser().getBrowserType().getName());
        logged.setBrowserName(userAgent.getBrowser().getName());

        // Header details
        logged.setUsername(user);
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
