package cn.ja.zsgc.test;

import cn.ja.zsgc.service.base.StudentService;
import cn.ja.zsgc.web.action.base.StudentAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class TestSSH {

    @Resource
    StudentService studentService;
    @Resource
   // StudentAction studentAction;

    @Test
    public void testAdd() {
       // studentAction.addStudent();
        // studentAction.addStudent();
       /* StudentModel studentModel = new StudentModel();
        studentModel.setCname("ddd");
        studentService.addStudent(studentModel);*/


    }

}
