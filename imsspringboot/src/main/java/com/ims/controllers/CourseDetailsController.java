package com.ims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.coursedetails.CourseDetails;
import com.ims.coursedetails.CourseDetailsService;

@RestController
@CrossOrigin(origins = "${allowed.origin}")
@RequestMapping(path = "/courseDetails")
public class CourseDetailsController {

	@Autowired
	CourseDetailsService courseService;

	@PostMapping(path = "/saveCourse")
	public void saveCourse(@RequestBody CourseDetails courseDetails) {
		courseService.saveCourse(courseDetails);
	}

	@GetMapping(path = "/{id}")
	public CourseDetails getCourseDetailsById(@PathVariable int id) {
		return courseService.getCourseById(id).get();
	}

	@GetMapping
	public List<CourseDetails> getAllCourse() {
		return courseService.getAllCourse();
	}

	@GetMapping(path = "/unapproved")
	public List<CourseDetails> getUnapproved() {
		return courseService.getUnApprovedCourse();
	}

	@GetMapping(path = "/active")
	public List<CourseDetails> getActive() {
		return courseService.getActiveCourse();
	}

	@GetMapping(path = "/inActive")
	public List<CourseDetails> getInActive() {
		return courseService.getInactiveCourse();
	}

	@PutMapping(path = "/update")
		public ResponseEntity<CourseDetails> updateCourse(@RequestBody CourseDetails courseDetails){
		CourseDetails existing = courseService.getCourseById(courseDetails.getCourseId()).get();
		existing.setCourseName(courseDetails.getCourseName());
		existing.setCourseFee(courseDetails.getCourseFee());
		saveCourse(existing);
		return new ResponseEntity<CourseDetails>(existing, HttpStatus.OK);
	}
}
