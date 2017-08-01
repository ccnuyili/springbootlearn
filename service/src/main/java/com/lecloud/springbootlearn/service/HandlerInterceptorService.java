package com.lecloud.springbootlearn.service;

/*the configured interceptor will apply to all requests handled with annotated controller
methods. If you want to narrow down the URL paths to which an interceptor applies, you can
use the MVC namespace or the MVC Java config, or declare bean instances of type
MappedInterceptor to do that。*/
/*
* The preHandle(..) method returns a boolean value. You can use this method to break or
* continue the processing of the execution chain. When this method returns true, the handler
* execution chain will continue; when it returns false, the DispatcherServlet assumes the
* interceptor itself has taken care of requests (and, for example, rendered an appropriate
* view) and does not continue executing the other interceptors and the actual handler in the
* execution chain.
* */

import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/*
经测试，HandlerInterceptorService针对的是@RequestMapping对应的方法
其处理顺序是：preHandle,@RequestMapping对应的方法,postHandle,afterCompletion.
每请求一次@RequestMapping对应的方法，就会按上面顺序跑一次。

可通过 WebMvcConfigurerAdapter 配置该HandlerInterceptorService。
 */


@Component
public class HandlerInterceptorService implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HandlerInterceptorService.class);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        System.out.println("preHandle is running");
 /*       final String authorization = request.getHeader("Authorization");
        final Optional<String> optional = Optional.ofNullable(authorization);
        if (optional.isPresent()) {
            final String au = optional.get();
            final String method = request.getMethod();
            final String uri = request.getRequestURI();
            this.checkAuth(au, method, uri);
        } else {
            throw new BadHttpRequest();
        }*/
        return true;
    }
    private void checkAuth(final String au_, final String method_, final String uri_) {
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle is running");

    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        System.out.println("afterCompletion is running");
    }
}
