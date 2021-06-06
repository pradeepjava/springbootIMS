package com.ims.userdetails;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "userdetails")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "userid")
	Integer userId;
	String name;
	@Column(name = "username")
	String userName;
	String password;
	String role;
	String status;
	@Column(name = "creationdate")
	Date creationDate;
}
