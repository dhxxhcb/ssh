<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <td>id</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>电话</td>
        <td>班级id</td>
        <td>修改</td>
        <td>删除</td>
    </tr>

    <s:iterator value="allStudents" var="student">
    <tr>
        <td><s:property value="#student.sid"/> </td>
        <td><s:property value="#student.sname"/> </td>
        <td><s:property value="#student.sage"/> </td>
        <td><s:property value="#student.sphone"/> </td>
        <td><s:property value="#student.cid"/> </td>
        <td>
            <s:a action="student_toUpdateUI">
                修改
                <s:param name="sid"  value="sid"/>
            </s:a>

        </td>
        <td>
            <s:a action="student_deleteStudent.action">
                删除
                <s:param name="sid"  value="sid"/>
            </s:a>

        </td>

    </tr>
    </s:iterator>
</table>
</body>
</html>
