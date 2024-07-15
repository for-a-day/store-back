package com.nagane.franchise.util.model.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ljy
 * @since 2024.06.27 
 * BaseResponseBody 코드
 * return 값 있을 시 응답값
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "성공 응답용 response")
public class SuccessResponseBody extends BaseResponseBody {
    @Schema(description = "전달할 데이터", nullable = true, example="Map<String, Object> data")
    Map<String, Object> data = null;

    public SuccessResponseBody() {
        super();
    }
    
    public SuccessResponseBody(Integer statusCode, String message, Map<String, Object> data) {
        super(statusCode, message);
        this.data = data;
    }
    
    // 팩토리 패턴 기반으로 객체 생성하는 유틸리티 메서드
    public static SuccessResponseBody of(Integer statusCode, String message, Map<String, Object> data) {
        return new SuccessResponseBody(statusCode, message, data);
    }
}