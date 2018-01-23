<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/23
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    label{
        display: block;
    }
</style>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            if(!$("input[name='ename']").val()){
                alert("员工姓名不能为空");
                return;
            }
            if(!$("input[name='hiredate']").val()){
                alert("入职日期不能为空");
                return;
            }
            if(!$("input[name='sal']").val()){
                alert("员工薪水不能为空");
                return;
            }
            $("#myForm").submit();
        })
    })
</script>
<head>
    <title>Title</title>
</head>
<body>
<form id="myForm" action="/addEmp.html" method="post">
    <input type="hidden" name="empno" value="${emp.empno}">
    <label>员工名字:<input type="text" name="ename" value="${emp.ename}"></label>
    <label>员工奖金:<input type="text" name="comm" value="${emp.comm}"></label>
    <label>入职日期:<input type="date" name="hiredate" value="${emp.hiredate}"></label>
    员工职位:
    <select>
        <c:forEach items="${jobs}" var="job">
            <option value="${job}">${job}</option>
        </c:forEach>
    </select>
    <label>员工薪水:<input type="text" name="sal" value="${emp.sal}"></label>
    <input id="saveBtn" type="button" value="保存">
</form>
</body>
</html>
