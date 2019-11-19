package com.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * 方式1：@Controller 结合 @ExceptionHandler
 */
@Controller
@RequestMapping("/test")
public class ErrorController {


    @ExceptionHandler({RuntimeException.class})
    public ModelAndView hello(Exception e) {
        return new ModelAndView("error", new ModelMap(e.getMessage()));
    }
}
