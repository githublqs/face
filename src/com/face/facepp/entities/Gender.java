package com.face.facepp.entities;


   
public class Gender {

   private double confidence;
   private String value;


    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    public double getConfidence() {
        return confidence;
    }
    

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    
}