package kr.co.test.week2board.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@ControllerAdvice(basePackages = "kr.co.test.week2board.board.controller")
//@Controller

@RestControllerAdvice
@Controller
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String,String>> handleException(Exception e) {
        HttpHeaders headers = new HttpHeaders();
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String,String> map = new HashMap<>();
        map.put("error", status.getReasonPhrase());
        map.put("code", e.getClass().getSimpleName()); // Todo : 정해진약이 있다면.. 줌
        map.put("message", e.getMessage()); // Todo:  필요한부분만. 전체를 다주면안됨.

        log.error("############################################:{}", e.getMessage());
        return new ResponseEntity<>(map, headers, status);
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
