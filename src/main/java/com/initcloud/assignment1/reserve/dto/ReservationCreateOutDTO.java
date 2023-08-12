package com.initcloud.assignment1.reserve.dto;

import java.util.Date;

import javax.swing.*;

import com.initcloud.assignment1.member.Member;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.room.entity.Room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReservationCreateOutDTO {

	private Long id;

	private Date startTime;

	private Date endTime;

	private Room room;

	private Member member;

	public static ReservationCreateOutDTO of(Reservation reservation) {
		return ReservationCreateOutDTO.builder()
			.id(reservation.getId())
			.startTime(reservation.getStartTime())
			.endTime(reservation.getEndTime())
			.room(reservation.getRoom())
			.member(reservation.getMember())
			.build();
	}

}
