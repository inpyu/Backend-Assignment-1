package com.initcloud.assignment1.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {

	INVALID_REQUEST(400, "FAIL", "유효하지 않은 요청입니다."),
	DUPLICATED_TIME(400 ,"FAIL", "이미 회의실이 예약된 시간입니다."),
	NOT_EXIST_ROOM(400 ,"FAIL", "존재하지 않는 회의실 입니다."),
	MAX_ROOM_TIME_ERROR(400, "FAIL","회의실 예약 최대 시간을 확인해주세요." );

	private final int code;
	private final String result;
	private final String message;

}
