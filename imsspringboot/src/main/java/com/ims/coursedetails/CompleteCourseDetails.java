package com.ims.coursedetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompleteCourseDetails {

	private CourseDescription courseDescription;
	private CourseDetails courseDetails;
	private ImageDetails imageDetails;

}
