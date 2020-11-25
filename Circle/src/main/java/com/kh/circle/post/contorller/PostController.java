package com.kh.circle.post.contorller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.circle.post.entity.Post;
import com.kh.circle.post.entity.PostPaging;
import com.kh.circle.post.service.PostService;
import com.kh.circle.post.service.PostServiceImp;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired 
	private HttpServletResponse response;

	@GetMapping("postList")
	public String postList(Model model, PostPaging paging) {
	
		// pageing calculation
		int currentPageNo = 1;
		int recordsPerPage = 0;
		String url = null;
		
		//커넥션 풀 연결 / 인스턴스 생성
		PostService postService = PostService.getInstance();
		
		//pages, lines 파라미터를 받아 currnetPageNo, recordsPerpage대입
		// 처음 페이지 열릴 때에는 당연히 1, 0
		if(request.getParameter("pages") != null)
			currentPageNo = Integer.parseInt(request.getParameter("pages"));
		
		if(request.getParameter("lines") != null)
			recordsPerPage = Integer.parseInt(request.getParameter("lines"));
		
		
		//Paging 객체 생성(currentPagenNO, recordsPerPage를 인자로 넣고 초기화함)
		//객체 선언한 뒤 paging 출력하면 recordsPerPage가 5로 출력
		PostPaging postPaging = new PostPaging(currentPageNo, recordsPerPage);
		
		
		//해당 게시글의 인덱스를 구하는 변수offset
		
		int offset = (postPaging.getCurrentPageNo() - 1) * postPaging.getRecordsPerPage();
		
		
		
		//post 데이터 가져오기
		List<Post> post = postService.getPostList(offset, postPaging.getRecordsPerPage());
		
		//전체 갯수 구해서 numberOfRecords 메소드 세팅
		//*************** 이 인근 이해안감
		postPaging.setNumberOfRecords(postService.getNoOfRecords());
		
		
		//paging 생성
		paging.makePaging();
		
		
		//list 존재시
		request.setAttribute("post", post);
		request.setAttribute("paging", paging);
		
		return "post/postList";
	}

	@GetMapping("postList")
	public String postList(Model model) {
	
List<Post> postList = sqlSession.selectList("post.postList");
		
		model.addAttribute("postList", postList);
	
		return "post/postList";
	}
	
	
	

	
	@GetMapping("/postInsert")
	public String postInsert(Model model, @ModelAttribute("post.postList") Post post) {
		
		
		model.addAttribute("post", post);
		
		return "post/postInsert";
	}
	
	
}
