package com.demo.sekolahadmin.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RegisterRequest {
    private String username;
    private String phone;
    private String email;
    private String password;
    private String name;
    private Long birthday;
}
