package com.corpbay.application.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggedInDevices {
    @Id
    private String remoteAddress;

    @ManyToOne
    private Admin admin;

    private String remoteHost;

    private Integer remotePort;

    private String locale;

    private String localAddress;

    private Integer localPort;

    private String localName;

    private Date loggedInTime;
}
