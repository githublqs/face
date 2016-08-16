package com.face.protocol.entiy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/15.
 */
public class Face_Rect implements Serializable {
	private static final long serialVersionUID = 2414768970824316157L;

	public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private  int top;
    private  int left;

    private  int width;
    private  int height;

    @Override
    public String toString() {
        return "Face_Rect{" +
                "top=" + top +
                ", left=" + left +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public Face_Rect( int left, int top,int width, int height) {

        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }

    public Face_Rect() {
    }
}
