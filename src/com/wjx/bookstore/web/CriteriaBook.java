package com.wjx.bookstore.web;

/**
 * Created by admin on 12/17/2016.
 */
public class CriteriaBook {
    private float minPrice=0;
    private float maxPrice=Integer.MAX_VALUE;
    private int PageNo;

    public CriteriaBook(float minPrice, float maxPrice, int pageNo) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        PageNo = pageNo;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        PageNo = pageNo;
    }
}
