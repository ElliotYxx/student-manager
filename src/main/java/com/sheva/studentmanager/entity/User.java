package com.sheva.studentmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tbl_users")
public class User implements Serializable {
    private static final long serialVersionUID = 8687078013349820251L;

    @TableId(type = IdType.AUTO)
    private String userNo;

    private String userPwd;

    private String userName;

}
