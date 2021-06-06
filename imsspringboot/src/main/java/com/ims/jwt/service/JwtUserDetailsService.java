package com.ims.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ims.jwt.dto.JwtUserDetails;
import com.ims.userdetails.UserDBService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserDBService userDBService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUserDetails jwtUser = userDBService.getJWTUserByUserName(username);

		if (jwtUser != null) {
			return jwtUser;
		}
		throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	}

}