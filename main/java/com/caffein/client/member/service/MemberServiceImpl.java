package com.caffein.client.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caffein.client.member.dao.MemberDAO;
import com.caffein.client.member.vo.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberVO mvo) {
		// TODO Auto-generated method stub
		memberDAO.insertMember(mvo);
	}

	@Override
	public List<MemberVO> getMemeberList() {
		// TODO Auto-generated method stub
		return memberDAO.getMemberList();
	}

	@Override
	public String getSalt(String email) {
		// TODO Auto-generated method stub
		return memberDAO.getSalt(email);
	}

	@Override
	public boolean checkMember(MemberVO mvo) {
		// TODO Auto-generated method stub
		return memberDAO.checkMember(mvo);
	}

}
