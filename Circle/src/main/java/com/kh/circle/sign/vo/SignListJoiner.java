package com.kh.circle.sign.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignListJoiner {
	private String emp_info_name;
	private String job_info_name;
	private String emp_info_emp_no;
	private String sign_count;
	private String sign_join_check;
}
