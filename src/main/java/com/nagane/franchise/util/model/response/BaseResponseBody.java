package com.nagane.franchise.util.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ljy
 * @since 2024.06.27 
 * BaseResponseBody 코드
 * 서버 요청 기본 응답값
 **/
@Data
@Schema(description = "응답용 response")
public class BaseResponseBody {
    @Schema(description = "상태 코드", nullable = false, example="200")
    Integer statusCode = null;
    
    @Schema(description = "응답 메시지", nullable = false, example="Success")
    String message = null;
    
    public BaseResponseBody() {}
    
    public BaseResponseBody(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    public BaseResponseBody(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    
    // 팩토리 패턴 기반으로 객체 생성하는 유틸리티 메서드
    public static BaseResponseBody of(Integer statusCode, String message) {
        return new BaseResponseBody(statusCode, message);
    }
}