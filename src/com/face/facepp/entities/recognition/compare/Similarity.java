package com.face.facepp.entities.recognition.compare;
import com.google.gson.annotations.SerializedName;
public class Similarity {
   @SerializedName("component_similarity")
   private ComponentSimilarity componentSimilarity;
   @SerializedName("session_id")
   private String sessionId;
   private double similarity;


    public void setComponentSimilarity(ComponentSimilarity componentSimilarity) {
        this.componentSimilarity = componentSimilarity;
    }
    public ComponentSimilarity getComponentSimilarity() {
        return componentSimilarity;
    }
    

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getSessionId() {
        return sessionId;
    }
    

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    public double getSimilarity() {
        return similarity;
    }
    
}