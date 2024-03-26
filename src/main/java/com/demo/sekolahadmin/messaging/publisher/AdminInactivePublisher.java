package com.demo.sekolahadmin.messaging.publisher;

import com.demo.sekolahadmin.messaging.DefaultPublisher;
import com.demo.sekolahadmin.messaging.dto.AdminInactiveMessageRequest;

public interface AdminInactivePublisher extends DefaultPublisher {

    void publishAdminInactive(AdminInactiveMessageRequest request, String username, String from, String requestId);
}
