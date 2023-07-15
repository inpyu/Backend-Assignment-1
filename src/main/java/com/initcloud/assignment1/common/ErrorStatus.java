package com.initcloud.assignment1.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {

	INVALID_REQUEST(400, "FAIL", "유효하지 않은 요청입니다."),
	NOT_EXIST_ROOM(400 ,"FAIL", "존재하지 않는 회의실 입니다.");

	private final int code;
	private final String result;
	private final String message;

}
