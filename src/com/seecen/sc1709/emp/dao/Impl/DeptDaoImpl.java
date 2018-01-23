package com.seecen.sc1709.emp.dao.Impl;

import com.seecen.sc1709.emp.dao.DeptDao;
import com.seecen.sc1709.emp.entity.Dept;
import com.seecen.sc1709.emp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/1/17.
 */
public class DeptDaoImpl implements DeptDao {
    @Override
    public void add(Dept dept) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try{
            String[] columns ={"deptno"};
            ps =conn.prepareStatement("insert into CCDEPT values(CCDEPT_SEQ.nextval,?,?)",columns);
            ps.setString(1,dept.getDname());
            ps.setString(2,dept.getLoc());
            ps.executeUpdate();
            set=ps.getGeneratedKeys();
            if(set.next()){
                dept.setDeptno(set.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
    }

    @Override
    public boolean delete(int deptno) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps = conn.prepareStatement("DELETE FROM CCDEPT WHERE DEPTNO=?");
            ps.setInt(1,deptno);
            int result=ps.executeUpdate();
            if(result>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
        return false;
    }

    @Override
    public boolean update(Dept dept) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps=conn.prepareStatement("UPDATE CCDEPT SET DNAME=?,LOC=? WHERE DEPTNO=?");
            ps.setString(1,dept.getDname());
            ps.setString(2,dept.getLoc());
            ps.setInt(3,dept.getDeptno());
            int result= ps.executeUpdate();
            if(result>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
        return false;
    }

    @Override
    public Dept findDeptById(int deptno) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps=conn.prepareStatement("SELECT * FROM CCDEPT WHERE DEPTNO=?");
            ps.setInt(1,deptno);
            set=ps.executeQuery();
            if(set.next()){
                Dept d = new Dept();
                d.setDeptno(deptno);
                d.setDname(set.getString("dname"));
                d.setLoc(set.getString("loc"));
                return d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dept> findDepts(String keyword) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        List<Dept>list = new ArrayList<Dept>();
        try {
            ps=conn.prepareStatement("SELECT * FROM CCDEPT WHERE DNAME LIKE ?");
            ps.setString(1,"%"+keyword+"%");
            set=ps.executeQuery();
            while (set.next()){
                Dept d =new Dept();
                d.setDeptno(set.getInt("deptno"));
                d.setDname(set.getString("dname"));
                d.setLoc(set.getString("loc"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
