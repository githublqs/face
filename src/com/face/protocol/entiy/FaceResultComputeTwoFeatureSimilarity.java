package com.face.protocol.entiy;
import java.io.Serializable;
/**
 * Created by Administrator on 2016/8/15.
 */
public class FaceResultComputeTwoFeatureSimilarity implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6219459326968247326L;
	private  String id;
    private  int errorcode;
    private  float sim;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public float getSim() {
		return sim;
	}
	public void setSim(float sim) {
		this.sim = sim;
	}
	public FaceResultComputeTwoFeatureSimilarity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaceResultComputeTwoFeatureSimilarity(String id, int errorcode,
			float sim) {
		super();
		this.id = id;
		this.errorcode = errorcode;
		this.sim = sim;
	}
	
	
	
    
    
}
