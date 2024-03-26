package com.demo.sekolahadmin.service;

import com.demo.sekolahadmin.dto.request.RegisterRequest;
import com.demo.sekolahadmin.dto.request.UpdateRequest;
import com.demo.sekolahadmin.dto.response.BasicResponse;
import com.demo.sekolahadmin.entity.Admin;
import com.demo.sekolahadmin.http.exception.AdminException;
import com.demo.sekolahadmin.messaging.dto.AdminInactiveMessageRequest;
import com.demo.sekolahadmin.messaging.dto.AdminRegisterMessageRequest;
import com.demo.sekolahadmin.messaging.publisher.AdminInactivePublisher;
import com.demo.sekolahadmin.messaging.publisher.AdminRegisterPublisher;
import com.demo.sekolahadmin.repository.AdminRepo;
import com.demo.sekolahadmin.repository.UserAuthenticationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    private final AdminInactivePublisher adminInactivePublisher;
    private final AdminRegisterPublisher adminRegisterPublisher;

    @Override
    public BasicResponse register(RegisterRequest request, String from) {
        validateRegister(request);
        Admin savedAdmin = register(request);
        publishRegister(request, from);
        return BasicResponse.builder()
                .id(savedAdmin.getId())
                .username(savedAdmin.getUsername())
                .name(savedAdmin.getName())
                .birthday(savedAdmin.getBirthday())
                .recordStatus(savedAdmin.getRecordStatus())
                .build();
    }

    private void publishRegister(RegisterRequest request, String from) {
        AdminRegisterMessageRequest message = AdminRegisterMessageRequest.builder()
                .username(request.getUsername())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        adminRegisterPublisher.publishAdminRegister(message, "SYSTEM", from, UUID.randomUUID().toString());
    }

    private Admin register(RegisterRequest request) {
        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setBirthday(new Date(request.getBirthday()));
        return adminRepo.save(admin);
    }

    private void validateRegister(RegisterRequest request) {
        // auth lib validate phone
        // auth lib validate email
        // auth lib validate username
    }

    @Override
    public BasicResponse update(UpdateRequest request, String from) {
        Admin exist = validateUpdate(request);
        Admin updatedAdmin = update(exist, request);
        return BasicResponse.builder()
                .username(updatedAdmin.getUsername())
                .name(updatedAdmin.getName())
                .id(updatedAdmin.getId())
                .birthday(updatedAdmin.getBirthday())
                .recordStatus(updatedAdmin.getRecordStatus())
                .build();
    }

    private Admin update(Admin exist, UpdateRequest request) {
        exist.setName(request.getName());
        exist.setBirthday(new Date(request.getBirthday()));
        return adminRepo.save(exist);
    }

    private Admin validateUpdate(UpdateRequest request) {
        Optional<Admin> admin = adminRepo.findByUsername(request.getUsername());
        if (admin.isEmpty()) {
            log.info("Admin " + request.getUsername() + " not found");
            throw new AdminException("400", "Not found", "Admin not found");
        }
        return admin.get();
    }

    @Override
    public BasicResponse inactive(String username, String from) {
        Admin exist = validateInactive(username);
        if (!exist.getRecordStatus().equals('I')) inactive(exist);
        publishInactive(exist, from);
        return BasicResponse.builder()
                .id(exist.getId())
                .birthday(exist.getBirthday())
                .name(exist.getName())
                .recordStatus(exist.getRecordStatus())
                .username(exist.getUsername())
                .build();
    }

    @Override
    public BasicResponse get(String username) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isEmpty()) {
            return BasicResponse.builder().build();
        }
        return BasicResponse.builder()
                .id(admin.get().getId())
                .username(admin.get().getUsername())
                .name(admin.get().getName())
                .birthday(admin.get().getBirthday())
                .recordStatus(admin.get().getRecordStatus())
                .build();
    }

    private void publishInactive(Admin exist, String from) {
        AdminInactiveMessageRequest message = AdminInactiveMessageRequest.builder()
                .username(exist.getUsername())
                .build();
        adminInactivePublisher.publishAdminInactive(message, "SYSTEM", from, UUID.randomUUID().toString());
    }

    private void inactive(Admin exist) {
        exist.setRecordStatus('I');
        adminRepo.save(exist);
    }

    private Admin validateInactive(String username) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isEmpty()) {
            log.info("Admin " + username + " not found");
            throw new AdminException("400", "Not found", "Admin not found");
        }
        return admin.get();
    }
}
