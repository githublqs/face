package com.face.facepp.entities;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

   
public class Position {

   private Center center;
   @JsonProperty("eye_left")
   private EyeLeft eye_left;
   @JsonProperty("eye_right")
   private EyeRight eye_right;
   private double height;
   @JsonProperty("mouth_left")
   private MouthLeft mouth_left;
   @JsonProperty("mouth_right")
   private MouthRight mouth_right;
   private Nose nose;
   private double width;
public Center getCenter() {
	return center;
}
public void setCenter(Center center) {
	this.center = center;
}
public EyeLeft getEye_left() {
	return eye_left;
}
public void setEye_left(EyeLeft eye_left) {
	this.eye_left = eye_left;
}
public EyeRight getEye_right() {
	return eye_right;
}
public void setEye_right(EyeRight eye_right) {
	this.eye_right = eye_right;
}
public double getHeight() {
	return height;
}
public void setHeight(double height) {
	this.height = height;
}
public MouthLeft getMouth_left() {
	return mouth_left;
}
public void setMouth_left(MouthLeft mouth_left) {
	this.mouth_left = mouth_left;
}
public MouthRight getMouth_right() {
	return mouth_right;
}
public void setMouth_right(MouthRight mouth_right) {
	this.mouth_right = mouth_right;
}
public Nose getNose() {
	return nose;
}
public void setNose(Nose nose) {
	this.nose = nose;
}
public double getWidth() {
	return width;
}
public void setWidth(double width) {
	this.width = width;
}


   
    
}