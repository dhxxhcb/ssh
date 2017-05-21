package cn.ja.zsgc.web.action.base;

import cn.ja.zsgc.domain.StudentModel;
import cn.ja.zsgc.service.base.StudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 *
 */
@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<StudentModel> {

    @Resource
    private StudentModel studentMode = new StudentModel();

    @Resource
    StudentService studentService;

    public String addStudent(){

        StudentModel studentModel = new StudentModel();

        studentModel.setCname("张三");
        studentService.addStudent(studentModel);

        return NONE;

    }




}
