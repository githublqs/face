package com.face.protocol.entiy;

import java.io.Serializable;

public class FaceInputEntitySimilarityComputeTwoFeature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6841194958182326119L;
	private String id;
	private int serviceType;
	private String feature_src;
	private String feature_dst;
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
	public String getFeature_src() {
		return feature_src;
	}
	public void setFeature_src(String feature_src) {
		this.feature_src = feature_src;
	}
	public String getFeature_dst() {
		return feature_dst;
	}
	public void setFeature_dst(String feature_dst) {
		this.feature_dst = feature_dst;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FaceInputEntitySimilarityComputeTwoFeature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceInputEntitySimilarityComputeTwoFeature(String id,
			int serviceType, String feature_src, String feature_dst) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.feature_src = feature_src;
		this.feature_dst = feature_dst;
	}
	
	
}
