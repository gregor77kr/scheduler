package com.scheduler.scheduler.model.main.dao;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.scheduler.scheduler.commons.CalendarBuilder;
import com.scheduler.scheduler.model.main.dto.CalendarDto;

@Repository
public class MainDaoImple implements MainDao {
	
	@Inject
	CalendarBuilder cb;
	

	@Override
	public ArrayList<CalendarDto> getList(String startDate, String endDate) {
		cb.setStartDate(startDate);
		cb.setEndDate(endDate);
		
		return cb.builder();
	}

	
}
