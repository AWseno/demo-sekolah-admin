package com.demo.sekolahadmin.messaging.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminInactiveMessageRequest {

    private String username;
}
