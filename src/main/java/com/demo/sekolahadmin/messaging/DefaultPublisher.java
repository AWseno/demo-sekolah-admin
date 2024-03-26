package com.demo.sekolahadmin.messaging;

import org.springframework.amqp.core.Message;

public interface DefaultPublisher {
    default Message generateHeader(Message message , String requestId , String username , String from ){
        message.getMessageProperties().getHeaders().put("X-REQUEST-ID", requestId);
        message.getMessageProperties().getHeaders().put("X-USERNAME", username);
        message.getMessageProperties().getHeaders().put("X-FROM", from);
        return message;
    }
}
