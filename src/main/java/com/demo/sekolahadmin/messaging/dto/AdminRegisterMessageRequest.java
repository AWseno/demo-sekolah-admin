package com.demo.sekolahadmin.messaging.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminRegisterMessageRequest {

    private String username;
    private String password;
    private String phone;
    private String email;
}
