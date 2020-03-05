package com.corpbay.application.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="organization")
    private String organization;

    public Users(@JsonProperty("id") Long id,
                 @JsonProperty("first_name") String firstName,
                 @JsonProperty("last_name") String lastName,
                 @JsonProperty("email") String email,
                 @JsonProperty("phone_number") String phoneNumber,
                 @JsonProperty("organization") String organization) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
    }

    public Users() {
        id = 0L;
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        organization = "";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
