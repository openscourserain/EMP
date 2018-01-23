package com.seecen.sc1709.emp.test;

import com.seecen.sc1709.emp.dao.DeptDao;
import com.seecen.sc1709.emp.dao.Impl.DeptDaoImpl;
import com.seecen.sc1709.emp.entity.Dept;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by ASUS on 2018/1/17.
 */
public class DeptDaoTest {
    private DeptDao dao =new DeptDaoImpl();

    @Test
    public void addTest(){
        Dept d = new Dept();
        d.setDname("售后");
        d.setLoc("测试");
        dao.add(d);
        Assert.assertTrue(d.getDeptno()>0);
    }
    @Test
    public void deleteTest(){
        boolean result=dao.delete(5);
        Assert.assertTrue(result);
    }
    @Test
    public void updateTest(){
        Dept d=new Dept(4,"ceshi","测试地址");
        boolean result =dao.update(d);
        Assert.assertTrue(result);
    }
    @Test
    public void findDeptByIdTest(){
        Dept d = dao.findDeptById(4);
        Assert.assertNotNull(d);
    }
    @Test
    public void findDeptsTest(){
        List<Dept> list = dao.findDepts("总");
        Assert.assertTrue(list.size()>0);
    }
    @After
    public void destroy(){
        dao = null;
    }
}
