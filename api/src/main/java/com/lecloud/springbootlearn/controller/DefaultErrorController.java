package com.lecloud.springbootlearn.controller;

import com.lecloud.commons.rest.response.exception.SpecificHttpStatusException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 替代springboot处理未经其他程序处理过的异常 <br>
 * createTime: 2017/1/29 15:05
 *
 * @author Chen Hao
 * @since 1.0.0
 */
@Controller
@RequestMapping("/error")
//@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public void error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : statusCode;
        HttpStatus httpStatus;
        try {
            httpStatus = HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Object msg = request.getAttribute("javax.servlet.error.message");
        String message = String.valueOf(msg == null ? "" : msg);
        message = StringUtils.hasLength(message) ? message : httpStatus.getReasonPhrase();
        throw new SpecificHttpStatusException(httpStatus, message);
    }
}
