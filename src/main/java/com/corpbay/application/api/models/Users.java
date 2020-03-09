package com.corpbay.application.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name="name")
    @JsonProperty("name")
    @NotNull
    private String name;

    @Column(name="email")
    @JsonProperty("email")
    @NotNull
    private String email;

    @Column(name="phone_number")
    @JsonProperty("phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name="organization")
    @JsonProperty("organization")
    @NotNull
    private String organization;

    @Column(name="password")
    @JsonProperty("password")
    private String password;

    @Column
    private Date createdDate;

    @Column
    private Date updateDate;
}
