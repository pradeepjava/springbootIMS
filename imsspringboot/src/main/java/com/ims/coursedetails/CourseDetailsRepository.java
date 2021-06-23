package com.ims.coursedetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Integer> {
	List<CourseDetails>findAllByStatus(String status);
	Optional<CourseDetails>getByCourseName(String courseName);
	
	@Query(value="select * from coursedetails where UPPER(coursename) like %:text%", nativeQuery = true)
	List<CourseDetails>findAllActiveUsers(@Param("text") String text);
	
	@Query(value="select * from coursedetails where status='Active' and approvestatus=:text", nativeQuery = true)
	List<CourseDetails>findAllByApproveStatus(@Param("text") String text);
}
