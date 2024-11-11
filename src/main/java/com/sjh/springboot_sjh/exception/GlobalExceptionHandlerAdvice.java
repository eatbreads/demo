package com.sjh.springboot_sjh.exception;

import com.sjh.springboot_sjh.pojo.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler({Exception.class})
    public ResponseMessage<String> handleException(Exception e, HttpServletRequest httpServletRequest)
    {
        logger.error("系统异常",e);

        return new ResponseMessage(500,"系统异常",null);
    }
}
