package com.initcloud.assignment1.reserve.exception;

import com.initcloud.assignment1.common.ErrorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReserveException extends Exception {
	private ErrorStatus error;
}
