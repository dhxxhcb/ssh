package cn.ja.zsgc.service.impl;

import cn.ja.zsgc.dao.impl.ClazzDao;
import cn.ja.zsgc.domain.ClassModel;
import cn.ja.zsgc.service.base.ClazzService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional
@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzDao clazzDao;

    @Override
    public List<ClassModel> findAllClazz() {
        return clazzDao.findAll();
    }

    @Override
    public ClassModel findClazzById(Integer cid) {
       return clazzDao.findById(cid);
    }

    @Override
    public List<ClassModel> findByCriteria(DetachedCriteria dc) {
        return clazzDao.findByCriteria(dc);

    }
}
