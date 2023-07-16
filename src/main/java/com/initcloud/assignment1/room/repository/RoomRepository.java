package com.initcloud.assignment1.room.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.initcloud.assignment1.room.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room>  findAllById(Long roomId);
}
