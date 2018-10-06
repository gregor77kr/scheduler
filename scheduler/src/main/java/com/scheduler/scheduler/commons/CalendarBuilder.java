package com.scheduler.scheduler.commons;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.scheduler.scheduler.model.main.dto.CalendarDto;

@Component
public class CalendarBuilder {

	private String startDate;
	private String endDate;

	public CalendarBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CalendarDto> builder() {

		// 결과를 return할 list 생성
		ArrayList<CalendarDto> list = new ArrayList<CalendarDto>();

		// 문자열 데이터 parsing
		int parsedStartYear = Integer.parseInt(startDate.substring(0, 4));
		int parsedStartMonth = Integer.parseInt(startDate.substring(4, 6));
		int parsedStartDate = Integer.parseInt(startDate.substring(6, 8));

		int parsedEndYear = Integer.parseInt(endDate.substring(0, 4));
		int parsedEndMonth = Integer.parseInt(endDate.substring(4, 6));
		int parsedEndDate = Integer.parseInt(endDate.substring(6, 8));

		// 두 기간 사이의 월 interval 산출
		int interval = getInterval(parsedStartYear, parsedStartMonth, parsedEndYear, parsedEndMonth);

		// dto 객체 선언
		CalendarDto dto = null;

		int year = parsedStartYear;
		int month = parsedStartMonth;

		for (int i = 0; i < interval; i++) {
			
			// 달이 하나밖에 없을 경우
			if(interval == 1) {
				dto = setter(parsedStartYear, parsedStartMonth, parsedStartDate, parsedEndDate);
				list.add(dto);
				break;
			}
			
			// 첫 달과 마지막 달은 시작일과 끝 일이 다르니 따로 처리함
			if (i == 0) {
				dto = setter(parsedStartYear, parsedStartMonth, parsedStartDate, 0);
				list.add(dto);

			} else if (i == interval - 1) {
				dto = setter(parsedEndYear, parsedEndMonth, parsedEndDate, 1);
				list.add(dto);

			} else {
				dto = setter(year, month);
				list.add(dto);

			}

			// index increasing
			// 12월인 경우 다음 month를 1로, year를 1 증가시켜준다
			// 아닌 경우 month +1
			if (month == 12) {
				month = 1;
				year += 1;
			} else {
				month += 1;
			}

		}
		return list;
	}

	// 첫 달과 마지막 달을 제외한 달을 위한 overloading method
	public CalendarDto setter(int year, int month) {
		CalendarDto dto = new CalendarDto();
		Calendar c = Calendar.getInstance();

		c.set(year, month - 1, 1);

		dto.setYear(year);
		dto.setMonth(month);
		dto.setStartDate(1);
		dto.setStartDay(c.get(Calendar.DAY_OF_WEEK));
		dto.setEndDate(c.getActualMaximum(Calendar.DAY_OF_MONTH));

		return dto;
	}

	// 첫 달과 마지막 달을 위한 overloading method
	public CalendarDto setter(int year, int month, int date, int flag) {
		CalendarDto dto = new CalendarDto();
		Calendar c = Calendar.getInstance();

		// flag == 0 --> 첫 달의 경우
		if (flag == 0) {
			c.set(year, month - 1, date);

			dto.setYear(year);
			dto.setMonth(month);
			dto.setStartDate(date);
			dto.setStartDay(c.get(Calendar.DAY_OF_WEEK));
			dto.setEndDate(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (flag == 1) {
			c.set(year, month - 1, 1);

			dto.setYear(year);
			dto.setMonth(month);
			dto.setStartDate(1);
			dto.setStartDay(c.get(Calendar.DAY_OF_WEEK));
			dto.setEndDate(date);
		} else {
			c.set(year, month -1, date);
			dto.setYear(year);
			dto.setMonth(month);
			dto.setStartDate(date);
			dto.setStartDay(c.get(Calendar.DAY_OF_WEEK));
			dto.setEndDate(flag);
		}

		return dto;
	}

	public int getInterval(int startYear, int startMonth, int endYear, int endMonth) {
		int interval = 0;

		if (startYear == endYear) {
			interval = endMonth - startMonth + 1;
		} else {
			interval = (13 - startMonth) + endMonth;
		}
		return interval;
	}
	
	// getter and setter
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * CalendarBuilder cb = new CalendarBuilder("20170115", "20180330");
	 * ArrayList<CalendarDto> list = cb.builder();
	 * 
	 * for(CalendarDto dto : list) { System.out.println("dto 출력 :" +
	 * dto.toString()); }
	 * 
	 * }
	 */

}
