package com.initcloud.assignment1.reserve.controller;

import static com.initcloud.assignment1.common.SuccessStatus.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.initcloud.assignment1.common.SuccessResponse;
import com.initcloud.assignment1.reserve.dto.ReservationAllListOutDTO;
import com.initcloud.assignment1.reserve.service.ReservationService;

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

}
