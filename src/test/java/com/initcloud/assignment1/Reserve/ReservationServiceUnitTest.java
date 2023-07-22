package com.initcloud.assignment1.Reserve;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.initcloud.assignment1.member.Member;
import com.initcloud.assignment1.reserve.dto.ReservationAllListOutDTO;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.reserve.repository.ReservationRepository;
import com.initcloud.assignment1.reserve.service.ReservationService;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.entity.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringJUnitConfig
@SpringBootTest
@Transactional
public class ReservationServiceUnitTest {

	@Autowired
	private ReservationService reservationService;

	@MockBean
	private ReservationRepository reservationRepository;

	@Test
	public void testGetAllReservations() {
		// Mock 데이터 생성
		List<Reservation> mockReservations = new ArrayList<>();

		// Reservation 데이터 예시 생성
		Room room1 = Room.builder().name("Room1").size(Size.소).build();
		Member member1 = Member.builder().sumTime(10L).build();
		Date startTime1 = Date.from(LocalDateTime.now().minusHours(2).atZone(ZoneId.systemDefault()).toInstant());
		Date endTime1 = Date.from(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()).toInstant());
		Reservation reservation1 = Reservation.builder().startTime(startTime1).endTime(endTime1).room(room1).member(member1).build();
		mockReservations.add(reservation1);

		Room room2 = Room.builder().name("Room2").size(Size.중).build();
		Member member2 = Member.builder().sumTime(15L).build();
		Date startTime2 = Date.from(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()).toInstant());
		Date endTime2 = Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant());
		Reservation reservation2 = Reservation.builder().startTime(startTime2).endTime(endTime2).room(room2).member(member2).build();
		mockReservations.add(reservation2);

		// reservationRepository의 findAll() 메서드가 호출될 때 mockReservations를 반환하도록 설정
		when(reservationRepository.findAll()).thenReturn(mockReservations);

		// 테스트 대상 메서드 호출
		List<ReservationAllListOutDTO> result = reservationService.getAllReservations();

		// 결과 검증
		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);

		// reservationRepository의 findAll() 메서드가 호출되었는지 검증
		verify(reservationRepository).findAll();
	}

	@Test
	public void testGetUserReservation() {
		// Mock 데이터 생성
		List<Reservation> mockReservations = new ArrayList<>();

		// Reservation 데이터 예시 생성
		Room room1 = Room.builder().id(1L).name("Room1").size(Size.소).build();
		Member member1 = Member.builder().id(1L).sumTime(10L).build();
		Date startTime1 = Date.from(LocalDateTime.now().minusHours(2).atZone(ZoneId.systemDefault()).toInstant());
		Date endTime1 = Date.from(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()).toInstant());
		Reservation reservation1 = Reservation.builder().startTime(startTime1).endTime(endTime1).room(room1).member(member1).build();
		mockReservations.add(reservation1);

		Room room2 = Room.builder().id(2L).name("Room2").size(Size.중).build();
		Member member2 = Member.builder().id(2L).sumTime(15L).build();
		Date startTime2 = Date.from(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()).toInstant());
		Date endTime2 = Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant());
		Reservation reservation2 = Reservation.builder().startTime(startTime2).endTime(endTime2).room(room2).member(member2).build();
		mockReservations.add(reservation2);

		// memberId에 해당하는 Mock 데이터 필터링
		Long memberIdToFind = 1L;
		List<Reservation> filteredReservations = mockReservations.stream()
			.filter(reservation -> reservation.getMember().getId().equals(memberIdToFind))
			.collect(Collectors.toList());

		// reservationRepository의 findByMemberId() 메서드가 호출될 때 filteredReservations를 반환하도록 설정
		when(reservationRepository.findByMemberId(memberIdToFind)).thenReturn(filteredReservations);

		// 테스트 대상 메서드 호출
		List<Reservation> result = reservationService.getUserReservation(memberIdToFind);

		// 결과 검증
		assertThat(result).isNotNull();
		assertThat(result).hasSize(1);

		// reservationRepository의 findByMemberId() 메서드가 호출되었는지 검증
		verify(reservationRepository).findByMemberId(memberIdToFind);
	}
}
