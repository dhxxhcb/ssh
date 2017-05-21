package cn.ja.zsgc.web.action.base;

import cn.ja.zsgc.dao.impl.ClazzDao;
import cn.ja.zsgc.dao.impl.ClazzDaoImpl;
import cn.ja.zsgc.domain.ClassModel;
import cn.ja.zsgc.domain.StudentModel;
import cn.ja.zsgc.service.base.ClazzService;
import cn.ja.zsgc.service.base.StudentService;
import cn.ja.zsgc.service.impl.ClazzServiceImpl;
import cn.ja.zsgc.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

/**
 *
 */

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<StudentModel> {


    @Resource
    private StudentService studentService;
    @Resource
    private ClazzService clazzService;

    private String ids;
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    //添加
    public String addStudent() {


        //ClassModel classModelByDB = clazzService.findClazzById(Integer.parseInt(cid));
       // this.getModel().setClassModel(classModelByDB);
        studentService.addStudent(this.getModel());
        return "home";
    }

    //查询所有
    public String findAllStudents() {
        List<StudentModel> studentModelList = studentService.findAllStudents();

        for (StudentModel model : studentModelList) {

            System.out.println(model.getSname() + "..." + model.getSid());
        }

       /* ServletActionContext.getRequest().getSession().setAttribute("studentModelList", studentModelList);
        ActionContext.getContext().getValueStack().set("allStudents",studentModelList);*/

        String[] exclude = {"classModel"};
        writeList2Json(studentModelList, exclude);
        return NONE;


    }

    //到修改要页面
    public String toUpdateUI() {

        int sid = this.getModel().getSid();
        StudentModel studnetByDb = studentService.findStudentBySid(this.getModel());

        ActionContext.getContext().getValueStack().set("student", studnetByDb);


        return "updatestudent";


    }

    //修改
    public String update() {

        StudentModel studentModelByDB = studentService.findStudentBySid(getModel());
        studentModelByDB.setSid(getModel().getSid());
        studentModelByDB.setSname(getModel().getSname());
        studentModelByDB.setSphone(getModel().getSphone());
        studentModelByDB.setClassModel(getModel().getClassModel());
/*        DetachedCriteria dc = DetachedCriteria.forClass(ClassModel.class);
        dc.add(Restrictions.eq("cid", cid));

        ClassModel clazzByDb = clazzService.findClazzById(Integer.parseInt(cid));
        studentModelByDB.setClassModel(clazzByDb);*/

        studentService.updateStudent(studentModelByDB);
        return "home";
    }

    //删除
    public String delete() {
        if (ids != null) {
            String[] idArr = this.ids.split(",");
            //studentService.deleteStuent(idArr);
        }

        return "home";
    }

    //分页
    public String pageQuery() throws IOException {
        PageBean pageBean = this.getPageBean();



        DetachedCriteria dc = DetachedCriteria.forClass(StudentModel.class);


        //有条件的分页查询
        StudentModel queryModelCondition = getModel();

        if(queryModelCondition != null){
            if(!StringUtils.isBlank(queryModelCondition.getSname())){
                String queryname = queryModelCondition.getSname();
                queryname = queryname.trim();
                dc.add(Restrictions.like("sname", "%" + queryname+"%"));

            }
            if(!StringUtils.isBlank(queryModelCondition.getSphone())){
                String queryPhone = queryModelCondition.getSphone();
                queryPhone = queryPhone.trim();
                dc.add(Restrictions.like("sphone", queryPhone));
            }

            if(queryModelCondition.getSage() != null){
                Integer querySage = queryModelCondition.getSage();
                dc.add(Restrictions.eq("sage", querySage));
            }

            if(!StringUtils.isBlank(cid) && !cid.equals("--选择班级--")){
                DetachedCriteria clazzDc = dc.createCriteria("classModel");
                clazzDc.add(Restrictions.eq("cid", Integer.parseInt(cid)));
            }

        }


        pageBean.setDc(dc);

        studentService.pageQuery(pageBean);



        String[] exclude = {"currentPage", "pageSize", "dc", "classModel", "gid", "clazzService"};
        writePageBean2Json(exclude);
        return NONE;
    }

    //获取根据学生id查班级id
    public String getClazzID(){

        StudentModel studentModelByDB = studentService.findStudentBySid(this.getModel());
        Integer cid = studentModelByDB.getClassModel().getCid();

        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(cid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }



}
