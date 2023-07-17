package com.initcloud.assignment1.room.controller;

import static com.initcloud.assignment1.common.SuccessStatus.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.initcloud.assignment1.common.SuccessResponse;
import com.initcloud.assignment1.room.dto.RoomAllListOutDTO;
import com.initcloud.assignment1.room.dto.RoomListOutDTO;
import com.initcloud.assignment1.room.service.RoomService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	/**
	 * 회의실 정보 조회
	 * */
	@GetMapping("/{roomId}")
	public SuccessResponse<List<RoomListOutDTO>> detailRoom(@PathVariable Long roomId){
		return new SuccessResponse<>(SUCCESS, roomService.getRoomDetail(roomId));
	}

	/**
	 * 모든 회의실 조회
	 * */
	@GetMapping("/all")
	public SuccessResponse<List<RoomAllListOutDTO>> allRoom(){
		return new SuccessResponse<>(SUCCESS, roomService.getAllRooms());
	}

}
