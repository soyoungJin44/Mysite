package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	public List<TboardVo> exeList() {
		System.out.println("service j w!");
		
		List<TboardVo> tboardList = tboardDao.selectList();
		System.out.println("service list dddd");
		
		return tboardList;
		
	}
	
	public List<TboardVo> exeList2(int crtPage) {
		System.out.println("service j w!");
		System.out.println("service"+crtPage);
		
		///////////////////////////////////////////////////
		/// 리스트 가져오기
		///////////////////////////////////////////////////
		int listCnt = 10;	//한페이지의 출력갯수
		
		
		//startRowNo 구하기
		//1 (1~10) 2 (11~10) 3(21~10)     ## mysql은 0번부터 시작
		
		// 식: (원하는 페이지-1) *10;
		
		int startRowNo = (crtPage-1)*listCnt;
		System.out.println("///////////////////"+startRowNo);
		
		//두개의 데이터를 1개로 묶어준다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		
		System.out.println("@@@@@@@@@@@@@" + limitMap);
		
		
		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		//System.out.println("service list dddd");
		
		return tboardList;
		
	}
	

	
}
