package com.initcloud.assignment1.reserve.controller;

import static com.initcloud.assignment1.common.SuccessStatus.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.initcloud.assignment1.common.SuccessResponse;
import com.initcloud.assignment1.member.Member;
import com.initcloud.assignment1.reserve.dto.ReservationAllListOutDTO;
import com.initcloud.assignment1.reserve.dto.ReservationCreateInDTO;
import com.initcloud.assignment1.reserve.dto.ReservationCreateOutDTO;
import com.initcloud.assignment1.reserve.dto.ReservationUpdateInDTO;
import com.initcloud.assignment1.reserve.dto.ReservationUpdateOutDTO;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.reserve.service.ReservationService;
import com.initcloud.assignment1.room.dto.RoomListOutDTO;
import com.initcloud.assignment1.room.entity.Room;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReserveController {

	private final ReservationService reservationService;

	/**
	 * 모든 예약 정보를 가져올수 있는 API
	 * */
	@GetMapping("/all")
	public SuccessResponse<List<ReservationAllListOutDTO>> allReservation(){
		return new SuccessResponse<>(SUCCESS, reservationService.getAllReservations());
	}

	/**
	 * User ID로 예약 정보를 조회할 수 있는 API
	 * */
	@GetMapping("/{userId}")
	public SuccessResponse<List<Reservation>> userReservation(@PathVariable Long userId){
		return new SuccessResponse<>(SUCCESS, reservationService.getUserReservation(userId));
	}

	/**
	 * Reservation Id로 예약을 삭제할 수 있는 API
	 * */
	@DeleteMapping("/{reservationId}")
	public SuccessResponse<String> deleteReservation(@PathVariable Long reservationId){
		return new SuccessResponse<>(SUCCESS, reservationService.deleteReservation(reservationId));
	}

	/**
	 * 예약 생성 API
	 * */
	@PostMapping
	public SuccessResponse<ReservationCreateOutDTO> createReservation(
		@Validated @RequestPart ReservationCreateInDTO dto,
		@Validated @RequestPart Room room,
		@Validated @RequestPart Member member){
		ReservationCreateOutDTO reviewCreateOutDTO = reservationService.createReservation(dto, member, room);
		return new SuccessResponse<>(CREATE_RESERVATION, reviewCreateOutDTO);
	}

	/**
	 * 예약 수정 API
	 * */
	@PostMapping
	public SuccessResponse<ReservationUpdateOutDTO> updateReservation(
		@Validated @RequestPart ReservationUpdateInDTO dto,
		@Validated @RequestPart Room room,
		@Validated @RequestPart Member member){
		ReservationUpdateOutDTO reviewUpdateOutDTO = reservationService.updateReservation(dto, member, room);
		return new SuccessResponse<>(UPDATE_RESERVATION, reviewUpdateOutDTO);
	}

}
