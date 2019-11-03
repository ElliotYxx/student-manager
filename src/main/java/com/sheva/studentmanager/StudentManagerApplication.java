package com.sheva.studentmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.sheva.studentmanager.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class StudentManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagerApplication.class, args);
    }

}
