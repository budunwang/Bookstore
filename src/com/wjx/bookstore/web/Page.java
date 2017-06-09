package com.wjx.bookstore.web;

import java.util.List;

/**
 * Created by admin on 12/17/2016.
 */
public class Page<T> {
    //当前页
    private int pageNo;
    //当前页的list
    private List<T> list;
    //每页记录数
    private int pageSize=3;
    //总共记录数
    private long totalItemNumber;

    public Page(int pageNo) {
        super();
        setPageNo(pageNo);
    }

    public int getPageNo() {
        //检验页码是否越界
        if(pageNo<0) {
            pageNo=1;
        }

        if(pageNo>getTotalPageNumber()) {
            pageNo=getTotalPageNumber();
        }

        return pageNo;
    }
    //设置当前页
    public void setPageNo(int pageNo) {
        this.pageNo=pageNo;
    }

    public List<T> getList() {
        return list;
    }
    //设置当前list
    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }
    //设置当前记录数
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //获取总记录数
    public long getTotalItemNumber() {
        return totalItemNumber;
    }

    public void setTotalItemNumber(long totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }

    //获取总页数
    public int getTotalPageNumber() {
        int totalPageNumber=(int)totalItemNumber/pageSize;
        if(totalItemNumber%pageSize!=0) {
            totalPageNumber++;
        }
        return totalPageNumber;
    }
    //是否有下一页
    public boolean isHasNext() {
        if(getPageNo()<getTotalPageNumber()) {
            return true;
        }
        return false;
    }
    //是否有上一页
    public boolean isHasPrev() {
        if(getPageNo()>1) {
            return true;
        }
        return false;
    }
    //获得上一页
    public int getPrevPage() {
        if(isHasPrev()) {
            return getPageNo()-1;
        }
        return getPageNo();
    }
    //获得下一页
    public int getNextPage() {
        if(isHasNext()) {
            return getPageNo()+1;
        }
        return getPageNo();
    }
}
