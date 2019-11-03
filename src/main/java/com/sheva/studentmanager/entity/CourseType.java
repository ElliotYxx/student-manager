package com.sheva.studentmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tbl_course_type")
public class CourseType implements Serializable {
    private static final long serialVersionUID = 8802173717644310430L;


    @TableId(type = IdType.AUTO)
    private Integer typeId;

    private String typeName;

}
