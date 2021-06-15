package com.ims.coursedetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Integer> {
	List<CourseDetails>findAllByApproveStatus(String status);
	List<CourseDetails>findAllByStatus(String status);
	Optional<CourseDetails>getByCourseName(String courseName);
}
