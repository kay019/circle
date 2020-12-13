package com.kh.circle.vacation.service;

import java.util.Map;

import com.kh.circle.sign.vo.SignSelectOne;
import com.kh.circle.vacation.entity.VacationInfo;

public interface VacationService {
	
	public Map<String, Object> vacationList(String emp_no);
	
	public SignSelectOne formVacation(VacationInfo vacationInfo);
	
	public void addVacation(VacationInfo vacationInfo);
}
