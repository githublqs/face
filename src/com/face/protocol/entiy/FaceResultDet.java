package com.face.protocol.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class FaceResultDet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1860952238202721813L;
	private String id;
	private int face_num;
	private List<Face_Rect> face_rect;
	private int errorcode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFace_num() {
		return face_num;
	}
	public void setFace_num(int face_num) {
		this.face_num = face_num;
	}
	public List<Face_Rect> getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(List<Face_Rect> face_rect) {
		this.face_rect = face_rect;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FaceResultDet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceResultDet(String id, int face_num, List<Face_Rect> face_rect,
			int errorcode) {
		super();
		this.id = id;
		this.face_num = face_num;
		this.face_rect = face_rect;
		this.errorcode = errorcode;
	}

	

}
