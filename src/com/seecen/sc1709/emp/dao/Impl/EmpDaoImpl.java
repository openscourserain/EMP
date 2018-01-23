package com.seecen.sc1709.emp.dao.Impl;

import com.seecen.sc1709.emp.dao.EmpDao;
import com.seecen.sc1709.emp.entity.Emp;
import com.seecen.sc1709.emp.entity.Page;
import com.seecen.sc1709.emp.util.JDBCUtil;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by ASUS on 2018/1/17.
 */
public class EmpDaoImpl implements EmpDao {


    @Override
    public void add(Emp emp) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try{
            String[] columns ={"empno"};
            ps =conn.prepareStatement("insert into CCEMP values(CCEMP_SEQ.nextval,?,?,?,?,?)",columns);
            ps.setDouble(1,emp.getComm());
            ps.setString(2,emp.getEname());
            if(emp.getHiredate()!=null) {
                ps.setDate(3, new Date(emp.getHiredate().getTime()));
            }else{
                ps.setDate(3, null);
            }
            ps.setDouble(4,emp.getSal());
            ps.setString(5,emp.getJob());
            ps.executeUpdate();
            set=ps.getGeneratedKeys();
            if(set.next()){
                emp.setEmpno(set.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }

    }

    @Override
    public boolean delete(int empno) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps = conn.prepareStatement("DELETE FROM CCEMP WHERE EMPNO=?");
            ps.setInt(1,empno);
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
    public boolean update(Emp emp) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps =conn.prepareStatement("UPDATE CCEMP SET COMM=?,ENAME=?,HIREDATE=?,JOB=?,SAL=? WHERE EMPNO =?");
            ps.setDouble(1,emp.getComm());
            ps.setString(2,emp.getEname());
            if(emp.getHiredate()!=null) {
                ps.setDate(3, new Date(emp.getHiredate().getTime()));
            }else{
                ps.setDate(3,null);
            }
            ps.setString(4,emp.getJob());
            ps.setDouble(5,emp.getSal());
            ps.setInt(6,emp.getEmpno());
            int result= ps.executeUpdate();
            if(result>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Emp findEmpById(int empno) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        try {
            ps=conn.prepareStatement("SELECT * FROM CCEMP WHERE EMPNO=?");
            ps.setInt(1,empno);
            set=ps.executeQuery();
            if(set.next()){
                Emp e =new Emp();
                e.setEmpno(empno);
                e.setComm(set.getDouble("comm"));
                e.setEname(set.getString("ename"));
                e.setHiredate(set.getDate("hiredate"));
                e.setJob(set.getString("job"));
                e.setSal(set.getDouble("sal"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
        return null;
    }

    @Override
    public List<Emp> findEmps(String keyword) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        List<Emp>list = new ArrayList<Emp>();
        try {
            ps=conn.prepareStatement("SELECT * FROM CCEMP WHERE ENAME LIKE ?");
            ps.setString(1,"%"+keyword+"%");
            set=ps.executeQuery();
            while (set.next()){
                Emp e = new Emp();
                e.setEmpno(set.getInt("empno"));
                e.setComm(set.getDouble("comm"));
                e.setEname(set.getString("ename"));
                e.setHiredate(set.getDate("hiredate"));
                e.setJob(set.getString("job"));
                e.setSal(set.getDouble("sal"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
        return list;
    }

    @Override
    public List<Emp> findEmps(String keyword, java.util.Date date) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet set =null;
        List<Emp>list = new ArrayList<Emp>();
        try {
            StringBuffer sb = new StringBuffer("SELECT * FROM CCEMP WHERE ENAME LIKE ?");
            if(date!=null){
                sb.append("  and ( hiredate >= ? and hiredate <= ?+1 )");
            }
            ps=conn.prepareStatement(sb.toString());
            ps.setString(1,"%"+keyword+"%");
            if(date!=null){
                ps.setDate(2, new Date(date.getTime()));
                ps.setDate(3, new Date(date.getTime()));
            }
            set=ps.executeQuery();
            while (set.next()){
                Emp e = new Emp();
                e.setEmpno(set.getInt("empno"));
                e.setComm(set.getDouble("comm"));
                e.setEname(set.getString("ename"));
                e.setHiredate(set.getDate("hiredate"));
                e.setJob(set.getString("job"));
                e.setSal(set.getDouble("sal"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,set);
        }
        return list;
    }

    @Override
    public Page findByPage(Page page) {
        List<Emp> list = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CCEMP where 1=1";
        try {
            conn = JDBCUtil.getConnection();
            ArrayList<String> conditionList = new ArrayList<>();
            if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                sql += " and ename like ? ";
            }
            if (page.getCondition().getStartTime() != null && page.getCondition().getStartTime().length() > 0) {
                sql += "and  (hiredate >= to_date(?,'yyyy-mm-dd'))";
                conditionList.add(page.getCondition().getStartTime());
            }
            if (page.getCondition().getEndTime() != null && page.getCondition().getEndTime().length() > 0) {
                sql += "and (hiredate <= to_date(?,'yyyy-mm-dd')+1)";
                conditionList.add(page.getCondition().getEndTime());
            }
            sql = "select b.* from (select a.*, rownum rn from ( " + sql + "  ) a where rownum <= ?) b where b.rn > ? ";
            pst = conn.prepareStatement(sql);
            if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                pst.setString(1, "%" + page.getCondition().getEname() + "%");
            }
            for (int i = 0; i < conditionList.size(); i++) {
                if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                    pst.setString(i+2,conditionList.get(i));
                }else{
                    pst.setString(i+1,conditionList.get(i));
                }
            }
            int index= 0;
            if(page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0){
                index= conditionList.size()+1;
            } else{
                index= conditionList.size();
            }
            index++;
            pst.setInt(index, page.getEnd());
            index++;
            pst.setInt(index, page.getStart());
            list = new ArrayList<>();
            rs = pst.executeQuery();
            while (rs.next()) {
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setComm(rs.getInt("comm"));
                emp.setEname(rs.getString("ename"));
                emp.setSal(rs.getInt("sal"));
                emp.setJob(rs.getString("job"));
                if (rs.getDate("hiredate") != null) {
                    emp.setHiredate(
                            new java.util.Date(rs.getTimestamp("hiredate").getTime()));
                } else {
                    emp.setHiredate(null);
                }
                list.add(emp);
            }
            page.setList(list);
            sql = "select count(*) from CCEMP where 1 = 1";
            if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                sql += " and ename like ? ";
            }
            if (page.getCondition().getStartTime() != null && page.getCondition().getStartTime().length() > 0) {
                sql += "and  (hiredate >= to_date(?,'yyyy-mm-dd'))";
            }
            if (page.getCondition().getEndTime() != null && page.getCondition().getEndTime().length() > 0) {
                sql += "and (hiredate <= to_date(?,'yyyy-mm-dd')+1)";
            }
            pst = conn.prepareStatement(sql);
            if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                pst.setString(1, "%" + page.getCondition().getEname() + "%");
            }
            for (int i = 0; i < conditionList.size(); i++) {
                if (page.getCondition().getEname() != null && page.getCondition().getEname().length() > 0) {
                    pst.setString(i+2,conditionList.get(i));
                }else{
                    pst.setString(i+1,conditionList.get(i));
                }
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                page.setTotal(rs.getInt(1));
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pst, rs);
        }
        return null;
    }

    @Override
    public List<String> getJobs() {
        String sql= " select job from ccemp group by job ";
        Connection conn = JDBCUtil.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        List<String> jobs = new ArrayList<>();
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                jobs.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return jobs;
    }


}
