package com.seis739.gourmetcompass.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    T data;
    String status;
    String error;

    public ApiResponse(T data) {
        this.data = data;
        this.status = "success";
    }

    public ApiResponse(String error) {
        this.status = "fail";
        this.error = error;
    }
}
