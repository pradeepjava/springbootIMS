package com.ims.coursedetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "image_table")
@NoArgsConstructor
public class ImageDetails {

	public ImageDetails(int id,String name, String type, byte[] picByte) {
		super();
		this.imageid=id;
		this.name = name;
		this.type = type;
		this.picbyte = picByte;
	}

	@Id
	@Column(name = "imageid")
	@GeneratedValue
	private Integer imageid;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
	@Column(name = "picbyte", length = 1000)
	private byte[] picbyte;
}
