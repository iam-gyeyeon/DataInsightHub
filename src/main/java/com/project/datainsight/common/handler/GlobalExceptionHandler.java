package com.project.datainsight.common.handler;

import com.project.datainsight.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<CommonResponse<String>> handleException(Exception e) {
        CommonResponse<String> response = new CommonResponse<>(
                "오류가 발생했습니다: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null
        );
        logger.error("Unexpected exception occurred: message={}", e.getMessage(), e);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
