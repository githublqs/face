package com.face.protocol.entiy;

import java.io.Serializable;

public class FaceInputEntityBase64 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1843680267359040237L;
	private String id;
	private int serviceType;
	private String img;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg_type() {
		return img_type;
	}
	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}
	public FaceInputEntityBase64() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceInputEntityBase64(String id, int serviceType, String img,
			String img_type) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.img = img;
		this.img_type = img_type;
	}
	
	
}
