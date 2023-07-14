package com.initcloud.assignment1.reserve.dto;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReservationCreateInDTO {

	private Date startTime;

	private Date endTime;


}
