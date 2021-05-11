package com.caffein.client.member.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.caffein.client.member.vo.MemberVO;

public class MemberServiceClient {
	
	public void memberTest() {
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		MemberService memberService = (MemberService) container.getBean("MemberService");
		
		MemberVO mvo = new MemberVO();
		mvo.setEmail("test@test.com");
		mvo.setPw("1234");
		mvo.setName("test");
		mvo.setTel("01010001000");
		mvo.setSex(0);
		mvo.setAge(0);
		
		memberService.insertMember(mvo);
		
		List<MemberVO> memberList = memberService.getMemeberList();
		for (MemberVO memberVO : memberList) {
			System.out.println(memberVO.toString());
		}
		
		container.close();
	}
	
	@Test
	public void memberCheckTest() {
		try (AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml")) {
			MemberService memberService = (MemberService) container.getBean("MemberService");
			
			MemberVO mvo = new MemberVO();
			mvo.setEmail("test@test.com");
			mvo.setPw("1234");
			
			if(memberService.checkMember(mvo)) {
				System.out.println("로그인 성공");
			} else {
				System.out.println("로그인 실패");
			}
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
