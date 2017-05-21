package cn.ja.zsgc.service.base;

import cn.ja.zsgc.domain.StudentModel;
import cn.ja.zsgc.utils.PageBean;

import java.util.List;

/**
 *
 */

public interface StudentService {
    void addStudent(StudentModel studentModel);

    void deleteStuent(String[] studentModel);

    List<StudentModel> findAllStudents();

    StudentModel findStudentBySid(StudentModel studentModel);

    void updateStudent(StudentModel studentModel);

    void pageQuery(PageBean pageBean);
}
