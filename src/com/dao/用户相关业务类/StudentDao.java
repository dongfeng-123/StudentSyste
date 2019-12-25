package com.dao.用户相关业务类;

import 实体类.User.Student;

import java.util.List;

/**
 * @author 风
 * @date 2019/12/24 - 15:18
 */
public interface StudentDao {
    public List<Student> getAllStudent();
    //增加学生
    public void add(String name, String age, int sex);
    //删除学生
    public void delete(int id);
    //修改学生
    public void updateDate(Student stu);
    //条件查询
    public List<Student> queryOne(String sql);


}
