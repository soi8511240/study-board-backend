package kr.co.test.week2board.exception;

import kr.co.test.week2board.response.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@Slf4j
//@ControllerAdvice(basePackages = "kr.co.test.week2board.board.controller")
//@Controller

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 에러 메시지 템플릿
    protected static final Map<Class<? extends Exception>, String> ERROR_MESSAGES = Map.of(
            NullPointerException.class, "필수 값이 누락되었습니다.",
            IllegalArgumentException.class, "잘못된 인자가 제공되었습니다.",
            IllegalStateException.class, "현재 애플리케이션 상태가 유효하지 않습니다.",
            RuntimeException.class, "런타임 예외가 발생했습니다.",
            IOException.class, "파일처리중 예외가 발생했습니다.",
            Exception.class, "처리 중에 에러가 발생했습니다." // 기본 메시지
    );

    @ExceptionHandler({Exception.class})
    public GlobalResponse<String> handleException(Exception e) {
        String defaultMessage = ERROR_MESSAGES.getOrDefault(Exception.class, "알 수 없는 예외가 발생했습니다.");
        String message = ERROR_MESSAGES.getOrDefault(e.getClass(), defaultMessage);
        // 새로운 예외와 메시지 추가
        // ERROR_MESSAGES.put(FileNotFoundException.class, "요청한 파일을 찾을 수 없습니다.");
        // ERROR_MESSAGES.put(SQLException.class, "데이터베이스 작업 중 오류가 발생했습니다.");

        log.error("\n\n\n\n\n\n############################################ errorHandler:{}\n\n\n\n\n\n\n\n", e.getMessage());
        return GlobalResponse.apiResponse(e, message);
    }

}
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Map<String,String>> handleException(Exception e) {
//        HttpHeaders headers = new HttpHeaders();
////        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//
//        Map<String,String> map = new HashMap<>();
//        map.put("error", status.getReasonPhrase());
//        map.put("code", e.getClass().getSimpleName());
//        map.put("message", e.getMessage());
//
//        log.error("############################################:{}", e.getMessage());
//        return new ResponseEntity<>(map, headers, status);
//    }
//
////
//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<String> handleArithmeticException(ArithmeticException e) {
//        log.error("ArithmeticException: {}",e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
//        log.error("IllegalArgument: {}",e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//    }
//
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<Object> handleException(Exception e) {
////        log.error("Exception: {}",e.getMessage());
////
////        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
////    }
//}
