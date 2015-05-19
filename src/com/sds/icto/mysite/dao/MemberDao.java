package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.vo.MemberVo;

@Repository
public class MemberDao {
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

	public void insert(MemberVo vo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into MEMBER VALUES(MEMBER_NO_SEQ.nextval, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			int rowCount = pstmt.executeUpdate();

			System.out.println(rowCount + "건이 입력되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void update(MemberVo vo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET password = ? WHERE no = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setLong(2, vo.getNo());
			int rowCount = pstmt.executeUpdate();

			System.out.println(rowCount + "건이 수정되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public MemberVo getMember(String email, String password) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo member = null;

		String sql = "SELECT * FROM MEMBER WHERE email=? ";

		if(password!=null){
			sql +=" and password = ?";
		}
		
		System.out.println(email + ":" + password);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			if(password!=null){
				pstmt.setString(2, password);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberVo(rs.getLong(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
				return member;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
