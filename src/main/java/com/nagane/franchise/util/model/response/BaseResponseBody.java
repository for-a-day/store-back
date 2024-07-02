package com.nagane.franchise.util.model.response;

import java.util.Map;

import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27 
 * BaseResponseBody 코드
 * 서버 요청 기본 응답값
 **/
@Data
public class BaseResponseBody {
	// 상태 코드 (예시: 200)
	Integer statusCode = null;
	// 응답 메시지 (예시: success)
	String message = null;
	// 전달할 데이터
	Map<String, Object> data = null;
	
	public BaseResponseBody() {}
	
	public BaseResponseBody(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public BaseResponseBody(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public BaseResponseBody(Integer statusCode, String message, Map<String, Object> data) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	// 팩토리 패턴 기반으로 객체 생성하는 유틸리티 메서드
	public static BaseResponseBody of(Integer statusCode, String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		body.statusCode = statusCode;
		return body;
	}
	
	public static BaseResponseBody of(Integer statusCode, String message, Map<String, Object> data) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		body.statusCode = statusCode;
		body.data = data;
		return body;
	}

}
