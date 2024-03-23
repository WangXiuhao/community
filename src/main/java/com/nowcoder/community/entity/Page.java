package com.nowcoder.community.entity;

/**
 * @author 王修豪
 * @version 1.0
 * 封装分页相关信息
 */
public class Page {
    // 当前的页码
    private int current = 1;//默认就是1
    // 每页显示的上限
    private int limit = 10;
    //数据的总数 用于计算总的页数
    private int rows;
    //查询路径 用于复用 分页链接 这个变量
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){//去掉负数的可能
            this.current = current;
        }

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >=1 && limit <= 100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getOffset(){
        //(current-1) * limit
        return (current-1)*limit;
    }

    /**
     * 用于获取总页数
     * @return
     */
    public int getTotal(){
        // row / limit 除不尽就+1
        if(rows % limit == 0){
            return rows/limit;
        }else {
            return rows / limit + 1;
        }
    }

    /**
     * 获取起始页码 下面需要显示的页数
     * @return
     */
    public int getFrom(){
        int from=current-2;
        return from<1?1:from;
    }

    public int getTo(){
        int to = current+2;
        int total = getTotal();
        return to>total?total:to;
    }


}
