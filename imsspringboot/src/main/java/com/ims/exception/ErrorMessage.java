package com.ims.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ErrorMessage {
	private Date createdAt;
	private String message;

}
