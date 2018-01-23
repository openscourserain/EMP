package com.seecen.sc1709.emp.servlet;

import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.entity.Page;
import com.seecen.sc1709.emp.service.EmpService;
import com.seecen.sc1709.emp.service.Impl.EmpServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ASUS on 2018/1/18.
 */
public class EmpController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        EmpService empService = new EmpServiceImpl();
        Page page = new Page();
        if(req.getParameter("currentPage")!=null){
            page.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
        } else {
            page.setCurrentPage(1);
        }
        page.setPageSize(2);
        Emp emp =new Emp();
        String ename = req.getParameter("ename");
        String startTime = req.getParameter("startTime");
        String endTime =req.getParameter("endTime");
        if (ename != null) {
            emp.setEname(ename);
            req.setAttribute("ename",ename);
        } else {
            emp.setEname(null);
        }
        if (startTime != null && startTime.length() > 0) {
            emp.setStartTime(startTime);
        } else {
            emp.setStartTime(null);
        }
        if (endTime != null && endTime.length() > 0) {
            emp.setEndTime(endTime);
        } else {
            emp.setStartTime(null);
        }
        //
        //获取传递的参数
        page.setCondition(emp);
        page = empService.findByPage(page);
        req.setAttribute("page", page);
        //
        req.getRequestDispatcher("/WEB-INF/view/Emp/Emp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);

    }
}
