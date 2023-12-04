package com.example.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.model.UserPojo;

public interface UserRepo extends JpaRepository< UserPojo, Long>{
	UserPojo findByEmail(String email);
}
