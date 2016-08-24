package com.face.facepp.entities;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
   
public class Detectresult{

	public List<Face> getFace() {
		return face;
	}
	public void setFace(List<Face> face) {
		this.face = face;
	}
	public int getImg_height() {
		return img_height;
	}
	public void setImg_height(int img_height) {
		this.img_height = img_height;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public int getImg_width() {
		return img_width;
	}
	public void setImg_width(int img_width) {
		this.img_width = img_width;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private List<Face> face;
   @JsonProperty("img_height")
   private int img_height;
   @JsonProperty("img_id")
   private String img_id;
   @JsonProperty("img_width")
   private int img_width;
   @JsonProperty("session_id")
   private String session_id;
   private String url;


    
}