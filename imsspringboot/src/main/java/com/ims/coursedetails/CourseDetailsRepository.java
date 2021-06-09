package com.ims.coursedetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDTO, Integer> {
	List<CourseDTO>findAllByApproveStatus(String status);
	List<CourseDTO>findAllByStatus(String status);
}
