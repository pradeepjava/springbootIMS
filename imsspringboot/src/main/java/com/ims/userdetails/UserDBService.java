package com.ims.userdetails;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.jwt.dto.JwtUserDetails;




@Service
@Transactional
public class UserDBService {
	static User dummyUser = new User(Integer.MAX_VALUE, "", "", "", "", "", new Date());
	@Autowired
	UserRepo userRepo;

	public User getUserByUserName(String userName) {
		User findByUserName = userRepo.findByUserName(userName);
		if(findByUserName!=null)
		{
			return getUserByUserId(findByUserName.getUserId());
		}
		return dummyUser;
	}

	public User getUserByUserId(Integer userId) {
		return userRepo.findById(userId).orElse(dummyUser);
	}

	public void save(User userDetails) {
		userRepo.save(userDetails);
	}

	public JwtUserDetails getJWTUserByUserName(String userName) {
		User user = getUserByUserId(getUserByUserName(userName).getUserId());
		if (user.getUserId().equals(Integer.MAX_VALUE)) {
			return null;
		}
		return new JwtUserDetails(user.getUserId(), user.getUserName(), user.getPassword(),user.getRole() , user.getName() ,
				user.getStatus());

	}
}
