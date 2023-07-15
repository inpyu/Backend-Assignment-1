package com.initcloud.assignment1.Room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.initcloud.assignment1.room.Exception.RoomException;
import com.initcloud.assignment1.room.dto.RoomAllListOutDTO;
import com.initcloud.assignment1.room.dto.RoomListOutDTO;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.entity.Size;
import com.initcloud.assignment1.room.repository.RoomRepository;
import com.initcloud.assignment1.room.service.RoomService;

@SpringBootTest
public class RoomServiceUnitTest {

	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomService roomService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllRooms() {
		// Mock data
		Room room1 = Room.builder()
			.id(1L)
			.name("Room 1")
			.size(Size.소)
			.reservation(null)
			.build();

		Room room2 = Room.builder()
			.id(2L)
			.name("Room 2")
			.size(Size.중)
			.reservation(null)
			.build();

		List<Room> allRooms = new ArrayList<>();
		allRooms.add(room1);
		allRooms.add(room2);

		when(roomRepository.findAll()).thenReturn(allRooms);

		List<RoomAllListOutDTO> result = roomService.getAllRooms();

		assertEquals(0, result.size());
	}

	

}
