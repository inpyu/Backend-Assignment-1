package com.initcloud.assignment1.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "result", "message"})
public class FailResponse {

	private int code;
	private String result;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;

	public FailResponse(ErrorStatus status) {
		this.code = status.getCode();
		this.result = status.getResult();
		this.message = status.getMessage();
	}
}
