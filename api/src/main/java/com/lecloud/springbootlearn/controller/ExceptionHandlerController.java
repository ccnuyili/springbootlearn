package com.lecloud.springbootlearn.controller;

import com.lecloud.commons.rest.response.exception.SpecificHttpStatusException;
import com.lecloud.commons.rest.response.exception.client.ClientException;
import com.lecloud.commons.rest.response.exception.server.ServerException;
import com.lecloud.commons.rest.response.support.ExceptionResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 通用异常处理器 <br>
 * createTime: 2017/1/09 11:21
 *
 * @author Chen Hao
 * @since 1.0.0
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ExceptionResponseEntity exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        ResponseStatus responseStatus = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        HttpStatus httpStatus = responseStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : responseStatus.code();
        return ExceptionResponseEntity.response(e, httpStatus);
    }

    @ExceptionHandler(SpecificHttpStatusException.class)
    public ExceptionResponseEntity specificHttpStatusExceptionHandler(SpecificHttpStatusException e) {
        logger.error(e.getMessage(), e);
        return ExceptionResponseEntity.response(e, e.getHttpStatus());
    }

    @ExceptionHandler(ServerException.class)
    public ExceptionResponseEntity serverExceptionHandler(ServerException e) {
        logger.error(e.getMessage(), e);
        ResponseStatus responseStatus = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        return ExceptionResponseEntity.response(e, responseStatus.code());
    }

    @ExceptionHandler(ClientException.class)
    public ExceptionResponseEntity clientExceptionHandler(ClientException e) {
        logger.error(e.getMessage(), e);
        ResponseStatus responseStatus = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        return ExceptionResponseEntity.response(e, responseStatus.code());
    }

}
