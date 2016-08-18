package com.face.po;

public class PageInfo {
	private int pageNo;
	private int pageSize;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageInfo(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	

}
