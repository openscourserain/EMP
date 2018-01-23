package com.seecen.sc1709.emp.service.Impl;

import com.seecen.sc1709.emp.dao.EmpDao;
import com.seecen.sc1709.emp.dao.Impl.EmpDaoImpl;
import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.entity.Page;
import com.seecen.sc1709.emp.service.EmpService;

import java.util.List;

/**
 * Created by ASUS on 2018/1/18.
 */
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();
    @Override
    public List<Emp> findEmp(Emp emp) {
        List<Emp> list =null;
//        if(emp!=null){
//            if(emp.getEname()!=null){
//                list= empDao.findEmps(emp.getEname());
//            }else{
//                list =empDao.findEmps("");
//            }
//        }
        //入职时间
        //预定时间
        if(emp.getHiredate()!=null){
            list =empDao.findEmps(emp.getEname(),emp.getHiredate());
        }else{
            list = empDao.findEmps(emp.getEname());
        }
        return list;
    }

    @Override
    public Page findByPage(Page page) {
        return empDao.findByPage(page);
    }

    @Override
    public boolean delete(int empno) {
        return empDao.delete(empno);
    }

    @Override
    public void deletes(String[] empnos) {
        if(empnos!=null){
            for(String empno:empnos){
                empDao.delete(Integer.valueOf(empno));
            }
        }
    }

    @Override
    public Emp findEmpById(int empno) {
        return empDao.findEmpById(Integer.valueOf(empno));
    }

    @Override
    public void add(Emp emp) {
        empDao.add(emp);
    }

    @Override
    public boolean update(Emp emp) {
        return empDao.update(emp);
    }

    @Override
    public List<String> getJobs() {
        return empDao.getJobs();
    }
}
