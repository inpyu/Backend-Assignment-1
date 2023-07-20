package com.initcloud.assignment1.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.initcloud.assignment1.reserve.exception.ReserveException;
import com.initcloud.assignment1.room.Exception.RoomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({BaseException.class})
	protected FailResponse handleBaseException(BaseException exception) {
		return new FailResponse(exception.getError());
	}

	@ExceptionHandler({RoomException.class})
	protected FailResponse handleRoomException(RoomException exception) {
		return new FailResponse(exception.getError());
	}

	@ExceptionHandler({ReserveException.class})
	protected FailResponse handleReserveException(ReserveException exception) {
		return new FailResponse(exception.getError());
	}

}
