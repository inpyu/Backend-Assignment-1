package com.initcloud.assignment1.room.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.initcloud.assignment1.reserve.entity.Reservation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Size size;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Reservation")
	private Reservation reservation;

	@Builder(access = AccessLevel.PROTECTED)
	public Room(String name, Size size, Reservation reservation){
		this.name = name;
		this.size = size;
		this.reservation = reservation;
	}

}
