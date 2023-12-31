package com.initcloud.assignment1.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {

	/**
	 * Success Code
	 * 200 : 요청 성공
	 * 201 : 요청으로 인해 새로운 리소스 생성
	 * 204 : 요청에 성공했으나 데이터는 없음
	 */

	/**
	 * Success Code : 200
	 * OK
	 */
	SUCCESS(200,"SUCCESS","요청이 완료 되었습니다."),



	/**
	 * Success Code : 201
	 * Created
	 */
	CREATE_RESERVATION(201,"SUCCESS","예약이 완료 되었습니다."),
	UPDATE_RESERVATION(201,"SUCCESS","예약 업데이트가 완료 되었습니다.");

	/**
	 * Success Code : 204
	 * No Content
	 */


	private final int code;
	private final String result;
	private final String message;
}