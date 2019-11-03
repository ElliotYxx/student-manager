package com.sheva.studentmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheva.studentmanager.entity.CourseType;
import com.sheva.studentmanager.service.CourseService;
import com.sheva.studentmanager.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Sheva
 * @data 2019/11/3  下午1:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
    
    @Autowired
    private CourseTypeService courseTypeService;
    
    @Autowired
    private CourseService courseService;

    /**
     * 访问课程类型输入界面
     */
    @GetMapping("/toInput")
    public String input(Map<String, Object> map){
        map.put("courseType", new CourseType());
        
        return "courseType/input_course_type";
    }

    /**
     * 创建新课程类型
     */
    @PostMapping("/create")
    public String create(CourseType courseType){
        courseTypeService.save(courseType);
        return "redirect:/courseType/list";
    }
    
    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr){
        int pageNo = 1;
        
        pageNo = Integer.parseInt(pageNoStr);
        if (pageNo < 1){
            pageNo = 1;
        }

        Page<CourseType> page = new Page<>(pageNo, 3);
        IPage<CourseType> iPage = courseTypeService.page(page,
                new LambdaQueryWrapper<CourseType>()
        .orderByAsc(CourseType::getTypeId)
        );
        
        map.put("page", iPage);
        
        return "courseType/list_course_type";
    }
    
    @DeleteMapping("/preUpdate/{typeId}")
    public String preUpdate(@PathVariable("typeId") Integer typeId, Map<String, Object> map){
        
        map.put("courseType", courseTypeService.getById(typeId));
        return "courseType/update_course_type";
    }
    
    @PutMapping("/update")
    public String update(CourseType courseType){
        
        courseTypeService.updateById(courseType);
        
        return "redirect:/courseType/list";
    }
    
    
}
