package cn.ja.zsgc.service.base;

import cn.ja.zsgc.domain.ClassModel;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 *
 */
public interface ClazzService {
    List<ClassModel> findAllClazz();

    ClassModel findClazzById(Integer cid);
    List<ClassModel> findByCriteria(DetachedCriteria dc);
}
