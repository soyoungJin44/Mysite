package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {
	@Autowired
	AttachDao attachDao;
	
	//파일 업로드
	
	public String exeupload(MultipartFile file) {           
		System.out.println("service upload준 완");
		
		// 1번 : 사진에 기본정보로 우리가 관리할 정보를 뽑아내야된다 =>>db에 뽑아낸값 저장ㅇㅇ
		//저장 폴더 변수로 만들어서 빼놔주기
		String saveDir = "C:\\javaStudy\\upload";
		
		
		//<할 일>
			//1-1 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName >>>" + orgName);
		
			//1-2 확장자
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exeName);
		
			//1-3 파일 사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
			//1-4 저장할 파일명 ( 시간 + 랜덤값 + 확장자 : 저장할 파일명)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exeName;
		System.out.println("dddddddd: " + saveName);
		
			//1-5 파일전체 경로+파일명
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//## < 1 > db에 저장
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize);
		System.out.println("과제: " + attachVo);
		
		// dao로 전달 ㄱ (과제)
		
//		attachDao.upload(attachVo);
		
		// 2번 :  사진을 서버에 복사해야된다(db에 직접적으로 전달 ㄴㄴ c드라이브에 저장한 주소값만 보낸다)
			//2-1 파일 저장
		
		try{
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		}catch(Exception e) {
			System.out.println(e.toString());
			
		};
		
		return saveName; // : (시간 + uuid + .jpg)
	}
	

}
