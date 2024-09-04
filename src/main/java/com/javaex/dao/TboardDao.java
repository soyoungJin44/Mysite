package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {
	
	@Autowired
	private SqlSession sqlSessoin;
	
	public List<TboardVo> selectList() {
		System.out.println("dao j w!");
		
		List<TboardVo> tboardList = sqlSessoin.selectList("tboard.selectList");
		
		System.out.println("////////////////"+tboardList);
		
		return tboardList;
		
	}
	
	public List<TboardVo> selectList2(Map<String, Integer> limitMap) {
		System.out.println("dao j w!");
		System.out.println("+++++++++++"+ limitMap);
		
		List<TboardVo> tboardList = sqlSessoin.selectList("tboard.selectList2", limitMap);
		System.out.println("Dao에용ㅇㅇ"+ tboardList);
		
		return tboardList;
		
	}
	
	// 전체 글 갯수
	public int selectTotalCnt() {
		System.out.println("total cnt j w!!");
		
		int totalCnt = sqlSessoin.selectOne("tboard.selectTotalCnt");
		
		
		return totalCnt;
		
	}
	
	//페이징 + 검색
	
	public List<TboardVo> selectList3(Map<String, Object> limitMap) {
		System.out.println("+++++++++++"+ limitMap);
		
		List<TboardVo> tboardList = sqlSessoin.selectList("tboard.selectList3", limitMap);
		System.out.println("Dao에용ㅇㅇ"+ tboardList);
		
		return tboardList;
		
	}
	
	// 검색 된 총 글 갯수
		public int selectTotalCntKeyword(String keyword) {
			System.out.println("total cnt j w!!");
			
			int totalCnt = sqlSessoin.selectOne("tboard.selectTotalCntKeyword",keyword);
			
			
			return totalCnt;
			
		}
	


}
