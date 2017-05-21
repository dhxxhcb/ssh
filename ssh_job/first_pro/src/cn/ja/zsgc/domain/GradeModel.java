package cn.ja.zsgc.domain;

/**
 *
 */
public class GradeModel {
    private int gid;
    private String gname;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeModel that = (GradeModel) o;

        if (gid != that.gid) return false;
        if (gname != null ? !gname.equals(that.gname) : that.gname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (gname != null ? gname.hashCode() : 0);
        return result;
    }
}
