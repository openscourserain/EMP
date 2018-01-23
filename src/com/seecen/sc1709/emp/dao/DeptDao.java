package com.seecen.sc1709.emp.dao;

import com.seecen.sc1709.emp.entity.Dept;

import java.util.List;

/**
 * Created by ASUS on 2018/1/17.
 */
public interface DeptDao {
    public void add(Dept dept); //增加部门
    public boolean delete(int deptno);    //根据部门编号编号删除部门
    public boolean update(Dept dept);  //更新部门信息
    public Dept findDeptById(int deptno);  //根据部门编号查找部门
    public List<Dept> findDepts(String keyword);    //根据关键字查找部门
}
