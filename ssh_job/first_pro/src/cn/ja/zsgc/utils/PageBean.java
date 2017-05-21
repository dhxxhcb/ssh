package cn.ja.zsgc.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 *
 */
public class PageBean {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
    private DetachedCriteria dc;
    private List rows;



    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public DetachedCriteria getDc() {
        return dc;
    }

    public void setDc(DetachedCriteria dc) {
        this.dc = dc;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getPageSize() {

        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
