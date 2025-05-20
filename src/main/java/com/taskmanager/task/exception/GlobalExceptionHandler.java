package com.taskmanager.task.exception;

import com.taskmanager.task.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex){
    ApiResponse<Object>response=new ApiResponse<>(false,ex.getMessage(),null);
    return ResponseEntity.badRequest().body(response);
    }


}
