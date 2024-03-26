package com.demo.sekolahadmin.dto.request;

import lombok.Data;

@Data
public class UpdateRequest {
    private String username;
    private String name;
    private Long birthday;
}
