package com.seecen.sc1709.emp.entity;

import java.util.List;

/**
 * Created by ASUS on 2018/1/21.
 */
public class Page {
    private int currentPage =1; //当前页，默认第一页

    private int pageSize =10;   //每页显示多少条，默认10条

    private int total;      //总记录数

    private List<Emp> list;

    private Emp condition;

    //总页数
    public int getPageTotal(){
        return (int) Math.ceil(total* 1.0 /pageSize);
    }

    //开始
    public int getStart(){
        return (currentPage-1)*pageSize;
    }
    //结束
    public int getEnd(){
        return currentPage*pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Emp> getList() {
        return list;
    }

    public void setList(List<Emp> list) {
        this.list = list;
    }

    public Emp getCondition() {
        return condition;
    }

    public void setCondition(Emp condition) {
        this.condition = condition;
    }
}
