package com.sheva.studentmanager.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheva.studentmanager.entity.Course;
import com.sheva.studentmanager.service.CourseService;
import com.sheva.studentmanager.service.CourseTypeService;
import com.sheva.studentmanager.vo.CourseQueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * @author sheva
 */
@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping("/toInput")
    public String toInput(Map<String, Object> map, Course course){
        map.put("courseTypeList", courseTypeService.list());

        course.setCourseStatus("O");
        course.setCourseReqs(new String[]{"a","b"});

        map.put("course", course);

        return "course/input_course";
    }

    @PostMapping("/create")
    public String create(@RequestParam("coursetextbookpic")MultipartFile file, Course course, Map<String, Object> map) throws Exception{
        //读取文件，转换成字节数组

        if (file != null){
            course.setCourseTextbookPic(file.getBytes());
        }

        try{
            courseService.save(course);
        }catch (Exception e){
            log.error("courseTypeList", courseTypeService.list());
            map.put("courseTypeList", courseTypeService.list());
            return "course/input_course";
        }

        return "redirect:/course/list";
    }


    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                     Map<String, Object> map, CourseQueryHelper helper){
        int pageNo = 1;

        //对pageNo进行校验
        pageNo = Integer.parseInt(pageNoStr);
        if (pageNo < 1){
            pageNo = 1;
        }

        Page<Course> page = courseService.getCoursePage(helper, pageNo, 3);

        map.put("courseTypeList", courseTypeService.list());
        map.put("page", page);
        map.put("helper", helper);

        return "course/list_course";
    }

    @DeleteMapping("/remove/{courseNo}")
    public String remove(@PathVariable("courseNo") String courseNo){
        courseService.removeById(courseNo);

        return "redirect:/course/list";
    }

    @GetMapping("/preUpdate/{courseNo}")
    public String preUpdate(@PathVariable("courseNo") String courseNo, Map<String, Object> map){
        Course course = courseService.getById(courseNo);
        course.setCourseType(courseTypeService.getById(course.getTypeId()));
        map.put("course", course);

        map.put("courseTypeList", courseTypeService.list());

        return "course/update_course";
    }

    @PostMapping("/update")
    public String update(@RequestParam("coursetextbookpic") MultipartFile file, Course course, Map<String ,Object> map) throws Exception{
        //读取多段提交的文件数据，转换成字节数组
        if (file.getBytes().length > 0){
            course.setCourseTextbookPic(file.getBytes());
        }

        try{
            courseService.updateById(course);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            map.put("courseTypeList", courseTypeService.list());

            return "/course/update_course";
        }

        return "redirect:/course/list";
    }

    @GetMapping("/getPic/{courseNo}")
        public String getPic(@PathVariable("courseNo")String courseNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] textBookPic = courseService.getTextbookPic(courseNo);

        if ((textBookPic == null) || (textBookPic.length == 0)){
            ClassPathResource classPathResource = new ClassPathResource("static/pics/default.jpg");
            InputStream inputStream = classPathResource.getInputStream();
            textBookPic = new byte[inputStream.available()];
            int i = inputStream.read(textBookPic);
            while(i != -1){
                i = inputStream.read(textBookPic);
            }
        }

        //向浏览器发送通知要法图片
        response.setContentType("/image/jpg");
        ServletOutputStream sos = response.getOutputStream();
        sos.write(textBookPic);
        sos.flush();
        sos.close();

        return null;
    }


}
