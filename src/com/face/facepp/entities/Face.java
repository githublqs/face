package com.face.facepp.entities;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

   
public class Face {

   private Attribute attribute;
   @JsonProperty("face_id")
   private String face_id;
   private Position position;
   private String tag;
public Attribute getAttribute() {
	return attribute;
}
public void setAttribute(Attribute attribute) {
	this.attribute = attribute;
}
public String getFace_id() {
	return face_id;
}
public void setFace_id(String face_id) {
	this.face_id = face_id;
}
public Position getPosition() {
	return position;
}
public void setPosition(Position position) {
	this.position = position;
}
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}


  
    
}