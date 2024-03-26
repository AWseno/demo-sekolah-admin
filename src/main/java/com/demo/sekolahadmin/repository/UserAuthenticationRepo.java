package com.demo.sekolahadmin.repository;

import com.demo.sekolahadmin.model.UserAuthentication;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthenticationRepo extends CrudRepository<UserAuthentication, String> {
}
