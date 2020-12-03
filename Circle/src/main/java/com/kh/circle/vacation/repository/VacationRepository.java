package com.kh.circle.vacation.repository;

import java.util.List;

import com.kh.circle.vacation.entity.Vacation;

public interface VacationRepository {
	public List<Vacation> vacationList(String emp_no);
	
	public String annualTerm (String emp_no);
	
	public int annualLeave (String annualTerm);
	
	public double usedVacationDays (String emp_no);
}