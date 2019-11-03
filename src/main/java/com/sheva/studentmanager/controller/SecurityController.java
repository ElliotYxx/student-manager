package com.sheva.studentmanager.controller;

import com.sheva.studentmanager.annotation.CurrentUser;
import com.sheva.studentmanager.entity.User;
import com.sheva.studentmanager.entity.UserInfo;
import com.sheva.studentmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Sheva
 * @data 2019/11/3  下午1:39
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/index")
    public String root(){
        return "index";
    }
    
    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map){
        map.put("user", new User());
        
        return "login";
    }
    
    @ResponseBody
    @PostMapping("/login")
    public Boolean login(@RequestBody User user, Map<String, Object> map,
                         @CurrentUser UserInfo userInfo){
        
        log.info(userInfo.toString());
        
        if (userService.getById(user.getUserNo()) != null){
            User user1 = userService.getById(user.getUserNo());
            if (user1.getUserPwd().equals(user.getUserPwd())){
                map.put("user", user1);
                return true;
            }
        }
        
        return false;
    }
    
    @GetMapping("/mainController/{userNo}")
    public String main(@PathVariable String userNo, Map<String, Object> map){
        map.put("user", userService.getById(userNo));
        return "main";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/security/toLogin";
    }
}
