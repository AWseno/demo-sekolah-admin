package com.demo.sekolahadmin.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MessagingConfig {

    @Bean("adminExchange")
    TopicExchange adminExchange() {
        return new TopicExchange(QueueEvent.EXCHANGE);
    }

    @Bean("adminQueue")
    List<Queue> adminQueue() {
        return Collections.singletonList(
                new Queue(QueueEvent.Admin.INACTIVE)
        );
    }

    @Bean("adminBindingTo")
    public List<Binding> adminBindingTo() {
        return Collections.singletonList(
                new Binding(QueueEvent.Admin.INACTIVE, Binding.DestinationType.QUEUE, QueueEvent.EXCHANGE, QueueEvent.Admin.INACTIVE, null)
        );
    }

}
