package com.ims.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ims.coursedetails.CourseDetails;
import com.ims.coursedetails.CourseDetailsService;
import com.ims.exception.ObjectAlreadyExistsInDBException;

@RestController
@CrossOrigin(origins = "${allowed.origin}")
@RequestMapping(path = "/courseDetails")
public class CourseDetailsController {

	@Autowired
	CourseDetailsService courseService;

	@PostMapping(path = "/saveCourse")
	public CourseDetails saveCourse(@RequestBody CourseDetails courseDetails) {
		throwExceptionForExistingCourseByName(courseDetails);
		return courseService.saveCourse(courseDetails);
	}

	private void throwExceptionForExistingCourseByName(CourseDetails courseDetails) {
		if (courseService.getCourseByCourseName(courseDetails.getCourseName()).isPresent()) {
			throw new ObjectAlreadyExistsInDBException("Course already exists!");
		}
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

	@GetMapping(path = "/inactive")
	public List<CourseDetails> getInActive() {
		return courseService.getInactiveCourse();
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteCourseDetials(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
	}

	@PutMapping(path = "/approveAll")
	public ResponseEntity<?> approveCourses(@RequestBody List<CourseDetails> listCourseDetails) {

		listCourseDetails.stream().map(c -> {
			c.setApproveStatus("Approved");
			return c;
		}).forEach(details -> updateCourseByDate(details));
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	private void updateCourseByDate(CourseDetails courseDetails) {
		CourseDetails existing = courseService.getCourseById(courseDetails.getCourseId()).get();
		existing.setCourseName(courseDetails.getCourseName());
		existing.setCourseFee(courseDetails.getCourseFee());
		existing.setStatus(courseDetails.getStatus());// changing here
		existing.setApproveStatus(courseDetails.getApproveStatus());
		existing.setApproveDate(new Date());
		courseService.updateCourse(existing);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<CourseDetails> updateCourse(@RequestBody CourseDetails courseDetails) {
		CourseDetails existing = courseService.getCourseById(courseDetails.getCourseId()).get();
		existing.setCourseName(courseDetails.getCourseName());
		existing.setCourseFee(courseDetails.getCourseFee());
		existing.setStatus(courseDetails.getStatus());// changing here
		existing.setApproveStatus(courseDetails.getApproveStatus());
		courseService.updateCourse(existing);
		return new ResponseEntity<CourseDetails>(existing, HttpStatus.OK);
	}

	@GetMapping(path = "/searchCourseByText")
	public List<CourseDetails> searchByText(@RequestParam(value = "text") String text) {
		int id = getIntegerFromText(text);
		if (id != 0) {
			Optional<CourseDetails> optionalValue = courseService.getCourseById(id);
			if (optionalValue.isPresent()) {
				return Arrays.asList(optionalValue.get());
			}
			return new ArrayList<>();
		}
		return courseService.serchCourseContainingName(text);
	}

	private int getIntegerFromText(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return 0;
		}

	}
}
