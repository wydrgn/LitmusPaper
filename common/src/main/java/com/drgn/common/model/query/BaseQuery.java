package com.drgn.common.model.query;

/**
 * 基础查询条件
 *
 * @author wydrgn
 * @date 2021/7/24 12:38
 */
public class BaseQuery {
    public String id;
    public String pageIndex;
    public String pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
