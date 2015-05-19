package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.BulletinBoard;

@Repository
public class BulletinDao {
	private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String id = "webdb";
	private final String password = "webdb";

	public Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url, id, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void insert(BulletinBoard b) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO BULLETINBOARD VALUES(bulletin_no_seq.nextval, ?, ?, ?, ?, sysdate, 0)";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, b.getMemberno());
		pstmt.setString(2, b.getB_title());
		pstmt.setString(3, b.getName());
		pstmt.setString(4, b.getContent());
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 입력 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}

	public void updateBoard(BulletinBoard b) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE BULLETINBOARD SET b_title = ?, content = ?, reg_date = sysdate WHERE b_no=?";

		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, b.getB_title());
		pstmt.setString(2, b.getContent());
		pstmt.setInt(3, b.getB_no());
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 수정 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}
	
	public void updateHit(BulletinBoard b) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE BULLETINBOARD SET hit = ? WHERE b_no=?";

		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, b.getHit()+1);
		pstmt.setInt(2, b.getB_no());
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 수정 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}
	public void delete() throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM BULLETINBOARD";

		pstmt = con.prepareStatement(sql);
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 삭제 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}

	public void deleteByMemberNo(int no) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM BULLETINBOARD WHERE memberno=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 삭제 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}

	public void deleteById(int no) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM BULLETINBOARD WHERE b_no=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 삭제 완료");

		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}
	}
	public List<BulletinBoard> selectAllList(int flag) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BulletinBoard> list = new ArrayList<BulletinBoard>();

		String sql = "SELECT * FROM BULLETINBOARD WHERE name != 'admin' ORDER BY reg_date desc";

		if (flag == 1) {
			sql = "SELECT * FROM BULLETINBOARD WHERE name = 'admin' ORDER BY reg_date desc";
		}

		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			list.add(new BulletinBoard(rs.getInt(1), rs.getInt(2), rs
					.getString(3), rs.getString(4), rs.getString(5), rs
					.getDate(6), rs.getInt(7)));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}

		return list;
	}

	public List<BulletinBoard> search(String keyword) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BulletinBoard> list = new ArrayList<BulletinBoard>();
		String sql = "SELECT * FROM BulletinBoard WHERE b_title LIKE ? OR CONTENT LIKE ? ORDER BY reg_date desc";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		rs = pstmt.executeQuery();

		while (rs.next()) {
			list.add(new BulletinBoard(rs.getInt(1), rs.getInt(2), rs
					.getString(3), rs.getString(4), rs.getString(5), rs
					.getDate(6), rs.getInt(7)));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}

		return list;
	}

	public BulletinBoard searchBybNo(int no) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BulletinBoard b = new BulletinBoard();
		String sql = "SELECT * FROM BulletinBoard WHERE b_no=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			b = new BulletinBoard(rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getDate(6),
					rs.getInt(7));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}

		return b;
	}

	public List<BulletinBoard> selectListByMemberNo(int memberno)
			throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BulletinBoard> list = new ArrayList<BulletinBoard>();

		String sql = "SELECT * FROM BulletinBoard WHERE memberno = ? ORDER BY reg_date desc";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, memberno);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			list.add(new BulletinBoard(rs.getInt(1), rs.getInt(2), rs
					.getString(3), rs.getString(4), rs.getString(5), rs
					.getDate(6), rs.getInt(7)));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}

		return list;
	}

	public Map<String, Object> getPrevAndNextText(int b_no) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "select * from (select  b_no, lead(b_no, 1, 0) over(order by b_no desc), lead(b_title, 1, '없음') over(order by b_no desc), lag(b_no, 1, 0) over(order by b_no desc), lag(b_title, 1, '없음') over(order by b_no desc) from BulletinBoard  ) where b_no= ? ";
		// String sql
		// ="select * from (select b_no, lead(b_no, 1, '다음글') over (order by b_no) from BulletinBoard  ) where b_no= ? ";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, b_no);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			System.out.println("rs.next 안");
			int prevNo = rs.getInt(2);
			String prevTitle = rs.getString(3);
			int nextNo = rs.getInt(4);
			String nextTitle = rs.getString(5);

			map.put("prevNo", prevNo);
			map.put("prevTitle", prevTitle);
			map.put("nextNo", nextNo);
			map.put("nextTitle", nextTitle);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}

		if (con != null) {
			con.close();
		}

		return map;
	}
}
