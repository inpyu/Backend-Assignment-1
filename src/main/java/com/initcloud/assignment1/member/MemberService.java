package com.initcloud.assignment1.member;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	// 예약된 시간 간격을 Member의 sumTime에 더해주는 메서드
	public void addReservationTime(Member member, long hoursToAdd) {
		long sumTimeInHours = member.getSumTime();

		sumTimeInHours += hoursToAdd;

		if (sumTimeInHours > 6) {
			throw new IllegalArgumentException("인당 최대 예약 가능 시간을 초과하였습니다.");
		}

		member = Member.update(member.getId(), sumTimeInHours, member.getReservations());
	}

}
