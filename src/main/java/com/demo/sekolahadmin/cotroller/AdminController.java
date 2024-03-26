package com.demo.sekolahadmin.cotroller;

import com.demo.sekolahadmin.dto.request.RegisterRequest;
import com.demo.sekolahadmin.dto.request.UpdateRequest;
import com.demo.sekolahadmin.http.response.MessageResponse;
import com.demo.sekolahadmin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Register success",
                adminService.register(registerRequest, httpServletRequest.getRemoteAddr()), null), HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessageResponse> update(@Valid @RequestBody UpdateRequest updateRequest, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Register success",
                adminService.update(updateRequest, httpServletRequest.getRemoteAddr()), null), HttpStatus.OK);
    }

    @DeleteMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessageResponse> inactive(@RequestParam("username") String username, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Register success",
                adminService.inactive(username, httpServletRequest.getRemoteAddr()), null), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessageResponse> get(@PathVariable("username") String username, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Register success",
                adminService.get(username), null), HttpStatus.OK);
    }

}
