package cn.wycclub.dto;

import java.util.List;

/**
 * 业务层返回的分页对象
 *
 * @author WuYuchen
 * @create 2018-02-13 20:33
 **/

public class PageResult<T> {
    //记录分页内所有对象
    protected List<T> list;
    //数据库表中对应数据的总数
    protected int totalRecord;
    //按照数据总数和每页对象总数算出的总页数
    protected int totalPage;
    //当前页码
    protected int currentPage;
    //上一页
    protected int previousPage;
    //下一页
    protected int nextPage;
    //每页对象总数
    protected int pageSize;
    //分页条的页码数字显示
    protected int pageBar[];

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        int num = this.totalRecord / this.pageSize;
        this.totalPage = this.totalRecord % this.pageSize != 0 ? ++num : num;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousPage() {
        this.previousPage = this.currentPage != 1 ? this.currentPage - 1 : 1;
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        this.nextPage = this.currentPage != this.totalPage ? this.currentPage + 1 : this.totalPage;
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int[] getPageBar() {
        int startPage = 1;
        int endPage = 9;
        if (getTotalPage() <= 9) {
            this.pageBar = new int[totalPage];
            endPage = this.totalPage;
        } else {
            this.pageBar = new int[9];
            startPage = this.currentPage - 4;
            endPage = this.currentPage + 4;
            if (startPage <= 0) {
                startPage = 1;
                endPage = 9;
            }
            if (endPage > this.totalPage) {
                startPage = this.totalPage - 8;
                endPage = this.totalPage;
            }
        }

        for (int i = startPage, j = 0 ; j < pageBar.length && i <= endPage ; i++, j++) {
            pageBar[j] = i;
        }
        return pageBar;
    }

    public void setPageBar(int[] pageBar) {
        this.pageBar = pageBar;
    }
}
