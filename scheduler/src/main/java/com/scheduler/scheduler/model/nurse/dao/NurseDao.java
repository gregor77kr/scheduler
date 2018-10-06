package com.scheduler.scheduler.model.nurse.dao;

import java.util.ArrayList;

import com.scheduler.scheduler.model.main.dto.InputDto;
import com.scheduler.scheduler.model.nurse.dto.NurseDto;

public interface NurseDao {
	
	public void dayDif();
	public void setter(InputDto dto);
	public ArrayList<NurseDto> getHeadList();
	public ArrayList<NurseDto> getNurseList();
	public ArrayList<NurseDto> getAssistList();
	
}
