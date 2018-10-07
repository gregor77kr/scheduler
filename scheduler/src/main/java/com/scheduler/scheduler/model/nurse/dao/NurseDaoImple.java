package com.scheduler.scheduler.model.nurse.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.scheduler.scheduler.commons.DepartAssist;
import com.scheduler.scheduler.commons.DepartHead;
import com.scheduler.scheduler.commons.DepartNurse;
import com.scheduler.scheduler.model.main.dto.InputDto;
import com.scheduler.scheduler.model.nurse.dto.NurseDto;

@Repository
public class NurseDaoImple implements NurseDao {
	private long dayDif;
	private InputDto dto;
	private String target;

	@Inject
	DepartNurse dNurse;

	@Inject
	DepartAssist dAssist;

	@Inject
	DepartHead dHead;

	@Override
	public void dayDif() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(dto.getStartDate());
			eDate = sdf.parse(dto.getEndDate());
		} catch (ParseException e) {
			dayDif = 0;
		}

		this.dayDif = (eDate.getTime() - sDate.getTime()) / (24 * 60 * 60 * 1000);

	}

	@Override
	public void setDto(InputDto dto) {
		this.dto = dto;
		dayDif();
	}

	@Override
	public void setTarget(String target) {
		this.target = target;
	}

	// 수간호사는 주말에 근무하지 않음
	// 주말이 언제인지 계산하기 위하여
	@Override
	public ArrayList<NurseDto> getHeadList() {
		dHead.setField(dayDif, dto.getHeadNum());
		dHead.setStartDate(dto.getStartDate());

		return dHead.createList();
	}

	@Override
	public ArrayList<NurseDto> getNurseList() {
		dNurse.setField(dayDif, dto.getNurseNum());

		return dNurse.createList();
	}

	@Override
	public ArrayList<NurseDto> getAssistList() {
		dAssist.setField(dayDif, dto.getAssistNum());

		return dAssist.createList();
	}

	@Override
	public ArrayList<String> getSearchList() {

		char flag = target.charAt(0);
		ArrayList<String> list = null;

		if (flag == 'H') {
			list = dHead.createSearchList(target, dHead.createList());
		} else if (flag == 'N') {
			list = dNurse.createSearchList(target, dNurse.createList());
		} else if (flag == 'A') {
			list = dAssist.createSearchList(target, dAssist.createList());
		}

		return list;
	}

	@Override
	public Map<String, Integer> getDepart() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		dNurse.setField(dayDif, dto.getNurseNum());
		dAssist.setField(dayDif, dto.getAssistNum());

		// 인원 받아오기
		Map<String, Integer> nurse = dNurse.getDepart();
		Map<String, Integer> assist = dAssist.getDepart();

		// result map에 다시 넣기
		for (String key : nurse.keySet()) {
			
			result.put(key, nurse.get(key));
		}

		for (String key : assist.keySet()) {
			
			result.put(key, assist.get(key));
		}
		
		
		return result;
	}

}
