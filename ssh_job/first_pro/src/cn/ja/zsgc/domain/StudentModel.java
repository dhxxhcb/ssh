package cn.ja.zsgc.domain;

import cn.ja.zsgc.dao.impl.ClazzDaoImpl;
import cn.ja.zsgc.service.base.ClazzService;
import cn.ja.zsgc.service.impl.ClazzServiceImpl;

import javax.annotation.Resource;

/**
 *
 */
public class StudentModel {
    private int sid;
    private String sname;
    private Integer sage;
    private String sphone;

    private Integer gid;
    private ClassModel classModel;


    //获取班级
    public String getClazzName() {

        if (classModel != null && classModel.getCname() != null) {
            return classModel.getCname();
        }

        return "未分配班级";
    }

    public String getCid(){
        if(classModel != null){
            Integer cid = classModel.getCid();
            if (cid != null) {
                return cid.toString();
            }

        }
        return "无";
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String cname) {
        this.sname = cname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
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

        StudentModel that = (StudentModel) o;

        if (sid != that.sid) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (sage != null ? !sage.equals(that.sage) : that.sage != null) return false;
        if (sphone != null ? !sphone.equals(that.sphone) : that.sphone != null) return false;

        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (sage != null ? sage.hashCode() : 0);
        result = 31 * result + (sphone != null ? sphone.hashCode() : 0);

        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        return result;
    }
}
