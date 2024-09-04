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


}
