package com.hisign.sso.api.query;

import java.io.Serializable;

import com.hisign.sso.api.util.StringUtils;

/**
 * @Title:
 *   查询条件 带分页
 * @description:
 * 
 * @author lnj 
 */
public class QueryFilter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private QueryCondition queryCondition;
	
	/**
	 * 当前登录人
	 */
	private String userId;
	
	/**
	 * 页码
	 */
	private int pageNum = -1;
	
	/**
	 * 页行数
	 */
	private int pageSize = -1;
	
	/**
	 * 排序字段
	 */
	private String orderBy;
	
	/**
	 * 排序方式
	 */
	private String sort;
	
	/**
	 * 是否按照row去进行分页,默认:false
	 */
	private boolean pageByRow = false;
	
	/**
	 * startRow
	 */
	private int offset = -1; 
	
	/**
	 * 查询多少条记录
	 */
	private int limit = -1;

	public QueryCondition getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getOrderBy() {
		if(!StringUtils.isEmpty(orderBy)){
			String newOrderBy = StringUtils.underscoreName(orderBy);
			return newOrderBy;
		}
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isPageByRow() {
		return pageByRow;
	}

	public void setPageByRow(boolean pageByRow) {
		this.pageByRow = pageByRow;
	}
	
    /**
     * 计算起止行号
     */
    public void calculateOffsetAndLimit() {
    	if(!this.isPageByRow()){ //不是以row方式进行分页
            this.offset = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
            this.limit =  this.pageSize * (this.pageNum > 0 ? 1 : 0);
    	}
    }
}
