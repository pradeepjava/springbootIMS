package com.ims.coursedetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Entity(name = "coursedetails")
public class CourseDetails {
	@Id
	@GeneratedValue
	@Column(name = "courseid")
	Integer courseId;
	@Column(name = "coursename")
	String courseName;
	@Column(name = "coursefee")
	Integer courseFee;
	@Column(name = "status")
	String status;
	@Column(name = "approvestatus", columnDefinition = "text default 'unapproved'")
	String approveStatus = "unapproved";

	public CourseDetails() {

	}
}
