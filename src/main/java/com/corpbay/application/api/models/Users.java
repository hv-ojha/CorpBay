package com.corpbay.application.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name="last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name="email")
    @JsonProperty("email")
    private String email;

    @Column(name="phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column(name="organization")
    @JsonProperty("organization")
    private String organization;

    @Column(name="password")
    @JsonProperty("password")
    private String password;

    @Column
    private Date createdDate;

    @Column
    private Date updateDate;
}
