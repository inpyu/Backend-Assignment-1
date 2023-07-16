package com.initcloud.assignment1.room.service;

import static com.initcloud.assignment1.common.ErrorStatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.asm.Advice;

import com.initcloud.assignment1.room.Exception.RoomException;
import com.initcloud.assignment1.room.dto.RoomAllListOutDTO;
import com.initcloud.assignment1.room.dto.RoomListOutDTO;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

	private final RoomRepository roomRepository;

	/**
	 * 회의실 정보를 가져올 수 있는 API
	 * */
	@Transactional(readOnly = true)
	public List<RoomListOutDTO> getRoomDetail(Long roomId) throws RoomException {
		try{
			List<Room> findRooms = roomRepository.findAllById(roomId);
			return RoomListOutDTO.of(findRooms);
		}catch(NoSuchElementException e){
			throw new RoomException(NOT_EXIST_ROOM);
		}
	}

	/**
	 * 모든 회의실을 가져올 수 있는 API
	 * */
	@Transactional(readOnly = true)
	public List<RoomAllListOutDTO> getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		List<RoomAllListOutDTO> roomLists = new ArrayList<>();

		for (Room room : allRooms) {
			RoomAllListOutDTO of = RoomAllListOutDTO.of(room);
			roomLists.add(of);
		}

		return roomLists;
	}

}
