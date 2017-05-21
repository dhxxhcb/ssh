package cn.ja.zsgc.dao.impl;

import cn.ja.zsgc.dao.base.BaseDaoImpl;
import cn.ja.zsgc.domain.StudentModel;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *
 */
@Repository
public class StudentDaoImpl extends BaseDaoImpl<StudentModel> implements StudentDao {


}
