package com.kh.circle.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.circle.post.entity.Post;
import com.kh.circle.post.entity.PostPaging;
import com.kh.circle.post.repository.PostDao;
import com.kh.circle.project.entity.ProjFile;
import com.kh.circle.project.entity.ProjPaging;
import com.kh.circle.project.entity.Project;
import com.kh.circle.project.repository.ProjFileDao;
import com.kh.circle.project.repository.ProjSaveDao;
import com.kh.circle.project.repository.ProjectDao;



@Service
public class ProjectServiceImp implements ProjectService{

	@Autowired
	private ProjectDao projDao;
	
	@Autowired
	private ProjFileDao projFileDao;
	
	@Autowired
	private ProjSaveDao projSaveDao;
	
	@Autowired
	private PostDao postDao;

	@Override
	public List<Project> projIssMain(String pro_code) {
		return projDao.projIssMain(pro_code);
	}


	@Override
	public int countPost() {
		return projDao.countProj();
	}


	@Override
	public String projEmpNo(String emp_no) {
		return projDao.projEmpNo(emp_no);
	}


	@Override
	public List<Project> projMain(String emp_no) {
		return projDao.projMain(emp_no);
	} 




	@Override
	public List<Project> projMember() {
		return projDao.projMember();
	}


	@Override
	public void projInsert(Project project, String emp_no) {

		projDao.projInsert(project, emp_no);
	}


	@Override
	public List<Project> issMember() {
		return projDao.issMember();
	}


	@Override
	public void projInsertIss(Project project,  MultipartFile iss_file) throws IllegalStateException, IOException {
		
		String iss_code = projDao.projInsertIss(project);
		
		String pro_code = projDao.projGetPro(project);
		
		
		if(!iss_file.isEmpty()) {
			
			ProjFile projFile = ProjFile.builder()
									.files_oname(iss_file.getOriginalFilename())
									.files_cname(iss_file.getContentType())
									.files_size(iss_file.getSize())
									.files_code(iss_code)
									.build();
			
		
			String file_code = projFileDao.insert(projFile, iss_code);
			
			//저장
			projSaveDao.save(iss_file, file_code);
			
			
		}
	}


	@Override
	public List<Project> issProg() {
		return projDao.issProg();
	}


	@Override
	public List<Project> issSitu() {
		return projDao.issSitu();
	}


	@Override
	public List<Project> selecetProject(ProjPaging projPaging) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Post> selecePost(PostPaging postPaging) {

		return postDao.selectPost(postPaging);
	}







}
