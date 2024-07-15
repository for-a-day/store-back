package com.nagane.franchise.util.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ljy
 * @since 2024.06.27 
 * BaseResponseBody 코드
 * 에러 시 응답값
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "응답 실패 시 response")
public class ErrorResponseBody extends BaseResponseBody {
    @Schema(description = "상태 코드", nullable = false, example="500")
    Integer statusCode = null;
    
    @Schema(description = "응답 메시지", nullable = false, example="INTERNAL_SERVER_ERROR")
    String message = null;
    
	 public ErrorResponseBody() {
        super();
    }
	
    public ErrorResponseBody(Integer statusCode) {
        super(statusCode);
    }
	
    public ErrorResponseBody(Integer statusCode, String message) {
        super(statusCode, message);
    }
    
    // 팩토리 패턴 기반으로 객체 생성하는 유틸리티 메서드
    public static ErrorResponseBody of(Integer statusCode, String message) {
        return new ErrorResponseBody(statusCode, message);
    }
}
