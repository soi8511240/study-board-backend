package kr.co.test.week2board.board.util;

public class CommonResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String message;
    private String success;
    private T data;

    // 기본 생성자
    private CommonResponse(T data, String message, String success) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    /** 성공 응답을 반환하는 정적 팩토리 메서드 */
    public static <T> CommonResponse<T> apiResponse(T data) {
        return new CommonResponse<>(data, "Request processed successfully", SUCCESS_STATUS);
    }

    /** 에러 응답을 반환하는 정적 팩토리 메서드 */
    public static <T, E> CommonResponse<T> apiResponse(E exception, String message) {

        String errorMessage;

        // 에러 객체의 타입에 따라 메시지를 처리
        if (exception instanceof Exception) {
            // Exception이면 에러 메시지 포함
            errorMessage = message + " | " + ((Exception) exception).getMessage();
        } else {
            // 다른 타입의 객체는 기본적으로 toString() 사용
            errorMessage = message + " | " + exception.toString();
        }

        return new CommonResponse<>(null, message + " | " + errorMessage, ERROR_STATUS);
    }


}
