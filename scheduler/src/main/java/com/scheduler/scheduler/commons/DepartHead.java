package com.scheduler.scheduler.commons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.scheduler.scheduler.model.nurse.dto.NurseDto;

@Component
public class DepartHead implements Depart {

	private long dayDif;
	private int headNum;
	private String startDate;

	@Override
	public void setField(long dayDif, int num) {
		this.dayDif = dayDif;
		headNum = num;
	}

	@Override
	public ArrayList<NurseDto> createList() {

		// 결과를 return하기 위한 arrayList
		ArrayList<NurseDto> list = new ArrayList<>();

		// list의 element가 될 dto
		NurseDto dto = null;

		// 수간호사의 숫자만큼 배열에 index 생성
		String array[] = new String[headNum];

		for (int i = 0; i < headNum; i++) {
			int temp = i + 1;
			array[i] = "H" + temp;
		}

		int day = getDayOfWeek();
		int temp = 0;

		for (int i = 0; i <= dayDif; i++) {
			dto = new NurseDto();

			if (day > 7) {
				day = 1;
			}

			if (temp >= headNum) {
				temp = 0;
			}

			if (day == 1 || day == 7) {
				dto.setDay("noHead");
				list.add(dto);
				day += 1;
			} else {
				dto.setDay(array[temp]);
				list.add(dto);
				temp += 1;
				day += 1;
			}
		}

		return list;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getDayOfWeek() {

		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(startDate.substring(0, 4)), Integer.parseInt(startDate.substring(4, 6)) - 1,
				Integer.parseInt(startDate.substring(6, 8)));

		return c.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public ArrayList<String> createSearchList(String target, ArrayList<NurseDto> dto) {

		ArrayList<String> result = new ArrayList<String>();
		
		for (NurseDto d : dto) {
			if (d.getDay().equals(target)) {
				result.add("D");
			} else {
				result.add("Rest");
			}
		}
		return result;
	}

	@Override
	public Map<String, Integer> getDepart() {
		
		return null;
	}
	
	

}
