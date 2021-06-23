package com.ims.coursedetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "coursedescription")
@NoArgsConstructor
@ToString
public class CourseDescription {

	public CourseDescription(int id, String keyfeature, String duration, int imgid) {
		super();
		this.descriptionid = id;
		this.keyfeature = keyfeature;
		this.duration = duration;
		this.imgid = imgid;
	}

	@Id
	@Column(name = "descriptionid")
	@GeneratedValue
	private Integer descriptionid;

	@Column(name = "keyfeature")
	private String keyfeature;

	@Column(name = "duration")
	private String duration;
	
	@Column(name = "imgid")
	private Integer imgid;
}
