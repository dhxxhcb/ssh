package cn.ja.zsgc.service.impl;

import cn.ja.zsgc.dao.impl.StudentDao;
import cn.ja.zsgc.domain.StudentModel;
import cn.ja.zsgc.service.base.StudentService;
import cn.ja.zsgc.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentDao studentDao;

    @Override
    public void addStudent(StudentModel studentModel) {
        studentDao.add(studentModel);
    }

    @Override
    public void deleteStuent(String[] idsArr) {
        if(idsArr != null){
            for (int i = 0; i < idsArr.length; i++) {
                StudentModel studentModelById = studentDao.findById(Integer.parseInt(idsArr[i]));
                studentDao.delete(studentModelById);

            }
        }
    }

    @Override
    public List<StudentModel> findAllStudents() {

        return studentDao.findAll();

    }

    @Override
    public StudentModel findStudentBySid(StudentModel studentModel) {

        DetachedCriteria dc = DetachedCriteria.forClass(StudentModel.class);

        StudentModel studentDaoByDB = studentDao.findById(studentModel.getSid());
        return studentDaoByDB;


    }

    @Override
    public void updateStudent(StudentModel studentModel) {
        studentDao.update(studentModel);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        studentDao.pageQuery(pageBean);
    }


}
