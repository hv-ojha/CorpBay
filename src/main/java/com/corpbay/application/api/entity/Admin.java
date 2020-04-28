package com.corpbay.application.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    @Id
    private String username;

    private String password;

    private String name;

    private Date createdDate;

    private Date updatedDate;

    private Date lastLoginDate;
}
