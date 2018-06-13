package cn.wycclub.dto;

/**
 * 用户需要的数据分页
 *
 * @author WuYuchen
 * @create 2018-02-13 21:13
 **/

public class PageInput {
    //当前页
    protected int currentPage = 1;
    //页面数据总量
    protected int pageSize = 12;
    //数据表中的位置
    protected int startIndex;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "PageInput{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", startIndex=" + startIndex +
                '}';
    }
}
