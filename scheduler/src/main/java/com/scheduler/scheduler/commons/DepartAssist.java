package com.scheduler.scheduler.commons;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

@Component
public class DepartAssist implements Depart {

	private long dayDif;
	private int assistNum;

	@Override
	public void setField(long dayDif, int num) {
		this.dayDif = dayDif;
		assistNum = num;
	}

	@Override
	public ArrayList<NurseDto> createList() {

		ArrayList<NurseDto> list = new ArrayList<>();
		NurseDto dto = null;

		for (int i = 0; i <= dayDif; i++) {
			dto = new NurseDto();
			switch ((i+1) % 4) {
			case 1:
				dto.setNight("A4");
				dto.setDay("A3");
				dto.setEvening("A2");
				dto.setRest("A1");
				list.add(dto);
				break;
			case 2:
				dto.setNight("A1");
				dto.setDay("A4");
				dto.setEvening("A3");
				dto.setRest("A2");
				list.add(dto);
				break;
			case 3:
				dto.setNight("A2");
				dto.setDay("A1");
				dto.setEvening("A4");
				dto.setRest("A3");
				list.add(dto);
				break;
			case 0:
				dto.setNight("A3");
				dto.setDay("A2");
				dto.setEvening("A1");
				dto.setRest("A4");
				list.add(dto);
				break;
			}
		}
		return list;
	}//create ends

}
