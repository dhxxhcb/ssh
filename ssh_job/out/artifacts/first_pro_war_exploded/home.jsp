<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">


        //页面加载完成，用ajax获取班级
        $(function () {
                    var url = "${pageContext.request.contextPath}/clazz_findAllClazz.action";

                    $.post(url, "", function (data) {
                        var classidTag = $("#classid");
                        classidTag.empty();
                        classidTag.append("<option value=''>--选择班级--</option>");
                        for (var i = 0; i < data.length; i++) {
                            var clazzid = data[i].cid;
                            classidTag.append("<option value='" + clazzid + "'>" + data[i].cname + "</option>")
                        }
                    })
                }
        );


        function doAdd() {
            //alert("增加...");
            $('#addStaffWindow').window("open");
        }


        function doView() {
            location.href = "${pageContext.request.contextPath}/findStudents.jsp";
        }


        //作废
        function doDelete() {
            var rows = $("#grid").datagrid("getSelections");

            if (rows.length == 0) {
                $.messager.alert("提示信息", "请选择作废记录", "warning");
            } else {

                $.messager.confirm("提示", "确定要删除选中的学生吗？", function (data) {

                    if (data) {
                        var arr = [];
                        alert(rows.length + "cd");
                        for (var i = 0; i < rows.length; i++) {
                            arr.push(rows[i].sid);
                        }
                        var ids = arr.join(",");

                        window.location.href = "${pageContext.request.contextPath}/student_delete.action?ids=" + ids;
                    }
                })
            }
        }

        function doRestore() {
            alert("...");
        }
        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];


        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
            sortable: true

        }, {
            field: 'sid',
            title: 'id',
            width: 120,
            align: 'center'
        }, {
            field: 'sname',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'sage',
            title: '年龄',
            width: 120,
            align: 'center',
            editor: {
                type: 'validatebox',
                options: {
                    required: true
                }
            }
        }, {
            field: 'sphone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'cid',
            hidden: true

        }, {
            field: 'clazzName',
            title: '班级',
            width: 120,
            align: 'center'

        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [2, 5, 100],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/student_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow

            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });


            // 添加取派员窗口
            $('#editStaffWindow').window({
                title: '添加学生',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            $.extend($.fn.validatebox.defaults.rules, {
                phoneNumber: {
                    validator: function (value, param) {
                        var phone = /^1[3|5|7|8][0-9]{9}$/;

                        return phone.test(value);
                    },
                    message: "手机号错误"
                }

            });

            $("#save").click(function () {
                var v = $("#saveform").form("validate");

                if (v) {

                    $("#saveform").submit();
                }
            });


            $("#update").click(function () {
                var v = $("#updateForm").form("validate");
                if (v) {
                    $("#updateForm").submit();
                }
            })

        });


        function doDblClickRow(rowIndex, rowData) {


            var cid = rowData.cid;
            alert(cid);


            var url = "${pageContext.request.contextPath}/clazz_findAllClazz.action";

            $.post(url, "", function (data) {
                var classidTag = $("#classidupdate");
                classidTag.empty();
               // classidTag.append("<option value=''>--选择班级--</option>");
                for (var i = 0; i < data.length; i++) {
                    var clazzid = data[i].cid;

                    if (clazzid == cid) {
                        classidTag.append("<option  value='" + clazzid + "'>" + data[i].cname + "</option>")
                    }
                }


                for(var i = 0; i <data.length; i++){

                    var clazzid = data[i].cid;


                    if (clazzid != cid) {
                        classidTag.append("<option  value='" + clazzid + "'>" + data[i].cname + "</option>")
                    }

                }
            });


            $("#editStaffWindow").window("open");
            $("#updateForm").form("load", rowData);

        }


    </script>


</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="对学生进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存1</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form method="post" id="saveform" action="${pageContext.request.contextPath}/student_addStudent.action">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">添加学生</td>
                </tr>


                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="sname" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" validType="phoneNumber" name="sphone" class="easyui-validatebox"
                               required="true"/></td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="sage" class="easyui-validatebox" required="true"/></td>
                </tr>

                <tr>
                    <td>班级</td>

                    <td>
                        <select id="classid" name="classModel.cid">

                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>


    <div class="easyui-window" title="修改" id="editStaffWindow" collapsible="false" minimizable="false"
         maximizable="false" style="top:20px;left:200px">
        <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
            <div class="datagrid-toolbar">
                <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">修改</a>
            </div>
        </div>

        <div region="center" style="overflow:auto;padding:5px;" border="false">
            <form method="post" id="updateForm" action="${pageContext.request.contextPath}/student_update.action">
                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">修改</td>
                    </tr>

                    <tr>
                        <td>id</td>
                        <td><input type="text" name="sid" readonly="readonly" class="easyui-validatebox"
                                   required="true"/></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="sname" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td>手机</td>
                        <td><input type="text" validType="phoneNumber" name="sphone" class="easyui-validatebox"
                                   required="true"/></td>
                    </tr>
                    <tr>
                        <td>
                            <select id="classidupdate" name="classModel.cid">
                                <option>--选择班级--</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>


    <%-- <div class="easyui-window" title="高级查询" id="findwindow" collapsible="false" minimizable="false"
          maximizable="false" style="top:20px;left:200px">
         <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
             <div class="datagrid-toolbar">
                 <a id="find" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">修改</a>
             </div>
         </div>

         <div region="center" style="overflow:auto;padding:5px;" border="false">
             <form method="post" id="findForm" action="${pageContext.request.contextPath}/student_find.action">
                 <table class="table-edit" width="80%" align="center">
                     <tr class="title">
                         <td colspan="2">查询</td>
                     </tr>

                     <tr>
                         <td>id</td>
                         <td><input type="text" name="sid" readonly="readonly" class="easyui-validatebox"
                                    required="true"/></td>
                     </tr>
                     <tr>
                         <td>姓名</td>
                         <td><input type="text" name="sname" class="easyui-validatebox" required="true"/></td>
                     </tr>
                     <tr>
                         <td>手机</td>
                         <td><input type="text" validType="phoneNumber" name="sphone" class="easyui-validatebox"
                                    required="true"/></td>
                     </tr>
                     <tr>
                         <td>
                             <select id="classidfind" name="cid">
                                 <option>--选择班级--</option>
                             </select>
                         </td>
                     </tr>
                 </table>
             </form>
         </div>
     </div>--%>

</div>
</body>
</html>