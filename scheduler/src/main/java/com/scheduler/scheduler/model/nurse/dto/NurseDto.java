package com.scheduler.scheduler.model.nurse.dto;

public class NurseDto {
	private String night;
	private String day;
	private String evening;
	private String rest;

	@Override
	public String toString() {
		return "NurseDto [night=" + night + ", day=" + day + ", evening=" + evening + ", rest=" + rest + "]";
	}

	public String getNight() {
		return night;
	}

	public void setNight(String night) {
		this.night = night;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getRest() {
		return rest;
	}

	public void setRest(String rest) {
		this.rest = rest;
	}

}
