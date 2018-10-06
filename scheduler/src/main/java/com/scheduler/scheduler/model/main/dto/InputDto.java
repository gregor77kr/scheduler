package com.scheduler.scheduler.model.main.dto;

public class InputDto {
	private int headNum;
	private int nurseNum;
	private int assistNum;
	private String startDate;
	private String endDate;

	@Override
	public String toString() {
		return "InputDto [headNum=" + headNum + ", nurseNum=" + nurseNum + ", assistNum=" + assistNum + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	public int getHeadNum() {
		return headNum;
	}

	public void setHeadNum(int headNum) {
		this.headNum = headNum;
	}

	public int getNurseNum() {
		return nurseNum;
	}

	public void setNurseNum(int nurseNum) {
		this.nurseNum = nurseNum;
	}

	public int getAssistNum() {
		return assistNum;
	}

	public void setAssistNum(int assistNum) {
		this.assistNum = assistNum;
	}

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

}
