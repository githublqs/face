package com.face.protocol.entiy;

import java.io.Serializable;

public class FaceInputComputeFeatureImgUrl implements Serializable {
	private static final long serialVersionUID = -7774518324168256225L;
	private String id;
	private int serviceType;
	private String img;
	private String img_type;
	private Face_Rect face_rect;
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
	public Face_Rect getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(Face_Rect face_rect) {
		this.face_rect = face_rect;
	}
	public FaceInputComputeFeatureImgUrl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceInputComputeFeatureImgUrl(String id, int serviceType,
			String img, String img_type, Face_Rect face_rect) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.img = img;
		this.img_type = img_type;
		this.face_rect = face_rect;
	}
	

	
}
