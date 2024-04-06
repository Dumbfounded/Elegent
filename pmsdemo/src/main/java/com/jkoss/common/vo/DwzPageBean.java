package com.jkoss.common.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 前端分页组件
 * 
 * @author chair
 *
 */
public class DwzPageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8470697978259453214L;

	// 指定的或是页面参数
	private Integer currentPage = 1; // 当前页
	private Integer numPerPage = 30; // 每页显示多少条

	// 查询数据库
	private Integer totalCount; // 总记录数
	private List<Object> recordList; // 本页的数据列表

	// 计算
	private Integer pageCount; // 总页数
	private Integer beginPageIndex; // 页码列表的开始索引（包含）
	private Integer endPageIndex; // 页码列表的结束索引（包含）

	private Map<String, Object> countResultMap = new HashMap(); // 当前分页条件下的统计结果

	public DwzPageBean() {

	}

	public Page toPage() {
		Page page = new Page();
		page.setCurrent(currentPage);
		page.setSize(numPerPage == null ? 15 : numPerPage);
		return page;
	}

	public DwzPageBean toDwzPageBean(Page page) {
		this.setRecordList(page.getRecords());
		this.setCurrentPage(page.getCurrent());
		this.setNumPerPage(page.getSize());
		this.setTotalCount(page.getTotal());
		this.setPageCount(page.getPages());
		return this;
	}

	public DwzPageBean toDwzPageBean(List recordList) {
		this.totalCount = recordList.size();
		this.recordList = this.getPagedList(this.currentPage, this.numPerPage, recordList);

		// 计算总页码
		pageCount = (totalCount + numPerPage - 1) / numPerPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}

		return this;
	}

	/**
	 * 得到分页后的数据
	 * 
	 * @param <T>
	 *
	 * @param pageNum
	 *            页码
	 * @return 分页后结果
	 */
	public List getPagedList(int nowPage, int pageSize, List data) {
		int fromIndex = (nowPage - 1) * pageSize;
		if (fromIndex >= data.size()) {
			return Collections.emptyList();// 空数组
		}
		if (fromIndex < 0) {
			return Collections.emptyList();// 空数组
		}
		int toIndex = nowPage * pageSize;
		if (toIndex >= data.size()) {
			toIndex = data.size();
		}
		return data.subList(fromIndex, toIndex);
	}

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param totalCount
	 * @param recordList
	 */
	public DwzPageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
		this.recordList = recordList;

		// 计算总页码
		pageCount = (totalCount + numPerPage - 1) / numPerPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	/**
	 * 只接受前5个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param totalCount
	 * @param recordList
	 */
	public DwzPageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList, Map<String, Object> countResultMap) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
		this.recordList = recordList;
		this.countResultMap = countResultMap;

		// 计算总页码
		pageCount = (totalCount + numPerPage - 1) / numPerPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	public void setPageNum(Integer pageNum) {
		this.currentPage = pageNum;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Object> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Object> recordList) {
		this.recordList = recordList;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(Integer beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public Integer getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(Integer endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public Map<String, Object> getCountResultMap() {
		return countResultMap;
	}

	public void setCountResultMap(Map<String, Object> countResultMap) {
		this.countResultMap = countResultMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
