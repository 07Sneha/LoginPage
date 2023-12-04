package com.example.login.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.login.model.UserPojo;
import com.example.login.web.dto.DtoClass;

public interface UserService  extends UserDetailsService {
	UserPojo save(DtoClass dc);
}
