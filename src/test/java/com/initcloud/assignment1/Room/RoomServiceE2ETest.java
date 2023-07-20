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
		mockMvc.perform(get("/rooms")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray());
	}

	@Test
	public void testGetRoomDetail() throws Exception {
		// Mock data
		Long roomId = 1L;

		mockMvc.perform(get("/rooms/{roomId}", roomId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray());
	}

}