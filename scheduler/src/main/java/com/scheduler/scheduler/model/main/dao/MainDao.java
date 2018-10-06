package com.scheduler.scheduler.model.main.dao;

import java.util.ArrayList;

import com.scheduler.scheduler.model.main.dto.CalendarDto;

public interface MainDao {
	
	public ArrayList<CalendarDto> getList(String startDate, String endDate);
	
}
