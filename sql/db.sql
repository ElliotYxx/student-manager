
create table tbl_course_type(
    type_id int primary key auto_increment comment '课程ID',
    type_name varchar(30) comment '课程类型名称'
)engine=Innodb default charset=utf8;

insert into tbl_course_type(type_name) values ('专业必修');

insert into tbl_course_type(type_name) values ('专业任选');

insert into tbl_course_type(type_name) values ('校选课');

insert into tbl_course_type(type_name) values ('专家讲座');

create table tbl_course(
    course_no varchar(100) primary key comment '主键，课程编号',
    course_name varchar(100) comment '课程名称',
    course_hours int not null comment '课程课时',
    type_id int not null comment '课程类型id',
    course_status varchar(5) comment '课程状态，O开放选课  Z暂不开放  C停止授课 ',
    course_Reqs varchar(100) comment '授课要求',
    course_point decimal(4,1) comment '学分',
    course_meno text comment '备注说明',
    course_textbook_pic mediumblob comment '教材封面',
    constraint fk_course_type foreign key (type_id) references tbl_course_type(type_id)
)engine=Innodb default charset=utf8;

create table tbl_users(
    user_no varchar(100) primary key comment '用户账号',
    user_pwd varchar(100) comment '密码',
    user_name varchar(100) comment '用户名称'
)engine=Innodb default charset=utf8;

insert into tbl_users values ('000101','123456','Listen');
insert into tbl_users values('000102','123456','Jerry');