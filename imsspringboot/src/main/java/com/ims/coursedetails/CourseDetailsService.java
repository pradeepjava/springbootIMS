package com.ims.coursedetails;

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
		return repo.findAllByApproveStatus("Unapproved");
	}

	public List<CourseDetails> getActiveCourse() {
		return repo.findAllByStatus("Active");
	}

	public List<CourseDetails> getInactiveCourse() {
		return repo.findAllByStatus("Inactive");
	}

	public List<CourseDetails> getAllCourse() {
		return repo.findAll();
	}

	public void deleteCourse(Integer id) {
		repo.delete(repo.getById(id));
	}

	public CourseDetails updateCourse(CourseDetails details) {
		repo.save(details);
		return details;
	}

	public List<CourseDetails> serchCourseContainingName(String text) {
		return repo.findAllActiveUsers(text.toUpperCase());
	}

	public List<CourseDetails> getApproved() {
		return repo.findAllByApproveStatus("Approved");
	}
}
