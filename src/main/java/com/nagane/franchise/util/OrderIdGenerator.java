package com.nagane.franchise.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author ljy
 * @since 2024.07.01
 * 주문번호 생성 메서드 정리
 * **/
public class OrderIdGenerator {
	// 날짜 형식 지정
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
	
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
	
	// 숫자 문자열로 변환
	private static String formatNumber(int number) {
		String formattedNumber = String.format("%04d", number);
		return formattedNumber;
	}
	
	// 주문 코드 생성 메서드
	public static String generateOrderCode(int todayCnt) {
		// 오늘 날짜 가져와서 format화
		String today = LocalDate.now().format(dateFormatter);
		
		// 거래처 이니셜 추가
		String shopInitials = "JS";
		
		// 랜덤 문자열 생성
		String randomString = generateRandomString(4);
		
		// 숫자 포맷화
		String settingNumber = formatNumber(todayCnt);
		
		// 완성된 주문번호 return
		return today + shopInitials + randomString + settingNumber;
	}
}
