package com.ims.coursedetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDetailsService {

	@Autowired
	CourseDetailsRepository repo;

	public void saveCourse(CourseDTO course) {
		repo.save(course);
	}

	public CourseDTO getCourseById(Integer id) {
		return repo.getById(id);
	}

	public List<CourseDTO> getUnApprovedCourse() {
		return repo.findAllByApproveStatus("unapproved");
	}

	public List<CourseDTO> getActiveCourse() {
		return repo.findAllByApproveStatus("active");
	}

	public List<CourseDTO> getDeactiveCourse() {
		return repo.findAllByApproveStatus("inactive");
	}
}
