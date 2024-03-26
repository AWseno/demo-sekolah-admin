package com.demo.sekolahadmin.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BasicResponse {
    private Long id;
    private String username;
    private String name;
    private Date birthday;
    private Character recordStatus;
}
