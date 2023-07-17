package com.initcloud.assignment1.reserve.service;

import static com.initcloud.assignment1.common.ErrorStatus.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.initcloud.assignment1.member.Member;
import com.initcloud.assignment1.member.MemberService;
import com.initcloud.assignment1.reserve.dto.ReservationAllListOutDTO;
import com.initcloud.assignment1.reserve.dto.ReservationCreateInDTO;
import com.initcloud.assignment1.reserve.dto.ReservationCreateOutDTO;
import com.initcloud.assignment1.reserve.dto.ReservationUpdateInDTO;
import com.initcloud.assignment1.reserve.dto.ReservationUpdateOutDTO;
import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.reserve.exception.ReserveException;
import com.initcloud.assignment1.reserve.repository.ReservationRepository;
import com.initcloud.assignment1.room.dto.RoomAllListOutDTO;
import com.initcloud.assignment1.room.entity.Room;
import com.initcloud.assignment1.room.entity.Size;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final MemberService memberService;

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

	/**
	 * 예약 삭제 API
	 * */

	public String deleteReservation(Long reservationId) {
		reservationRepository.deleteById(reservationId);
		return "리뷰 삭제 완료";
	}

	/**
	 * 예약 생성 API
	 * */
	public ReservationCreateOutDTO createReservation(ReservationCreateInDTO dto, Member member, Room room)
	throws ReserveException {
		LocalTime currentTime = LocalTime.now();
		// 현재 시간이 00시부터 01시 사이인 경우에 예외 발생
		if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.of(1, 0))) {
			throw new IllegalArgumentException("예약은 00시부터 01시 사이에는 불가능합니다.");
		}

		// Room의 size 정보 가져오기
		Size roomSize = room.getSize();

		/**
		 * 최대 예약 시간 확인 로직
		 */

		// 예약 가능한 최대 연속 시간 가져오기
		int maxContinuousHours = getMaxContinuousHoursByRoomSize(roomSize);

		// 예약 생성 가능한지 검사
		Date startTime = dto.getStartTime();
		Date endTime = dto.getEndTime();

		long hoursBetween = TimeUnit.MILLISECONDS.toHours(endTime.getTime() - startTime.getTime());
		if (hoursBetween > maxContinuousHours) {
			throw new ReserveException(MAX_ROOM_TIME_ERROR);
		}

		/**
		 * 하나의 아이디로 최대 6시간 예약 로직 구현
		 * */
		long hoursToAdd = hoursBetween;
		memberService.addReservationTime(member, hoursToAdd);

		/**
		 * 이미 예약이 있으면 예약 불가능
		 **/
		boolean isAlreadyReserved = reservationRepository.existsByRoomAndStartTimeBetweenOrEndTimeBetween(
			room, startTime, endTime, startTime, endTime);
		if (isAlreadyReserved) {
			throw new ReserveException(DUPLICATED_TIME);
		}


		/**
		 * 예약 생성
		 */
		Reservation reservation = Reservation.create(dto, room, member);
		reservationRepository.save(reservation);

		return ReservationCreateOutDTO.of(reservation);
	}

	/**
	 * 예약 수정
	 * */
	public ReservationUpdateOutDTO updateReservation(ReservationUpdateInDTO dto, Member member, Room room) throws
		ReserveException {
		LocalTime currentTime = LocalTime.now();
		// 현재 시간이 00시부터 01시 사이인 경우에 예외 발생
		if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.of(1, 0))) {
			throw new IllegalArgumentException("예약은 00시부터 01시 사이에는 불가능합니다.");
		}

		// Room의 size 정보 가져오기
		Size roomSize = room.getSize();

		/**
		 * 최대 예약 시간 확인 로직
		 */

		// 예약 가능한 최대 연속 시간 가져오기
		int maxContinuousHours = getMaxContinuousHoursByRoomSize(roomSize);

		// 예약 생성 가능한지 검사
		Date startTime = dto.getStartTime();
		Date endTime = dto.getEndTime();

		long hoursBetween = TimeUnit.MILLISECONDS.toHours(endTime.getTime() - startTime.getTime());
		if (hoursBetween > maxContinuousHours) {
			throw new ReserveException(MAX_ROOM_TIME_ERROR);
		}

		/**
		 * 하나의 아이디로 최대 6시간 예약 로직 구현
		 * */
		long hoursToAdd = hoursBetween;
		memberService.addReservationTime(member, hoursToAdd);

		/**
		 * 이미 예약이 있으면 예약 불가능
		 **/
		boolean isAlreadyReserved = reservationRepository.existsByRoomAndStartTimeBetweenOrEndTimeBetween(
			room, startTime, endTime, startTime, endTime);
		if (isAlreadyReserved) {
			throw new ReserveException(DUPLICATED_TIME);
		}

		/**
		 * 예약 생성
		 */
		Reservation reservation = Reservation.update(dto, room);
		reservationRepository.save(reservation);

		return ReservationUpdateOutDTO.of(reservation);
	}




	/**
	 * Room Size별 최대 예약시간 반환 함수
	 * */
	private int getMaxContinuousHoursByRoomSize(Size roomSize) {
		switch (roomSize) {
			case 소:
				return 6;
			case 중:
				return 4;
			case 대:
				return 2;
			default:
				throw new IllegalArgumentException("유효하지 않은 회의실 크기입니다.");
		}
	}

}
