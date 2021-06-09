package com.ims.coursedetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Integer> {
	List<CourseDetails>findAllByApproveStatus(String status);
	List<CourseDetails>findAllByStatus(String status);
}
