package com.scheduler.scheduler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scheduler.scheduler.model.main.dao.MainDao;
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
		
		nDao.setDto(dto);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("list", dao.getList(dto.getStartDate(), dto.getEndDate()));
		mav.addObject("headList", nDao.getHeadList());
		mav.addObject("nurseList", nDao.getNurseList());
		mav.addObject("assistList", nDao.getAssistList());
		mav.addObject("dto", dto);
		mav.addObject("map", nDao.getDepart());
		
		return mav;
	}
	
	@RequestMapping("/search.do")
	public ModelAndView search(@ModelAttribute InputDto dto, @RequestParam String target) {
	
		// 멤버변수 설정
		nDao.setDto(dto);
		nDao.setTarget(target);
		
		// mav
		ModelAndView mav = new ModelAndView();
		
		// return view
		mav.setViewName("searchResult");
		
		// 달력 그리기 위한 list
		mav.addObject("list", dao.getList(dto.getStartDate(), dto.getEndDate()));
		
		// 검색 결과 리스트
		mav.addObject("searchList", nDao.getSearchList());
		
		// dto 객체
		mav.addObject("dto", dto);
		
		mav.addObject("target", target);
		
		return mav;
	}
	
}
