package com.scheduler.scheduler.commons;

import java.util.ArrayList;
import java.util.Map;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

public interface Depart {
	
	public void setField(long dayDif, int num);
	public ArrayList<NurseDto> createList();
	public ArrayList<String> createSearchList(String target, ArrayList<NurseDto> dto);
	public Map<String, Integer> getDepart();
	
}
