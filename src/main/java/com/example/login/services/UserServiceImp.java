package com.example.login.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.login.model.RolePojo;
import com.example.login.model.UserPojo;
import com.example.login.repo.UserRepo;
import com.example.login.web.dto.DtoClass;

public class UserServiceImp implements UserService  {
	
	//instead of autowired we can inject repo with this 
	private UserRepo userRepo;
	public UserServiceImp(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserPojo save(DtoClass ditoobject) {
		UserPojo user = new UserPojo(ditoobject.getFirstName(), 
				ditoobject.getLastName(), ditoobject.getEmail(),
				passwordEncoder.encode(	ditoobject.getPassword()), Arrays.asList(new RolePojo("ROLE_USER")));
//passwordEncoder is to store password in encoded form in mysql 
		
		return userRepo.save(user);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserPojo user =userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}

	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RolePojo> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
