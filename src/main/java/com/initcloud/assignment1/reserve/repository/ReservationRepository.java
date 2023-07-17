package com.initcloud.assignment1.reserve.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.initcloud.assignment1.reserve.entity.Reservation;
import com.initcloud.assignment1.room.entity.Room;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	List<Reservation> findByMemberId(Long userId);

	void deleteById(Long id);

	boolean existsByRoomAndStartTimeBetweenOrEndTimeBetween(Room room, Date startDateTime, Date endDateTime, Date startDateTime1, Date endDateTime1);
}
