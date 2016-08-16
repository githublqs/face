package com.face.protocol.entiy;

public class FaceInputComputeFeatureBase64 {
	private String id;
	private int serviceType;
	private String imgurl;
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
	public Face_Rect getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(Face_Rect face_rect) {
		this.face_rect = face_rect;
	}
	public FaceInputComputeFeatureBase64() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceInputComputeFeatureBase64(String id, int serviceType,
			String imgurl, String img_type, Face_Rect face_rect) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.imgurl = imgurl;
		this.img_type = img_type;
		this.face_rect = face_rect;
	}
	

	
}
