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

import com.initcloud.assignment1.room.Room;
import com.initcloud.assignment1.user.User;

@Entity
public class Reserve {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date startTime;

	@Column(nullable = false)
	private Date endTime;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Room")
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User")
	private User user;

}
