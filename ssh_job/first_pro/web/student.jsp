<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js">

</script>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
        src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
        type="text/javascript"></script>

<script>


    var url = "${pageContext.request.contextPath}/clazz_findAllClazz.action";

    //页面加载完成，用ajax获取班级
    $(function getClazz() {
                $.post(url, "", function (data) {
                    var classidTag = $("#classid");
                    classidTag.empty();
                    for (var i = 0; i < data.length; i++) {
                        var clazzid = data[i].cid;
                        classidTag.append("<option value='" + clazzid + "'>" + data[i].cname + "</option>")
                    }
                })
            }
    );
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/student_addStudent.action" method="post">
    添加
    <hr>
    <table border="1">
        <tr>
            <td>
                姓名
            </td>
            <td>
                年龄
            </td>
            <td>
                手机
            </td>
            <td>
                班级
            </td>
            <td>操作</td>
        </tr>
        <tr>
            <td>
                <input type="text" name="sname"><br/>
            </td>
            <td>
                <input type="text" name="sage"><br/>
            </td>
            <td>
                <input type="text" name="sphone"><br/>
            </td>
            <td>


                <select id="classid" name="cid">

                    <option>--选择班级--</option>
                </select>
            </td>
            <td>
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
<a href="${pageContext.request.contextPath}/student_findAllStudents.action">查询所有</a>
</body>
</html>
