package com.initcloud.assignment1.reserve.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.initcloud.assignment1.reserve.dto.ReservationCreateInDTO;
import com.initcloud.assignment1.reserve.dto.ReservationUpdateInDTO;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date startTime;

	@Column(nullable = false)
	private Date endTime;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Room")
	private Room room;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Member")
	private Member member;

	@Builder
	public Reservation (Date startTime, Date endTime, Room room, Member member) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
		this.member = member;
	}

	public static Reservation create(ReservationCreateInDTO reservationCreateInDTO, Room room, Member member){
		return Reservation.builder()
			.startTime(reservationCreateInDTO.getStartTime())
			.endTime(reservationCreateInDTO.getEndTime())
			.room(room)
			.member(member)
			.build();
	}

	public static Reservation update(ReservationUpdateInDTO reservationUpdateInDTO, Room room){
		return Reservation.builder()
			.startTime(reservationUpdateInDTO.getStartTime())
			.endTime(reservationUpdateInDTO.getEndTime())
			.room(room)
			.build();
	}

}
