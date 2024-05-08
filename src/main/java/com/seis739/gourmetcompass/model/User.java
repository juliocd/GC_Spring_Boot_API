package com.seis739.gourmetcompass.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seis739.gourmetcompass.dto.UserDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @JsonProperty(value="id")
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty(value="firstFame")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty(value="lastName")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty(value="email")
    @Column(name = "email")
    private String email;

    @JsonProperty(value="password")
    @Column(name = "password")
    private String password;

    @JsonProperty(value="createdAt")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @JsonProperty(value="deletedAt")
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    public UserDTO getPublicUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setEmail(this.email);
        userDTO.setFirstName(this.firstName);
        userDTO.setLastName(this.lastName);

        return userDTO;
    }
}
