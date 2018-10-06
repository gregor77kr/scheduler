package com.scheduler.scheduler.commons;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

@Component
public class DepartHead implements Depart {

	private long dayDif;
	private int headNum;

	@Override
	public void setField(long dayDif, int num) {
		this.dayDif = dayDif;
		headNum = num;
	}

	@Override
	public ArrayList<NurseDto> createList() {

		ArrayList<NurseDto> list = new ArrayList<>();
		NurseDto dto = null;

		String array[] = new String[headNum];

		for (int i = 0; i < headNum; i++) {
			int temp = i + 1;
			array[i] = "H" + temp;
		}
		
		int temp = 0;
		
		for (int i = 0; i <= dayDif; i++) {
			dto = new NurseDto();
			
			if(temp >= headNum) {
				temp = 0;
			}
			
			dto.setDay(array[temp]);
			list.add(dto);
			temp += 1;
		}

		return list;
	}

}
