package com.seecen.sc1709.emp.servlet;

import com.seecen.sc1709.emp.service.EmpService;
import com.seecen.sc1709.emp.service.Impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2018/1/22.
 */
@WebServlet(name = "DeleteEmpController", urlPatterns = "/deleteEmp.html")
public class DeleteEmpController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpService empService = new EmpServiceImpl();
        String empno = request.getParameter("empno");
        if (empno != null && empno.length() > 0) {
            empService.delete(Integer.valueOf(empno));
        }
        //批量删除
        String[] empnos = request.getParameterValues("empnos");
        empService.deletes(empnos);
        response.sendRedirect("/emp.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
