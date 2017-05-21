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
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">

        //页面加载完成，用ajax获取班级
        $(function () {
                    var url = "${pageContext.request.contextPath}/clazz_findAllClazz.action";

                    $.post(url, "", function (data) {
                        var classidTag = $("#classid");
                        classidTag.empty();
                        classidTag.append("<option>--选择班级--</option>");
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
            location.href = "findStudents.jsp";
        }


        function doRestore() {
            alert("...");
        }
        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler:findStudents
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
                pageList: [2, 5, 10],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/student_pageQuery.action",
                idField: 'id',
                columns: columns

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
        });



        //表单序列化为json
        $.fn.serializeJson = function () {
            var serializeObj = {};
            var array = this.serializeArray();
            $(array).each(function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
            return serializeObj;
        };


        function findStudents() {
            var param = $("#saveform").serializeJson();
            $("#grid").datagrid('load', param);
        }


    </script>
</head>

<br/>


<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">

    <div class="" title="查询" id="addStaffWindow" collapsible="false" minimizable="false"
         maximizable="false" style="top:20px;left:200px">
        <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
            <div class="datagrid-toolbar">
                <a id="gohome" icon="icon-save" href="${pageContext.request.contextPath}/home.jsp" class="easyui-linkbutton" plain="true">回到主页面</a>
            </div>
        </div>

        <div region="center" style="overflow:auto;padding:5px;" border="false">
            <form method="post" id="saveform" action="${pageContext.request.contextPath}/student_addStudent.action">
                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">查询学生</td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="sname" onkeydown="findStudents()"/></td>
                    </tr>
                    <tr>
                        <td>手机</td>
                        <td><input type="text" onkeydown="findStudents()" name="sphone"
                        /></td>
                    </tr>
                    <tr>
                        <td>年龄</td>
                        <td><input type="text" onkeydown="findStudents()" name="sage"/></td>
                    </tr>

                    <tr>
                        <td>班级</td>

                        <td>
                            <select id="classid" name="cid" onclick="findStudents()">

                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>


        <table id="grid"></table>

    </div>
</div>
</body>
</html>