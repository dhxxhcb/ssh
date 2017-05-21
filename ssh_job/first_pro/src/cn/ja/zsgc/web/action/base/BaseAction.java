package cn.ja.zsgc.web.action.base;


import cn.ja.zsgc.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;
    private DetachedCriteria dc;


    private Integer rows;
    private Integer page;

    public Integer getRows() {
        return rows;
    }

    public Integer getPage() {
        return page;
    }

    public DetachedCriteria getDc() {

        return dc;
    }

    public void setDc(DetachedCriteria dc) {
        this.dc = dc;
    }

    public void setModel(T model) {
        this.model = model;
    }

    private PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    //设置pageBean
    public void setPage(Integer page) {
        pageBean.setCurrentPage(page);
    }


    public void setRows(Integer rows) {
        pageBean.setPageSize(rows);
    }


    public BaseAction() {
        ParameterizedType genericSuperclass = null;
        Type genericSuperclass1 = this.getClass().getGenericSuperclass();

        if (genericSuperclass1 instanceof ParameterizedType) {
            genericSuperclass = (ParameterizedType) genericSuperclass1;
        } else {
            genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
        }

        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> domainClass = (Class<T>) actualTypeArguments[0];

        dc = DetachedCriteria.forClass(domainClass);

        try {
            model = domainClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


    public void writeList2Json(List list, String[] excludes) {

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);

        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        String jsonData = jsonArray.toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writePageBean2Json(String[] execludes) {

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(execludes);

        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        String jsonData = jsonObject.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        System.out.println(jsonData);

        try {

            ServletActionContext.getResponse().getWriter().print(jsonData);
        } catch (IOException e) {
            System.out.println("json数据封装出现问题！！！！");
            e.printStackTrace();
        }

    }

    @Override
    public T getModel() {
        return model;
    }
}
