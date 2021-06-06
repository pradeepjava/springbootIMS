package com.ims.jwt;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class UserDBService {
	static User dummyUser = new User(Integer.MAX_VALUE, "", "", "", "", "", new Date());
	@Autowired
	UserRepoForUserTable userRepo;

	public User getUserByUserName(String userName) {
		return getUserByUserId(userRepo.findByUserName(userName).getUserId());
	}

	public User getUserByUserId(Integer userId) {
		return userRepo.findById(userId).orElse(dummyUser);
	}

	public void save(User userDetails) {
		userRepo.save(userDetails);
	}

	public JwtUserDetails getJWTUserByUserName(String userName) {
		User user = getUserByUserId(userRepo.findByUserName(userName).getUserId());
		if (user.getUserId().equals(Integer.MAX_VALUE)) {
			return null;
		}
		return new JwtUserDetails(user.getUserId(), user.getUserName(), user.getPassword(), user.getRole(),
				user.getStatus());

	}
}
