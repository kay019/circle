package com.kh.circle.poll.service;

import java.util.HashMap;
import java.util.List;

import com.kh.circle.poll.entity.Pagination;

public interface PollService {

	List<HashMap<String, String>> homeList(String empNo);
	
	List<HashMap<String, String>> getResult(HashMap<String, String> params);
	
	int countTotalProgressPost(Pagination prePageInfo);
	List<HashMap<String, String>> progressList(Pagination pageInfo);
	
	int countTotalFinishedPost(Pagination prePageInfo);
	List<HashMap<String, String>> finishedList(Pagination pageInfo);
	
	int countTotalMyPost(Pagination prePageInfo);
	List<HashMap<String, String>> myList(Pagination pageInfo);

	List<HashMap<String, String>> getQuestion(HashMap<String, String> params);


}