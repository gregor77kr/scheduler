package com.scheduler.scheduler.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			switch ((i + 1) % 4) {
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
	}// create ends

	@Override
	public ArrayList<String> createSearchList(String target, ArrayList<NurseDto> dto) {

		ArrayList<String> result = new ArrayList<String>();

		for (NurseDto d : dto) {

			if (d.getNight().equals(target)) {
				result.add("N");
			} else if (d.getDay().equals(target)) {
				result.add("D");
			} else if (d.getEvening().equals(target)) {
				result.add("E");
			} else {
				result.add("Rest");
			}
		}
		return result;
	}

	@Override
	public Map<String, Integer> getDepart() {

		Map<String, Integer> map = new HashMap<String, Integer>();

		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		switch (assistNum % 4) {

		case 0:
			a1 = assistNum / 4;
			a2 = a1;
			a3 = a1;
			a4 = a1;
			break;

		case 1:
			a1 = (assistNum / 4);
			a2 = a1 + 1;
			a3 = a1;
			a4 = a1;
			break;

		case 2:
			a1 = (assistNum / 4);
			a2 = a1 + 1;
			a3 = a1 + 1;
			a4 = a1;
			break;

		case 3:
			a1 = (assistNum / 4);
			a2 = a1 + 1;
			a3 = a1 + 1;
			a4 = a1 + 1;
			break;
		}

		map.put("A1", a1);
		map.put("A2", a2);
		map.put("A3", a3);
		map.put("A4", a4);
		
		return map;
	}

}
