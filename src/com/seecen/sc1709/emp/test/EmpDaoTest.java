package com.seecen.sc1709.emp.test;

import com.seecen.sc1709.emp.dao.EmpDao;
import com.seecen.sc1709.emp.dao.Impl.EmpDaoImpl;
import com.seecen.sc1709.emp.entity.Emp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by ASUS on 2018/1/17.
 */
public class EmpDaoTest {
    private EmpDao dao =new EmpDaoImpl();

    @Test
    public void addTest(){
        Emp e = new Emp();
        e.setComm(2000);
        e.setEname("John");
        dao.add(e);
        Assert.assertTrue(e.getEmpno()>0);
    }
    @Test
    public void deleteTest(){
        boolean result=dao.delete(7);
        Assert.assertTrue(result);
    }
    @Test
    public void updateTest(){
        Emp e=new Emp(3,5500,"Roy",null,"测试用",30000);
        boolean result =dao.update(e);
        Assert.assertTrue(result);
    }
    @Test
    public void findEmpByIdTest(){
        Emp e = dao.findEmpById(5);
        Assert.assertNotNull(e);
    }
    @Test
    public void findEmpsTest(){
        List<Emp> list = dao.findEmps("o");
        Assert.assertTrue(list.size()>0);
    }
    @After
    public void destroy(){
        dao = null;
    }
}
