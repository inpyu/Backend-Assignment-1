package com.initcloud.assignment1.Room;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomServiceE2ETest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllRooms() throws Exception {
		mockMvc.perform(get("/rooms/all")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.data[0].id").value(1))
			.andExpect(jsonPath("$.data[0].name").value("Room 1"))
			.andExpect(jsonPath("$.data[1].id").value(2))
			.andExpect(jsonPath("$.data[1].name").value("Room 2"))
			.andExpect(jsonPath("$.data[2].id").value(3))
			.andExpect(jsonPath("$.data[2].name").value("Room 3"));
	}

	@Test
	public void testGetRoomDetail() throws Exception {
		// Mock data
		Long roomId = 1L;

		mockMvc.perform(get("/rooms/{roomId}", roomId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.data[0].id").value(1))
			.andExpect(jsonPath("$.data[0].name").value("Room 1"))
			.andExpect(jsonPath("$.data[0].size").value("소"));
	}

}
