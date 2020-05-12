package com.corpbay.application.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggedInDevices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String remoteAddress;

    private String username;

    private String remoteHost;

    private Integer remotePort;

    private String operatingSystem;

    private String operatingSystemType;

    private String deviceType;

    private String browserName;

    private String browserType;

    private Date loggedInTime;
}
