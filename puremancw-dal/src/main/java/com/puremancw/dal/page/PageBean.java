/**
 * 
 */
package com.puremancw.dal.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页组件
 * <p>Tilte: PageBean</p>
 * @author puremancw
 * @date 2016年11月18日 下午3:24:33
 
 */
public class PageBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766550381865173848L;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	
	/**
	 * 每页显示条数
	 */
	private int numPerPage;
	
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 本页数据列表
	 */
	private List<T> recordList = new ArrayList<T>(0);
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 页码列表的开始索引
	 */
	private int beginPageIndex;
	
	/**
	 * 页码列表的结束索引
	 */
	private int endPageIndex;
	
	/**
	 * 当前分页条件下的统计结果
	 */
	private Map<String, Object> countResultMap;
	
	public PageBean() {}
	
	/**
	 * 
	 * 计算总页数
	 * 
	 * @param totalCount		总计路数
	 * @param numPerPage		每页记录数
	 * @return	totalPage		总页数
	 */
	public static int countTotalPage(int totalCount, int numPerPage) {
		if(totalCount % numPerPage == 0) {
			//刚好整除
			return totalCount / numPerPage;
		} else {
			return totalCount / numPerPage + 1;
		}
	}
	
	/**
	 * 
	 * 校验当前页currentPage
	 * 1、先根据总记录数totalCount和每页记录数numPerPage，计算出总页数totalPage
	 * 2、判断页面提交过来的当前页currentPage是否大于总页数totalPage，大于则返回totalPage
	 * 3、判断currentPage是否小于1，小于则返回1
	 * 4、其它则直接返回currentPage
	 * 
	 * @param totalCount		总记录数
	 * @param numPerPage		每页条数
	 * @param currentPage		输入的当前页
	 * @return
	 */
	public static int checkCurrentPage(int totalCount, int numPerPage, int currentPage) {
		int totalPage = PageBean.countTotalPage(totalCount, numPerPage);
		if(currentPage > totalPage) {
			//如果页面提交过来的页数大于总页数，则将当前页设为总页数
			//此时要求totalPage要大于等于1
			if(totalPage < 1) {
				return 1;
			}
			return totalPage;
		} else if(currentPage < 1) {
			return 1;
		} else {
			return currentPage;
		}
	}
	
	/**
	 * 
	 * 校验页面输入的每页记录数numPerPage是否合法
	 * 1、当页面输入的每页记录数numPerPage大于允许的最大每页记录数MAX_PAGE_SIZE时，返回MAX_PAGE_SIZE，
	 * 2、如果numPerPage小于1，则返回默认的每页记录数DEFAULT_PAGE_SIZE
	 * 
	 * @param numPerPage		页面输入的记录数
	 * @return
	 */
	public static int checkNumPerPage(int numPerPage) {
		if(numPerPage > PageParam.MAX_PAGE_SIZE) {
			return PageParam.MAX_PAGE_SIZE;
		} else if(numPerPage < 1) {
			return PageParam.DEFAULT_NUM_PER_PAGE;
		} else {
			return numPerPage;
		}
	}
	
	/**
	 * 只接受前4个必要的属性，会自动计算出其他3个属性的值
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @param totalCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int numPerPage, int totalCount, List<T> recordList) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
		this.recordList = recordList;
		
		//计算总页码
		totalPage = (totalCount = numPerPage - 1) / numPerPage;
		
		//计算beginpageIndex 和 endPageIndex
		if(totalPage <= 10) {
			//如果总页数不多于10页，则全部显示
			beginPageIndex = 1;
			endPageIndex = totalPage;
		} else {
			//如果总页数多余10页，则显示当前页附近的共10个页码
			//当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			//当前页的页码不足4个时，则显示前10个页码
			if(beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			//当后面的页码不足5个时，则显示后10个页码
			if(endPageIndex > totalPage) {
				endPageIndex = totalPage;
				beginPageIndex = totalPage -10 + 1;
			}
		}
	}
	
	/**
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @param totalCount
	 * @param recordList
	 * @param countResultMap
	 */
	public PageBean(int currentPage, int numPerPage, int totalCount, List<T> recordList, Map<String, Object> countResultMap) {
		this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;
        this.countResultMap = countResultMap;
        
        // 计算总页码
        totalPage = (totalCount + numPerPage - 1) / numPerPage;
        
        // 计算 beginPageIndex 和 endPageIndex
        if (totalPage <= 10) {
            // 如果总页数不多于10页，则全部显示
            beginPageIndex = 1;
            endPageIndex = totalPage;
        } else {
            // 如果总页数多于10页，则显示当前页附近的共10个页码
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > totalPage) {
                endPageIndex = totalPage;
                beginPageIndex = totalPage - 10 + 1;
            }
        }
	}
	 
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public Map<String, Object> getCountResultMap() {
		return countResultMap;
	}

	public void setCountResultMap(Map<String, Object> countResultMap) {
		this.countResultMap = countResultMap;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
