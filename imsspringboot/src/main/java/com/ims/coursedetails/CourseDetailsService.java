package com.ims.coursedetails;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDetailsService {

	@Autowired
	CourseDetailsRepository repo;

	public CourseDetails saveCourse(CourseDetails course) {
		return repo.save(course);
	}
	
	public Optional<CourseDetails> getCourseByCourseName(String courseName) {
		return repo.getByCourseName(courseName);
	}

	public Optional<CourseDetails> getCourseById(Integer id) {
		return repo.findById(id);
	}

	public List<CourseDetails> getUnApprovedCourse() {
		return repo.findAllByApproveStatus("unapproved");
	}

	public List<CourseDetails> getActiveCourse() {
		return repo.findAllByStatus("active");
	}

	public List<CourseDetails> getInactiveCourse() {
		return repo.findAllByStatus("inactive");
	}

	public List<CourseDetails> getAllCourse() {
		return repo.findAll();
	}

	public CourseDetails updateCourse(Integer id, String name, Integer fee) {
		CourseDetails existing = repo.getById(id);
		existing.setCourseName(name);
		existing.setCourseFee(fee);
		repo.save(existing);
		return existing;
	}
}
