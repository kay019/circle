package com.kh.circle.empInfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.circle.addressBook.entity.PagingInfo;
import com.kh.circle.empInfo.service.EmpInfoService;
import com.kh.circle.login.entity.EmpInfo;

@Controller
@RequestMapping("/empInfo")
public class EmpInfoController {
	@Autowired
	private EmpInfoService empInfoService;
	
	@GetMapping("/myInfo")
	public String myInfo(HttpSession session,
						Model model) {

		String emp_no = ( (EmpInfo) session.getAttribute("empInfo")).getEmp_info_emp_no();

		if(emp_no != null) {
			Map<String, Object> map = empInfoService.empInfoOne(emp_no);
			
			model.addAttribute("map", map);

			return "attendance/myInfo";
		} else {
			model.addAttribute("msg", "로그인 후 사용 가능합니다.");
			
			return "common/error";
		}
	}
	
	@GetMapping("/allInfoList")
	public String allInfoList(@RequestParam(value="nowPage", defaultValue="1") int nowPage,
								@RequestParam(value="perPage", defaultValue="10") int perPage,
								@RequestParam(value="empNo", required=false) String empNo,
								@RequestParam(value="name", required=false) String name,
								@RequestParam(value="deptName", required=false) String deptName,
								Model model) {
		
		//입력값 전달 객체
		PagingInfo pInfo = PagingInfo.builder()
									.nowPage(nowPage)
									.perPage(perPage)
									.empNo(empNo)
									.name(name)
									.deptName(deptName)
									.build();
		
		Map<String, Object> map = empInfoService.empInfoList(pInfo);
		
		model.addAttribute("map", map);
		model.addAttribute("empNo", empNo);
		model.addAttribute("name", name);
		model.addAttribute("deptName", deptName);
		
		return "attendance/allInfoList";
	}
	
	@PostMapping("checkPwd")
	@ResponseBody
	public String checkPwd(@RequestParam(value="curPwd") String curPwd,
							@RequestParam(value="emp_no") String emp_no){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("curPwd", curPwd);
		map.put("emp_no", emp_no);
		
		String result = empInfoService.checkPwd(map);
		
		return result;
	}
}
