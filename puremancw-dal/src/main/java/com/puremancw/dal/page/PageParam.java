/**
 * 
 */
package com.puremancw.dal.page;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页参数传递工具类
 * <p>Tilte: PageParam</p>
 * @author puremancw
 * @date 2016年11月18日 下午3:52:52
 
 */
public class PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3893645934298553917L;
	
	/**
	 * 默认为第一页
	 */
	public static final int DEFAULT_PAGE_NUM = 1;
	
	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_NUM_PER_PAGE = 20;
	
	/**
	 * 最大每页记录数
	 */
	public static final int MAX_PAGE_SIZE = 100;
	
	/**
	 * 当前页数
	 */
	public int pageNum = DEFAULT_PAGE_NUM;
	
	/**
	 * 每页记录数
	 */
	private int numPerPage;
	
	public PageParam(){}
	
	public PageParam(int pageNum, int numPerPage){
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage > 0 ? numPerPage : DEFAULT_NUM_PER_PAGE;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
