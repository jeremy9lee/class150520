package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.GuestBook;

@Repository
public class GuestBookDao {

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

	public void add(GuestBook gb) throws SQLException {
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO GUESTBOOK VALUES(guestbook_seq.nextval, ?, ?, ?, sysdate)";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, gb.getName());
		pstmt.setString(2, gb.getPassword());
		pstmt.setString(3, gb.getMessage());
		int rowCount = pstmt.executeUpdate();

		System.out.println(rowCount + "건 입력 완료");

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

		String sql = "DELETE FROM GUESTBOOK";

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

	public void delete(int no) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM GUESTBOOK WHERE no=?";

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

	public List<GuestBook> selectAllList() throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<GuestBook> list = new ArrayList<GuestBook>();

		String sql = "SELECT * FROM GUESTBOOK";

		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			list.add(new GuestBook(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getDate(5)));
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
	
	public GuestBook search(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GuestBook gb = new GuestBook();

		String sql = "SELECT * FROM GUESTBOOK WHERE no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			gb = new GuestBook(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getDate(5));
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

		return gb;
	}
	
}
