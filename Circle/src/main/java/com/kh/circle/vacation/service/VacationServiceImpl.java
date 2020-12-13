package com.kh.circle.vacation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.circle.sign.vo.SignSelectOne;
import com.kh.circle.sign.vo.SignWriteInsert;
import com.kh.circle.vacation.entity.Vacation;
import com.kh.circle.vacation.entity.VacationInfo;
import com.kh.circle.vacation.repository.VacationRepository;

@Service
public class VacationServiceImpl implements VacationService {
	@Autowired
	private VacationRepository vacationRepository;
	
	@Override
	public Map<String, Object> vacationList(String emp_no) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 연차사용목록 출력
		List<Vacation> vList = vacationRepository.vacationList(emp_no);
		map.put("vList", vList);
		
		// 요약정보 출력
		// 1. 근속연월 확인
		String annualTerm = vacationRepository.annualTerm(emp_no);
		
		// 2. 반환된 근속연월로 발급된 연차 확인
		// 	- 1년 미만은 1개월 당 1일
		int annualLeave = vacationRepository.annualLeave(annualTerm);
		map.put("annualLeave", annualLeave);
		
		// 3. 사용연차 일수 계산
		double usedVacationDays = vacationRepository.usedVacationDays(emp_no);
		map.put("usedVacationDays", usedVacationDays);
		
		// 4. 잔여 연차 일수 계산
		double leftVacationDays = annualLeave - usedVacationDays;
		map.put("leftVacationDays", leftVacationDays);
		
		return map;
	}

	@Override
	public void addVacation(VacationInfo vacationInfo) {
		vacationRepository.addVacation(vacationInfo);
	}

	@Override
	public SignSelectOne formVacation(VacationInfo vacationInfo) {
		// 받아온 정보를 vo형식으로 변환
		
		SignSelectOne signForm = SignSelectOne.builder()
										.sign_code("SIGN000001")
										.sign_emp_code(vacationInfo.getEmpNo())
										.sign_type_name("휴가계")
										.sign_title("휴가 신청서입니다.")
										.emp_info_name(vacationInfo.getEmpName())
										.build();
		
		//내용물 저장은 어디에?
		
		return signForm;
	}

}
