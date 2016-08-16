package com.face.protocol.entiy;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Administrator on 2016/8/15.
 */
public class FaceResultComputeFeature4MaxFaceRect  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7486322308306217859L;
	private  String id;
    private int errorcode;
    private Face_RectAndFeature face_rect;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public Face_RectAndFeature getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(Face_RectAndFeature face_rect) {
		this.face_rect = face_rect;
	}
	public FaceResultComputeFeature4MaxFaceRect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceResultComputeFeature4MaxFaceRect(String id, int errorcode,
			Face_RectAndFeature face_rect) {
		super();
		this.id = id;
		this.errorcode = errorcode;
		this.face_rect = face_rect;
	}
	
}
