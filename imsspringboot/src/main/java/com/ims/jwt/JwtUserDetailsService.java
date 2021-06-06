package com.ims.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserDBService userDBService;
//	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
//
//	static {
//		inMemoryUserList.add(new JwtUserDetails(1, "in28minutes",
//				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//		inMemoryUserList.add(new JwtUserDetails(2, "ranga",
//				"$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm", "ROLE_USER_2"));
//		
//		//$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUserDetails jwtUser = userDBService.getJWTUserByUserName(username);

		if (jwtUser != null) {
			return jwtUser;
		}
		throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	}

}