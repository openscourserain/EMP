package com.seecen.sc1709.emp.servlet;

import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.service.EmpService;
import com.seecen.sc1709.emp.service.Impl.EmpServiceImpl;
import com.seecen.sc1709.emp.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddEmpController",urlPatterns = "/addEmp.html")
public class AddEmpController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Emp emp = new Emp();
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        emp.setEname(ename);
        String comm = request.getParameter("comm");
        if(comm!=null && comm.length()>0){
            emp.setComm(Double.valueOf(comm));
        }
        String sal = request.getParameter("sal");
        if(sal!=null && sal.length()>0){
            emp.setSal(Double.valueOf(sal));
        }
        String hiredate = request.getParameter("hiredate");
        if(hiredate!=null && hiredate.length()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date =sdf.parse(hiredate);
                emp.setHiredate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String job = request.getParameter("job");
        emp.setJob(job);
        EmpService empService = new EmpServiceImpl();
        //当主键不为空时就是更新操作，否则就是添加
        if(empno!=null && empno.length()>0){
            emp.setEmpno(Integer.valueOf(empno));
            empService.update(emp);
        }else {
            empService.add(emp);
        }
        //保存成功后跳转回列表页面
        response.sendRedirect("/emp.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno= request.getParameter("empno");
        EmpService empService = new EmpServiceImpl();
        if (empno != null && empno.length() > 0) {
            Emp emp = empService.findEmpById(Integer.valueOf(empno));
            request.setAttribute("emp", emp);
        }
        List<String> jobs = empService.getJobs();
        request.setAttribute("jobs",jobs);
        WebUtil.go(request, response,
                WebUtil.BASE_URL + "/Emp/addEmp.jsp");

    }
}
