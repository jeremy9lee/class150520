package com.sds.icto.mysite.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.BulletinBoard;
@Repository
public class BulletinDaoImpl {

	@Autowired
	private SqlSession session;

	public boolean insert(BulletinBoard b) throws SQLException {

		return session.insert("insert", b) != 0 ? true : false;
	}

	public boolean updateBoard(BulletinBoard b) throws SQLException {
		
		return session.update("updateBoard", b) != 0 ? true : false;
	}

	public boolean updateHit(BulletinBoard b) throws SQLException {

		b.setHit(b.getHit() + 1);
		b.setB_no(b.getB_no());
		return session.update("updateHit", b) != 0 ? true : false;

	}

	public boolean delete( long memberno, int no) throws SQLException {

		Map map = new HashMap();
//		map.put("memberno", memberno);
		map.put("no", no);
		return session.delete("delete", map) != 0 ? true : false;
	}

	public List<BulletinBoard> selectAllList(@Param("flag") boolean flag)
			throws SQLException {
		
		Map map = new HashMap();
		map.put("flag", flag);
		return session.selectList("selectAllList", map);
	}

	public List<BulletinBoard> search(@Param("keyword") String keyword)
			throws SQLException {

		return session.selectList("search", keyword);
	}

	public BulletinBoard searchBybNo(@Param("no") int no) throws SQLException {
		return session.selectOne("searchBybNo", no);
	}

	public List<BulletinBoard> selectListByMemberNo(@Param("memberno") int memberno)
			throws SQLException {
		return session.selectList("searchBybNo", memberno);
	}

	public List<Map<String, Object>> getPrevAndNextText(@Param("no")String b_no) throws SQLException {

//			int prevNo = rs.getInt(2);
//			String prevTitle = rs.getString(3);
//			int nextNo = rs.getInt(4);
//			String nextTitle = rs.getString(5);
//
//			map.put("prevNo", prevNo);
//			map.put("prevTitle", prevTitle);
//			map.put("nextNo", nextNo);
//			map.put("nextTitle", nextTitle);
//		}
		Map map = new HashMap();
		map.put("no", b_no);

		return session.selectList("getPrevAndNextText", map);
	}

}
