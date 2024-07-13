package com.page.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author JSON
 * @date 2024/4/18
 * @description
 */
public class PageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    // 总记录数
    private Long total;

    // 总页数
    private Long pages;

    // 前页数据集合
    private List records;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }
}
