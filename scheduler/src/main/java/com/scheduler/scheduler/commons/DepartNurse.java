package com.scheduler.scheduler.commons;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

@Component
public class DepartNurse implements Depart {

	private long dayDif;
	private int nurseNum;

	@Override
	public void setField(long dayDif, int num) {
		this.dayDif = dayDif;
		this.nurseNum = num;
	}

	@Override
	public ArrayList<NurseDto> createList() {

		ArrayList<NurseDto> list = new ArrayList<>();
		NurseDto dto = null;
		
		for (int i = 0; i <= dayDif; i++) {
			dto = new NurseDto();
			switch ((i+1)%4) {
			case 1:
				dto.setNight("N1");
				dto.setDay("N2");
				dto.setEvening("N3");
				dto.setRest("N4");
				list.add(dto);
				break;
			case 2:
				dto.setNight("N4");
				dto.setDay("N1");
				dto.setEvening("N2");
				dto.setRest("N3");
				list.add(dto);
				break;
			case 3:
				dto.setNight("N3");
				dto.setDay("N4");
				dto.setEvening("N1");
				dto.setRest("N2");
				list.add(dto);
				break;
			case 0:
				dto.setNight("N2");
				dto.setDay("N3");
				dto.setEvening("N4");
				dto.setRest("N1");
				list.add(dto);
				break;
			}
			
		}
		
		return list;
	}
	
}
