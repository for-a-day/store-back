package com.nagane.franchise.util;

import java.util.Random;

/**
 * @author ljy
 * @since 2024.07.01
 * 테이블 코드 생성 메서드 정리
 * **/
public class TableCodeGenerator {
	
	// 랜덤 문자열 생성을 위한 문자열 세팅
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	// 랜덤 문자열 생성
	private static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random ramdom = new Random();
		for (int i = 0; i < length; i++) {
			// CHARACTERS 내에서 랜덤한 숫자 하나를 통해 단일 CHAR 값 가져와서 sb에 더함
			sb.append(CHARACTERS.charAt(ramdom.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}

	
	// 주문 코드 생성 메서드
	public static String generateTableCode() {
		
		// 랜덤 문자열 생성
		String randomString = generateRandomString(6);

		// 완성된 주문번호 return
		return randomString;
	}
}
