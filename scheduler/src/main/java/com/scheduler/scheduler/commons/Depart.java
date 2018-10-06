package com.scheduler.scheduler.commons;

import java.util.ArrayList;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

public interface Depart {
	
	public void setField(long dayDif, int num);
	public ArrayList<NurseDto> createList();
}
