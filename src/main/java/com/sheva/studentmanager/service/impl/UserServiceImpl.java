package com.sheva.studentmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheva.studentmanager.entity.User;
import com.sheva.studentmanager.mapper.UserMapper;
import com.sheva.studentmanager.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sheva
 * @data 2019/11/1  下午2:16
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
