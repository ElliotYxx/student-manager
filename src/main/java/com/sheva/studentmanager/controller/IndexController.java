package com.sheva.studentmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sheva
 * @data 2019/11/3  下午1:36
 * @Version 1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String root(){
        return "index";
    }
}
