package com.seis739.gourmetcompass.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonSerialize
public class UserSessionDTO {
    private String token;
    private LocalDateTime expires;
}
