package com.sheva.studentmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheva.studentmanager.entity.CourseType;

/**
 * @author sheva
 */
public interface CourseTypeService extends IService<CourseType> {

    void deleteCourseType(Integer typeId);
}
