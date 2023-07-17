package com.initcloud.assignment1.room.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.entity.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class RoomAllListOutDTO {

	private Long id;
	private String name;

	public static RoomAllListOutDTO of(Room room) {
		return RoomAllListOutDTO.builder()
			.id(room.getId())
			.name(room.getName())
			.build();
	}
}
