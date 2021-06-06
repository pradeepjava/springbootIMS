package com.ims.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepoForUserTable extends JpaRepository<User, Integer> {
User findByUserName(String userName);
}
