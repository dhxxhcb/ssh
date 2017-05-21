package cn.ja.zsgc.web.action.base;

import cn.ja.zsgc.domain.ClassModel;
import cn.ja.zsgc.domain.StudentModel;
import cn.ja.zsgc.service.base.ClazzService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Controller
@Scope("prototype")
public class CalzzAction extends ActionSupport implements ModelDriven<ClassModel> {

    private ClassModel classModel = new ClassModel();

    @Resource
    private ClazzService clazzService;


    public String findAllClazz() {

        List<ClassModel> classModelList = clazzService.findAllClazz();

        ActionContext.getContext().getValueStack().set("clazzList", classModelList);


        JsonConfig jsonConfig = new JsonConfig();
        String[] arr = {"studentModelset", "gid"};
        jsonConfig.setExcludes(arr);

        JSONArray jsonArray = JSONArray.fromObject(classModelList, jsonConfig);
        String jsonData = jsonArray.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");


        try {
            ServletActionContext.getResponse().getWriter().print(jsonData);
        } catch (IOException e) {
            System.out.println("打印到页面异常。。。。。");
            e.printStackTrace();
        }

        return NONE;

    }


    @Override
    public ClassModel getModel() {
        return classModel;
    }
}
