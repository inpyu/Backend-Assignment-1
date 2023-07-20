package com.initcloud.assignment1.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception{

	private ErrorStatus error;

}

