
 package com.kh.circle.login.service;
 
 import javax.servlet.http.HttpSession;

import com.kh.circle.login.entity.Member;
 
 public interface MemberService {
 
	 public Member login(Member member, HttpSession session) throws Exception;
	 
	 //임의로 설계
	public Member logIn(Member member); 
 
 }
 