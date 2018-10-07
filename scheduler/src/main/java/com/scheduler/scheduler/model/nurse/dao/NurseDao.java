package com.scheduler.scheduler.model.nurse.dao;

import java.util.ArrayList;
import java.util.Map;

import com.scheduler.scheduler.model.main.dto.InputDto;
import com.scheduler.scheduler.model.nurse.dto.NurseDto;

public interface NurseDao {
	
	public void dayDif();
	public void setDto(InputDto dto);
	public void setTarget(String target);
	public ArrayList<NurseDto> getHeadList();
	public ArrayList<NurseDto> getNurseList();
	public ArrayList<NurseDto> getAssistList();
	public ArrayList<String> getSearchList();
	public Map<String, Integer> getDepart();
	
}
