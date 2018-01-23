<%@ page import="com.seecen.sc1709.emp.dao.EmpDao" %>
<%@ page import="com.seecen.sc1709.emp.entity.Emp" %>
<%@ page import="com.seecen.sc1709.emp.dao.Impl.EmpDaoImpl" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/18
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
    <style>
        .condition{
            width: 80%;
            margin: 0 auto;
            height: 50px;
            line-height: 50px;
        }
        .tb{
            width: 80%;
            margin: 0 auto;
            border: 1px solid #666666;
        }
        .tb td{
            text-align: center;
        }
        .pagebar{
            margin: 0 auto;
            width: 80%;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script>
        function go(index) {
            $("#currentPage").val(index);
            $("#myForm").submit();
        }
        $(function () {
            $("#searchBtn").click(function () {
                $("#currentPage").val(1);
                $("#myForm").submit();
            })

            $(".allSelect").click(function () {
                //prop修改有布尔特性的值，如：checked disabled readOnly，jquery方法套路，当设置两个参数就是修改值，当设置一个参数就是读取属性值
                $(".sel").prop("checked", $(this).prop("checked"));
            })
            //同步全选功能
            $(".sel").click(function () {
                var selNum = $(".sel").length;
                var selectedNum = $(".sel:checked").length;
                $(".allSelect").prop("checked", selectedNum == selNum);
            })

            $("#delAllBtn").click(function () {
                if ($(".sel:checked").length == 0) {
                    alert("请选择要删除的数据！");
                    return;
                }
                if (confirm("您确定要删除这些数据吗？")) {
                    $("#delForm").submit();
                }
            })
            //添加功能
            $("#addBtn").click(function () {
                location.href = "/addEmp.html";
            })

        })
    </script>
</head>
<body>
<div class="condition">
    <form id="myForm" action="/emp.html" method="post">
        <input id="currentPage" type="hidden" name="currentPage" value="${page.currentPage}">
        <label>员工名<input type="text" name="ename" value="${ename}"></label>
        <label>入职时间<input type="date" value="${page.condition.startTime}" name="startTime">
            <input type="date" value="${page.condition.endTime}" name="endTime">
        </label>
        <input id="searchBtn" type="button" value="搜索">
        <input id="addBtn" type="button" value="添加">
    </form>
</div>
<form id="delForm" action="/deleteEmp.html" method="post">
<table class="tb">
    <thead>
    <tr>
        <th><label><input type="checkbox" class="allSelect">全选</label></th>
        <th>员工编号</th>
        <th>员工奖金</th>
        <th>员工名字</th>
        <th>入职日期</th>
        <th>员工职位</th>
        <th>员工薪水</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="item" varStatus="vs">
        <tr>
            <td><label><input type="checkbox" name="empnos" class="sel" value="${item.empno}">${vs.count}</label></td>
            <td>${item.empno}</td>
            <td>${item.comm}</td>
            <td>${item.ename}</td>
            <td><f:formatDate value="${item.hiredate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
            <td>${item.job}</td>
            <td>${item.sal}</td>
            <td><a href="/addEmp.html?empno=${item.empno}">修改</a> <a href="/deleteEmp.html?empno=${item.empno}" onclick="return confirm('您确定要删除么？')">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<div class="pagebar">
    <input id="delAllBtn" type="button" value="批量删除">
    <a href="javascript:go(1)">首页</a>
    <c:if test="${page.currentPage>1}">
        <a href="javascript:go(${page.currentPage-1})">上一页</a>
    </c:if>
    <c:if test="${page.currentPage<=1}">
        <a href="javascript:void(0)" style="color: #cccccc; text-decoration: none;">上一页</a>
    </c:if>
    [${page.currentPage}/${page.pageTotal}]
    <c:if test="${page.currentPage<page.pageTotal}">
        <a href="javascript:go(${page.currentPage+1})">下一页</a>
    </c:if>
    <c:if test="${page.currentPage==page.pageTotal}">
        <a href="javascript:void(0)"  style="color: #cccccc; text-decoration: none;">下一页</a>
    </c:if>
    <a href="javascript:go(${page.pageTotal})">末页</a>
    <input type="text" style="width: 30px"><input type="button" value="跳转">
</div>
</body>
</html>
