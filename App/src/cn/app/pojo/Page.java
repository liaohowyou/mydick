package cn.app.pojo;

import java.util.List;

public class Page {
	private Integer pageSize = 3; // 当前显示行数
	private Integer pageNo = 1; // 当前页数
	private Integer pagecount; // 页面总页数
	private Integer rows; // 数据库总页数
	private List list; // 存放片段
	public Integer getFirst;
	public Integer pageIndex;
	

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPagecount() {
		if(rows%pageSize == 0){
			pagecount = rows/pageSize;
		}else{
			pagecount = rows/pageSize+1;
		}
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getGetFirst() {
		return (pageNo-1)*pageSize;
	}

	public void setGetFirst(Integer getFirst) {
		this.getFirst = getFirst;
	}
	
	

}
