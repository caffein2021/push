package com.caffein.client.member.service;

import java.util.List;

import com.caffein.client.member.vo.MemberVO;

public interface MemberService {
	void insertMember(MemberVO mvo);
	String getSalt(String email);
	boolean checkMember(MemberVO mvo);
	List<MemberVO> getMemeberList();
}
