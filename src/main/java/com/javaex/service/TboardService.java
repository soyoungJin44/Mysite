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
	
	// 페이징
	public Map<String, Object> exeList2(int crtPage) {
		System.out.println("service j w!");
		System.out.println("service"+crtPage);
		
		///////////////////////////////////////////////////
		/// 1. 리스트 가져오기
		///////////////////////////////////////////////////
		int listCnt = 10;	//한페이지의 출력갯수
		int totalCnt = tboardDao.selectTotalCnt();
		System.out.println("///////*****");
		System.out.println(totalCnt);
		System.out.println("///////*****");
		
		// 사용자측에서 -값 입력했을 때 1페이지로 ㄱㄱ
		
		//(조건식) ? 값 : 값 // 바로밑에는 삼항연산자란다..
		//crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
				
		if(crtPage < 0) {
			crtPage = 1;
		};
		
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
		
		////////////////////////////////////////////
		//// 2. 페이징 계산 ( 하단 버튼)
		////////////////////////////////////////////
		int pageBtncount = 5;   // 페이지당 버튼 갯수
		
		// << endPageBtnNo : 마지막 버튼 번호  >>
		// 식 :(올림(현페이지/끝페이지))*끝페이지
		
		int endPageBtnNo = (int)Math.ceil((crtPage / (double)pageBtncount))*pageBtncount;
		System.out.println("99999:::::" + endPageBtnNo);
		
		
		//  << 시작 버튼 번호 >>
		
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		System.out.println(crtPage + "," + startPageBtnNo + "," + endPageBtnNo);
		
		
		
		//다음 화살표 유무
		
		boolean next = false;
		//listCnt * endPageBtnNo < totalCnt	// 한 페이지당갯수 * 마지막버튼번호 < 전체글 갯수(db에서 가져오면됨)
		if(listCnt * endPageBtnNo < totalCnt) {
			next = true;
		}else {
			//다음 화살표가 false일 때 마지막 숫자버튼이 갯수를 정확히 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//화면에 표현할 모든 데이터를 묶는다 >> map으로
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		
		
		return pMap;
		
		
	}
	
	//###############33333333333번  페이징 + 검색
	public Map<String, Object> exeList3(int crtPage, String keyword) {
		System.out.println("service j w!");
		System.out.println("service"+crtPage);
		
		///////////////////////////////////////////////////
		/// 1. 리스트 가져오기
		///////////////////////////////////////////////////
		int listCnt = 10;	//한페이지의 출력갯수
		int totalCnt = tboardDao.selectTotalCntKeyword(keyword);
		System.out.println("#$@#%$#%$#^#$^#$%#$%@$%@#%@#%$@#% 토탈토탈토탈토탈토탈토탈토탈토탈"+totalCnt );
		
		// 사용자측에서 -값 입력했을 때 1페이지로 ㄱㄱ
		
		//(조건식) ? 값 : 값 // 바로밑에는 삼항연산자란다..
		//crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
				
		if(crtPage < 0) {
			crtPage = 1;
		};
		
		//startRowNo 구하기
		//1 (1~10) 2 (11~10) 3(21~10)     ## mysql은 0번부터 시작
		
		// 식: (원하는 페이지-1) *10;
		
		int startRowNo = (crtPage-1)*listCnt;
		
		
		System.out.println("///////////////////"+startRowNo);
		
		//두개의 데이터를 1개로 묶어준다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("keyword", keyword);
		
		System.out.println("@@@@@@@@@@@@@33333" + limitMap);
		
		
		List<TboardVo> tboardList = tboardDao.selectList3(limitMap);
		//System.out.println("service list dddd");
		
		////////////////////////////////////////////
		//// 2. 페이징 계산 ( 하단 버튼)
		////////////////////////////////////////////
		int pageBtncount = 5;   // 페이지당 버튼 갯수
		
		// << endPageBtnNo : 마지막 버튼 번호  >>
		// 식 :(올림(현페이지/끝페이지))*끝페이지
		
		int endPageBtnNo = (int)Math.ceil((crtPage / (double)pageBtncount))*pageBtncount;
		System.out.println("99999:::::" + endPageBtnNo);
		
		
		//  << 시작 버튼 번호 >>
		
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		System.out.println(crtPage + "," + startPageBtnNo + "," + endPageBtnNo);
		
		
		
		//다음 화살표 유무
		
		boolean next = false;
		//listCnt * endPageBtnNo < totalCnt	// 한 페이지당갯수 * 마지막버튼번호 < 전체글 갯수(db에서 가져오면됨)
		if(listCnt * endPageBtnNo < totalCnt) {
			next = true;
		}else {
			//다음 화살표가 false일 때 마지막 숫자버튼이 갯수를 정확히 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//화면에 표현할 모든 데이터를 묶는다 >> map으로
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		
		
		return pMap;
		
		
	}
	
	
	
}
