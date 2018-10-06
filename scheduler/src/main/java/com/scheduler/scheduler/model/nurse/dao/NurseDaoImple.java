package com.scheduler.scheduler.model.nurse.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

		this.dayDif =   (eDate.getTime() - sDate.getTime()) / (24 * 60 * 60 * 1000);
		
	}

	@Override
	public void setter(InputDto dto) {
		this.dto = dto;
		dayDif();
	}

	@Override
	public ArrayList<NurseDto> getHeadList() {
		dHead.setField(dayDif, dto.getHeadNum());
		
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

}
