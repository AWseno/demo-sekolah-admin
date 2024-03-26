package com.demo.sekolahadmin.messaging.publisher;

import com.demo.sekolahadmin.messaging.DefaultPublisher;
import com.demo.sekolahadmin.messaging.dto.AdminRegisterMessageRequest;

public interface AdminRegisterPublisher extends DefaultPublisher {

    void publishAdminRegister(AdminRegisterMessageRequest request, String username, String from, String requestId);

}
