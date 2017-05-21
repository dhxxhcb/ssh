package cn.ja.zsgc.dao.base;


import cn.ja.zsgc.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {


    private Class<T> domainClass;

    public Class<T> getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    /**
     * 为了注解给HibernateDaoSupport类里的私有sessionFactory属性注入，
     * BaseDaoImpl继承了HibernateDaoSupport,
     * BaseDaoImpl里的sessionFactory,和seSessionFactory()方法，怎么给这个注入呢？？？
     * <p/>
     * 假设BaseDaoImpl 里有变量SessionFactory sf,有set
     * "@Resource"要么根据名字注入，名字不一样会根据类型注入。
     * 注解是靠反射注入，
     * xml配置靠set方法
     * <p/>
     * <p/>
     * <p/>
     * sessionFactory /..Templete ,templete是通过sessionFactory 来实例化
     */
    @Resource
    public void setSf(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /*
    在构造方法中动态获得实体类的类型，再用反射实例化对象
     */
    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();


        domainClass = (Class<T>) actualTypeArguments[0];


    }





    @Override
    public void add(T entity) {

        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {

        return this.getHibernateTemplate().get(domainClass, id);

    }

    @Override
    public List findAll() {
        String hql = "from " + (domainClass.getSimpleName());
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List findByCriteria(DetachedCriteria dc) {
        return this.getHibernateTemplate().findByCriteria(dc);
    }

    @Override
    public List findByNameQuery(String queryName, Object... args) {
        return this.getHibernateTemplate().findByNamedQuery(queryName, args);
    }

    @Override
    public void executeNameQuery(String queryName, Object... args) {
        Session session = this.getSession();
        Query query = session.getNamedQuery(queryName);
        if (args != null && args.length > 0) {
            int i = 0;
            for (Object arg : args) {
                query.setParameter(i++, arg);
            }
        }
        query.executeUpdate();

    }

    @Override
    public void pageQuery(PageBean pageBean) {
        DetachedCriteria dc = pageBean.getDc();
        dc.setProjection(Projections.rowCount());

        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();

        List<Long> list = this.getHibernateTemplate().findByCriteria(dc);
        System.out.println(list.size());
        Long totalCount = list.get(0);
        System.out.println(totalCount);
        pageBean.setTotal(totalCount.intValue());

        dc.setProjection(null);
        dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

        int firstResult = (currentPage - 1)*pageSize;
        int maxResults = pageSize;
        List<T> staffList = this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
        pageBean.setRows(staffList);
    }



}
