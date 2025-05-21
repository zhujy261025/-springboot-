package com.gec.springbootcourse.req;

import javax.validation.constraints.NotNull;

public class PageReq {
    @NotNull(message = "【页码】不能为空")
    private int pageNum;

    @NotNull(message = "【每页条数】不能为空")
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
