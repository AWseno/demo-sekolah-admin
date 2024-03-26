package com.demo.sekolahadmin.repository;

import com.demo.sekolahadmin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}
