package com.scheduler.scheduler.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scheduler.scheduler.model.main.dao.MainDao;
import com.scheduler.scheduler.model.main.dto.CalendarDto;
import com.scheduler.scheduler.model.main.dto.InputDto;
import com.scheduler.scheduler.model.nurse.dao.NurseDao;

@Controller
public class MainController {
	
	@Inject
	MainDao dao;
	
	@Inject
	NurseDao nDao;
	
	@RequestMapping("/")
	public String main() {
		
		return "home";
	}
	
	@RequestMapping("/create.do")
	public ModelAndView create(@ModelAttribute InputDto dto) {
		
		nDao.setter(dto);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("list", dao.getList(dto.getStartDate(), dto.getEndDate()));
		mav.addObject("headList", nDao.getHeadList());
		mav.addObject("nurseList", nDao.getNurseList());
		mav.addObject("assistList", nDao.getAssistList());
		
		return mav;
	}
	
}
