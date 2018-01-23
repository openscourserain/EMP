package com.seecen.sc1709.emp.entity;

import java.util.Date;

/**
 * Created by ASUS on 2018/1/17.
 */
public class Emp extends Condition {
    private int empno;  //雇员编号
    private double comm;
    private String ename;
    private Date hiredate;
    private String job;
    private double sal;

    public Emp() {
    }

    public Emp(int empno, double comm, String ename, Date hiredate, String job, double sal) {
        this.empno = empno;
        this.comm = comm;
        this.ename = ename;
        this.hiredate = hiredate;
        this.job = job;
        this.sal = sal;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
}
