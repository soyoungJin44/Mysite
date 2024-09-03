package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;

@Service
public class AttachService {
	@Autowired
	AttachDao attachDao;
	
	//파일 업로드
	
	public void exeupload(MultipartFile file) {           
		System.out.println("service upload준 완");
		
		// 1번 : 사진에 기본정보로 우리가 관리할 정보를 뽑아내야된다 =>>db에 뽑아낸값 저장ㅇㅇ
		
		//<할 일>
			//1-1 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName >>>" + orgName);
		
			//1-2 확장자
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exeName);
		
			//1-3 파일 사이즈
		long FileSize = file.getSize();
		System.out.println(FileSize);
		
		
		
		
		
		// 2번 :  사진을 서버에 복사해야된다(db에 직접적으로 전달 ㄴㄴ c드라이브에 저장한 주소값만 보낸다)
		
		return ;
	}
	

}
