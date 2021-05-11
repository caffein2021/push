package com.caffein.client.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.caffein.client.member.vo.MemberVO;
import com.caffein.common.JDBCUtil;
import com.caffein.common.security.Security;

@Repository("MemberDAO")
public class MemberDAO {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private static final int SALT_SIZE = 16;
	private Security security = new Security();
	
	private final String MEMBER_INSERT = "INSERT INTO tbl_member(member_id, "
			+ "email, pw, name, tel, sex, age, salt, rdate) "
			+ "values((SELECT NVL(MAX(member_id), 0) + 1 FROM tbl_member), "
			+ "?, ?, ?, ?, ?, ?, ?, sysdate)";
	private final String MEMBER_CHECK = "SELECT * FROM tbl_member "
			+ "WHERE email = ? AND pw = ?";
	private final String GET_SALT = "SELECT salt FROM tbl_member "
			+ "WHERE email = ?";
	private final String MEMBER_LIST = "SELECT * FROM tbl_member";
	
	// 회원 추가 메서드
	public void insertMember(MemberVO mvo) {
		System.out.println("insertMember");
		try {
			String salt = security.generateSalt(SALT_SIZE); // security Util로 salt값 생성
			String pw = mvo.getPw();
			
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, mvo.getEmail());
			stmt.setString(2, security.hashing(pw.getBytes(), salt));
			stmt.setString(3, mvo.getName());
			stmt.setString(4, mvo.getTel());
			stmt.setInt(5, mvo.getSex());
			stmt.setInt(6, mvo.getAge());
			stmt.setString(7, salt);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, con);
		}
	}
	
	public String getSalt(String email) {
		System.out.println("getSalt");
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(GET_SALT);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, con);
		}
		return null;
	}
	
	public boolean checkMember(MemberVO mvo) {
		System.out.println("checkMember");
		try {
			String email = mvo.getEmail();
			String salt = getSalt(email);
			String pw = mvo.getPw();
			
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(MEMBER_CHECK);
			stmt.setString(1, email);
			stmt.setString(2, security.hashing(pw.getBytes(), salt));
			rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, con);
		}
		return false;
	}
	
	// 회원 리스트 출력 메서드
	public List<MemberVO> getMemberList() {
		System.out.println("getMemberList");
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setMember_id(rs.getInt(1));
				mvo.setEmail(rs.getString(2));
				mvo.setPw(rs.getString(3));
				mvo.setName(rs.getString(4));
				mvo.setTel(rs.getString(5));
				mvo.setSex(rs.getInt(6));
				mvo.setAge(rs.getInt(7));
				mvo.setSalt(rs.getString(8));
				mvo.setRdate(rs.getDate(9));
				memberList.add(mvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, con);
		}
		return memberList;
	}
}
