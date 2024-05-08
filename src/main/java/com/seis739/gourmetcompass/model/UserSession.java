package com.seis739.gourmetcompass.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seis739.gourmetcompass.dto.UserSessionDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_sessions")
public class UserSession {
    @Id
    @JsonProperty(value="id")
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty(value="userId")
    @Column(name = "user_id")
    private int userId;

    @JsonProperty(value="token")
    @Column(name = "token")
    private String token;

    @JsonProperty(value="expires")
    @Column(name = "expires")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expires;

    @JsonProperty(value="createdAt")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public UserSession() {
        createdAt = LocalDateTime.now();
    }

    public UserSessionDTO getPublicFormat() {
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setExpires(this.expires);
        userSessionDTO.setToken(this.token);
        userSessionDTO.setUserId(this.userId);
        
        return userSessionDTO;
    }
}
