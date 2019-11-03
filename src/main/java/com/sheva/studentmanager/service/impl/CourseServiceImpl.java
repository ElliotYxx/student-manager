package com.sheva.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheva.studentmanager.entity.Course;
import com.sheva.studentmanager.mapper.CourseMapper;
import com.sheva.studentmanager.mapper.CourseTypeMapper;
import com.sheva.studentmanager.service.CourseService;
import com.sheva.studentmanager.vo.CourseQueryHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sheva
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Override
    public byte[] getTextbookPic(String courseNo) {
        Course course = baseMapper.selectById(courseNo);

        if (course.getCourseTextbookPic() != null){
            return course.getCourseTextbookPic();
        }
        return null;
    }

    @Override
    public Page<Course> getCoursePage(CourseQueryHelper helper, Integer pageNo, Integer pageSize) {
        Page<Course> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(helper.getQryCourseName())){
            courseWrapper.like("course_name", helper.getQryCourseName());
        }

        if (helper.getQryStartPoint() != null){
            courseWrapper.lambda().le(Course::getCoursePoint, helper.getQryStartPoint());
        }

        if (helper.getQryEndPoint() != null){
            courseWrapper.lambda().le(Course::getCoursePoint, helper.getQryEndPoint());
        }

        if (StringUtils.isNotEmpty(helper.getQryCourseType())){
            courseWrapper.like("type_id", Integer.parseInt(helper.getQryCourseType()));
        }

        List<Course> courseList = baseMapper.getCourseList(page, courseWrapper);

        courseList.forEach(course ->
                course.setCourseType(courseTypeMapper.selectById(course.getTypeId()))
        );

        if (CollectionUtils.isNotEmpty(courseList)){
            page.setRecords(courseList);
            return page;
        }

        return new Page<>();

    }
}
