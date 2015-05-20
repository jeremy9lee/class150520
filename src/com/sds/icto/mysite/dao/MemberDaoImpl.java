package com.sds.icto.mysite.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.MemberVo;

@Repository
public class MemberDaoImpl {
	@Autowired
	SqlSession session;

	public int insertMember(MemberVo vo) {
		return session.insert("insertMember", vo);
	}

	public int updateMember(MemberVo vo) {
		return session.update("updateMember", vo);
	}

	public MemberVo getMember(@Param("email") String email, @Param ("password") String password) {
	Map map  = new HashMap();
		map.put("email", email);
		map.put("password", password);
		return session.selectOne("getMember",map);
	}
}
