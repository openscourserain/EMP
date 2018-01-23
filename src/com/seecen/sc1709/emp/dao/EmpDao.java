package com.seecen.sc1709.emp.dao;

import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.entity.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/1/17.
 */
public interface EmpDao {
    public void add(Emp emp); //增加雇员
    public boolean delete(int empno);    //根据雇员编号删除雇员
    public boolean update(Emp emp);  //更新雇员信息
    public Emp findEmpById(int empno);  //根据雇员编号查找雇员
    public List<Emp> findEmps(String keyword);    //根据关键字查找雇员
    public List<Emp> findEmps(String keyword, Date date);   //根据雇员名字和入职日期查询雇员

    public Page findByPage(Page page);

    List<String> getJobs();
}
