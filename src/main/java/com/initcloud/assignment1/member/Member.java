package com.initcloud.assignment1.member;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.initcloud.assignment1.reserve.dto.ReservationCreateInDTO;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.entity.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long sumTime;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "Reservation")
	private List<Reservation> reservations;

	@Builder
	public Member(Long id, Long sumTime, Size size, List<Reservation> reservations){
		this.id = id;
		this.sumTime = sumTime;
		this.reservations = reservations;
	}

	public static Member update(Long id, Long sumTime, List<Reservation> reservations){
		return Member.builder()
			.id(id)
			.sumTime(sumTime)
			.reservations(reservations)
			.build();
	}

}
