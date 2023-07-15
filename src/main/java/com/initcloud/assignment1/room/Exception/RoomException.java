package com.initcloud.assignment1.room.Exception;

import com.initcloud.assignment1.common.ErrorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoomException extends Exception{
	private ErrorStatus error;
}
