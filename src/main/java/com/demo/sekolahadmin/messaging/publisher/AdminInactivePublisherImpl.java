package com.demo.sekolahadmin.messaging.publisher;

import com.demo.sekolahadmin.messaging.QueueEvent;
import com.demo.sekolahadmin.messaging.dto.AdminInactiveMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInactivePublisherImpl implements AdminInactivePublisher {

    private final AmqpTemplate amqpTemplate;

    @Override
    public void publishAdminInactive(AdminInactiveMessageRequest request, String username, String from, String requestId) {
        log.info(String.format("Publish Register Admin with username='%s', requestId='%s'", request.getUsername(), requestId));
        amqpTemplate.convertAndSend(QueueEvent.EXCHANGE, QueueEvent.Admin.INACTIVE, request, message -> this.generateHeader(message, requestId, username, from));
    }
}
