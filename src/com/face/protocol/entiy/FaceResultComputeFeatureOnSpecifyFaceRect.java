package com.face.protocol.entiy;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Administrator on 2016/8/15.
 */
//计算指定人脸区域的特征（URL图像）
public class FaceResultComputeFeatureOnSpecifyFaceRect  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8047708324072657734L;
	private  String id;
    private  String feature;
    private int errorcode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public FaceResultComputeFeatureOnSpecifyFaceRect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceResultComputeFeatureOnSpecifyFaceRect(String id, String feature,
			int errorcode) {
		super();
		this.id = id;
		this.feature = feature;
		this.errorcode = errorcode;
	}
	
    
    
}
