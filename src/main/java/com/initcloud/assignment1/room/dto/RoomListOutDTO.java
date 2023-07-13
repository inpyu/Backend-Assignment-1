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
public class RoomListOutDTO {

	private Long id;
	private String name;
	private Size size;

	static public List<RoomListOutDTO> of(List<Room> rooms) {
		return rooms.stream()
			.map(room -> RoomListOutDTO.builder()
				.id(room.getId())
				.name(room.getName())
				.size(room.getSize())
				.build())
			.collect(Collectors.toList());
	}
}
