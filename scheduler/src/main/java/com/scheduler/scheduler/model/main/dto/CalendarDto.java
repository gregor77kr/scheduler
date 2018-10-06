package com.scheduler.scheduler.model.main.dto;

public class CalendarDto {

	private int year;
	private int month;
	private int startDay;
	private int startDate;
	private int endDate;

	@Override
	public String toString() {
		return "CalendarDto [year=" + year + ", month=" + month + ", startDay=" + startDay + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

}
