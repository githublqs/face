package com.face.po;

public class PageInfo {
	private int pageNo;
	private int pageSize;
	private boolean retListDirect;
	public boolean isRetListDirect() {
		return retListDirect;
	}
	public void setRetListDirect(boolean retListDirect) {
		this.retListDirect = retListDirect;
	}
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
	public PageInfo(int pageNo, int pageSize, boolean retListDirect) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.retListDirect = retListDirect;
	}
	
	

}
