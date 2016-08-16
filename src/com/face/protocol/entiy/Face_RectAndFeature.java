package com.face.protocol.entiy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/15.
 */
public class Face_RectAndFeature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7482436173959739825L;
	private  int top;
    private  int left;
    private  int width;
    private  int height;
    private String feature;
    
    public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
    public Face_RectAndFeature(int top, int left, int width, int height,
			String feature) {
		super();
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
		this.feature = feature;
	}
	public Face_RectAndFeature() {
    }
}
