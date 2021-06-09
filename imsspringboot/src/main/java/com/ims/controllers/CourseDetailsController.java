package com.ims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.coursedetails.CourseDTO;
import com.ims.coursedetails.CourseDetailsService;

@RestController
@CrossOrigin(origins = "${allowed.origin}")
@RequestMapping(path = "/courseDetails")
public class CourseDetailsController {

	@Autowired
	CourseDetailsService courseService;

	@PostMapping(path = "/saveCourse")
	public void saveCourse(@RequestBody CourseDTO courseDetails) {
		courseService.saveCourse(courseDetails);
	}
}
