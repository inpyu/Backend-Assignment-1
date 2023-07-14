package com.initcloud.assignment1.reserve.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.initcloud.assignment1.reserve.dto.ReservationAllListOutDTO;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.reserve.repository.ReservationRepository;
import com.initcloud.assignment1.room.dto.RoomAllListOutDTO;
import com.initcloud.assignment1.room.entity.Room;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;

	/**
	 * 모든 예약목록을 가져올 수 있는 API
	 * */
	@Transactional(readOnly = true)
	public List<ReservationAllListOutDTO> getAllReservations() {
		List<Reservation> allReservations = reservationRepository.findAll();
		List<ReservationAllListOutDTO> reservationLists = new ArrayList<>();

		for (Reservation reservation : allReservations) {
			ReservationAllListOutDTO of = ReservationAllListOutDTO.of(reservation);
			reservationLists.add(of);
		}
		return reservationLists;
	}

	/**
	 * User ID로 예약 정보를 조회할 수 있는 API
	 * */
	@Transactional(readOnly = true)
	public List<Reservation> getUserReservation(Long userId) {
		List<Reservation> reservations = reservationRepository.findByMemberId(userId);
		return reservations;
	}
}
