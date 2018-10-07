package com.scheduler.scheduler.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			switch ((i + 1) % 4) {
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
	
	// 간호사 팀 인원분배
	@Override
	public Map<String, Integer> getDepart() {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		
		switch (nurseNum % 4) {
		
		case 0:
			n1 = nurseNum/4;
			n2 = n1;
			n3 = n1;
			n4 = n1;
			break;
		
		case 1:
			n1 = (nurseNum/4);
			n2 = n1 + 1;
			n3 = n1;
			n4 = n1;
			break;
		
		case 2:
			n1 = (nurseNum/4);
			n2 = n1 + 1;
			n3 = n1 + 1;
			n4 = n1;
			break;
			
		case 3:
			n1 = (nurseNum/4);
			n2 = n1 + 1;
			n3 = n1 + 1;
			n4 = n1 + 1;
			break;
		}
		
		map.put("N1", n1);
		map.put("N2", n2);
		map.put("N3", n3);
		map.put("N4", n4);
		
		return map;
	}
	
	
	
}
