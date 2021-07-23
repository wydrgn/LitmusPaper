package com.drgn.common.exception;

import com.drgn.common.model.web.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e) throws Exception {
        if (e instanceof MethodArgumentNotValidException) { // 数据校验异常，仅提示
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
            Map<String, String> errorMap = fieldErrorList.stream().
                    collect(Collectors.toMap(fe -> fe.getObjectName() + "." + fe.getField()
                            , fe -> StringUtils.isEmpty(fe.getDefaultMessage()) ? " " : fe.getDefaultMessage(),
                            (k1, k2) -> k1 + "," + k2));
            return Result.FAIL(errorMap);
        }
        if (e instanceof HttpRequestMethodNotSupportedException) { //请求方法异常, 仅提示
            return Result.FAIL("不支持的请求类型，消息提示：" + e.getMessage());
        }
        if (e instanceof BaseException) { // 自定义异常
            //throw e;
            return Result.FAIL(e.getMessage());
        }
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) e;
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Result.FAIL("找不到对应请求地址");
            }
        }
        if (e instanceof IllegalStateException) {
            return Result.FAIL(e.getMessage());
        }
        //feign
        /*if (e instanceof RetryableException) {
            return Result.FAIL(e.getMessage());
        }*/
        return Result.FAIL(e.getMessage());
    }
}
