package com.youyi.api.exception;

import com.youyi.api.constant.ResponseStatus;
import com.youyi.api.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YOUYI
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = { 
            BindException.class, ValidationException.class, 
            MethodArgumentNotValidException.class })
    public ResponseResult<List<String>> handleParameterVerificationException(MethodArgumentNotValidException e) {
        log.info("Exception: {}", e.getMessage());
        List<String> fieldErrors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseResult.fail(fieldErrors);
    }
    @ResponseBody
    @ExceptionHandler(value = { DataException.class })
    public ResponseResult<List<String>> handleDataException(Exception e) {
        String message = e.getMessage();
        log.info("Exception: {}", e.getMessage());
        return ResponseResult.fail(Collections.singletonList(message));
    }
    @ResponseBody
    @ExceptionHandler(value = { LoginException.class })
    public ResponseResult<Object> handleLoginException(Exception e) {
        return ResponseResult.fail(ResponseStatus.HTTP_STATUS_401);
    }
}