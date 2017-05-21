package cn.ja.zsgc.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ClassModel {
    private Integer cid;
    private String cname;
    private Integer gid;


    private Set<StudentModel> studentModelset = new HashSet<StudentModel>();

    public Set<StudentModel> getStudentModelset() {
        return studentModelset;
    }

    public void setStudentModelset(Set<StudentModel> studentModelset) {
        this.studentModelset = studentModelset;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassModel that = (ClassModel) o;

        if (cid != that.cid) return false;
        if (cname != null ? !cname.equals(that.cname) : that.cname != null) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        return result;
    }
}
