package com.demo.sekolahadmin.service;

import com.demo.sekolahadmin.dto.request.RegisterRequest;
import com.demo.sekolahadmin.dto.request.UpdateRequest;
import com.demo.sekolahadmin.dto.response.BasicResponse;

public interface AdminService {

    BasicResponse register(RegisterRequest request, String from);

    BasicResponse update(UpdateRequest request, String from);

    BasicResponse inactive(String username, String from);

    BasicResponse get(String username);
}
