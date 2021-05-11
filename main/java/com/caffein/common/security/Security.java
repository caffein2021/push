package com.caffein.common.security;
	
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Security {
	
	// 비밀번호 해싱  
	public String hashing(byte[] pw, String salt) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용 
			
		for(int i = 0; i < 10000; i++) {
			String temp = ByteToString(pw) + salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성 
			md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다 
			pw = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다 
		}
		
		return ByteToString(pw);
	}
	
	// SALT 값 생성  
	public String generateSalt(int SALT_SIZE) throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rnd.nextBytes(temp);
		
		return ByteToString(temp);
		
	}
	
	// 바이트 값을 16진수로 변경해준다 
	public String ByteToString(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
}