package com.sheva.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheva.studentmanager.entity.Course;
import com.sheva.studentmanager.entity.CourseType;
import com.sheva.studentmanager.mapper.CourseMapper;
import com.sheva.studentmanager.mapper.CourseTypeMapper;
import com.sheva.studentmanager.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sheva
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements CourseTypeService {

    @Autowired
    CourseMapper courseMapper;
    @Override
    public void deleteCourseType(Integer typeId) {
        courseMapper.delete(
                new LambdaQueryWrapper<Course>()
                .eq(Course::getTypeId, typeId)
        );
        baseMapper.deleteById(typeId);
    }
}
