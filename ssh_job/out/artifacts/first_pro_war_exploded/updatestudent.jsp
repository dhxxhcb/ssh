<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/student_updateStudent.action">
    <input type="hidden" name="sid" value="${student.sid}">
    <table border="1">
        <tr>
            <td>
                姓名
            </td>
            <td>
                年龄
            </td>
            <td>
                电话
            </td>
            <td>

            </td>
            <td>操作</td>
        </tr>
        <tr>
            <td>
                <input type="text" name="sname" value="${student.sname}">
            </td>
            <td>
                <input type="text" name="sage" value="${student.sage}">
            </td>
            <td>
                <input type="text" name="sphone" value="${student.sphone}">

            </td>
            <td>
                <input type="submit" value="提交修改">
            </td>
        </tr>

    </table>


</form>

</body>
</html>
