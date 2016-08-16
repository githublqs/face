package com.face.protocol.entiy;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Administrator on 2016/8/15.
 */
public class FaceResultComputeFeature4Multiple  implements Serializable {
    /**
	 * 
	 */
	private  String id;
    private int face_num;
    private int errorcode;
    private List<Face_RectAndFeature> face_rect;
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
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public List<Face_RectAndFeature> getFace_rect() {
		return face_rect;
	}
	public void setFace_rect(List<Face_RectAndFeature> face_rect) {
		this.face_rect = face_rect;
	}
	public FaceResultComputeFeature4Multiple() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceResultComputeFeature4Multiple(String id, int face_num,
			int errorcode, List<Face_RectAndFeature> face_rect) {
		super();
		this.id = id;
		this.face_num = face_num;
		this.errorcode = errorcode;
		this.face_rect = face_rect;
	}
    
    
    
}
