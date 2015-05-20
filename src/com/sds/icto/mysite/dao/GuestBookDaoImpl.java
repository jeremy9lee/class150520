package com.sds.icto.mysite.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.GuestBook;

@Repository
public class GuestBookDaoImpl {
	
	@Autowired
	private SqlSession session;
	public int insertGuestBook(GuestBook gb) throws SQLException {
		return session.insert("insertGuestBook", gb);
	}

	public int deleteGuestBook(@Param("no") int no) throws SQLException {
		GuestBook gb = new GuestBook();
		gb.setNo(no);
		return session.delete("deleteGuestBook", gb);
	}


	public List<GuestBook> selectAllGuestBookList() throws SQLException {
		return session.selectList("selectAllGuestBookList");
	}
	
	public GuestBook selectAllGuestBookListById(@Param ("g_no") int no) throws SQLException {
		return session.selectOne("selectAllGuestBookListById", no);
	}

}
