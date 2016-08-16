package com.face.protocol.entiy;

import java.io.Serializable;

public class FaceInputEntityImgUrl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6793065311436926033L;
	private String id;
	private int serviceType;
	private String imgurl;
	private String img_type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getImg_type() {
		return img_type;
	}
	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}
	public FaceInputEntityImgUrl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceInputEntityImgUrl(String id, int serviceType, String imgurl,
			String img_type) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.imgurl = imgurl;
		this.img_type = img_type;
	}
	

	
}
