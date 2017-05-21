package cn.ja.zsgc.dao.base;


import cn.ja.zsgc.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public interface IBaseDao<T> {
    /**
     * 添加
     *
     * @param entity
     */
    public void add(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T findById(Serializable id);

    public List<T> findAll();

    public List<T> findByCriteria(DetachedCriteria dc);

    public List<T> findByNameQuery(String queryName, Object... args);

    public void executeNameQuery(String queryName, Object... args);

   public void pageQuery(PageBean pageBean);



}

