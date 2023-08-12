package com.initcloud.assignment1.reserve.dto;

import java.util.Date;

import com.initcloud.assignment1.member.Member;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.room.entity.Room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReservationUpdateOutDTO {

	private Long id;

	private Date startTime;

	private Date endTime;

	private Room room;

	public static ReservationUpdateOutDTO of(Reservation reservation) {
		return ReservationUpdateOutDTO.builder()
			.id(reservation.getId())
			.startTime(reservation.getStartTime())
			.endTime(reservation.getEndTime())
			.room(reservation.getRoom())
			.build();
	}

}
