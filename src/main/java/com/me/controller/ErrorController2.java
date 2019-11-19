package com.me.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * 方式2：@ControllerAdvice 结合 @ExceptionHandler
 */
@ControllerAdvice
public class ErrorController2 {

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView hello(Exception e) {
        return new ModelAndView("error", new ModelMap(e.getMessage()));
    }
}
