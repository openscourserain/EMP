package com.seecen.sc1709.emp.service;

import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.entity.Page;

import java.util.List;

/**
 * Created by ASUS on 2018/1/18.
 */
public interface EmpService {
    public List<Emp> findEmp(Emp emp);

    public Page findByPage(Page page);

    public boolean delete(int empno);
    public void deletes(String[] empnos);
    public Emp findEmpById(int empno);  //根据雇员编号查找雇员
    public void add(Emp emp); //增加雇员
    public boolean update(Emp emp);  //更新雇员信息

    public   List<String> getJobs();
}
