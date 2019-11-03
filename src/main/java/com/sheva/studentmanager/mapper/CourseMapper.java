package com.sheva.studentmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheva.studentmanager.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 获取course分页数据
     */

    List<Course> getCourseList(Page<Course> coursePage, @Param("ew") Wrapper wrapper);
}
