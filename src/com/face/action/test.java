 package com.face.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import com.sun.jna.Library;
import com.sun.jna.Native;

public class test {
	public interface FaceLibTools extends Library{
		FaceLibTools FaceLib =(FaceLibTools) Native.loadLibrary("FaceLib",FaceLibTools.class); 
	public int SetDetectFaceParam(int iMinFace, int iMaxFace, int iFaceQuality);
  public int GetFeatureLength();
	public int DetectDTFaceFromFile(String s,s_FREyeFace sf,int[] pNum);
	public int InitFaceSDK();
	public int ExtractFaceFeatureFromFile(String f,s_FREyeFace sg,byte [] b,int[] s);
	public int DetectDTFace(byte[] sh,int length,s_FREyeFace sf,int[] pNum);
	public int CompareFaceVsFace(byte [] a, int iLen1, byte [] b, int iLen2, float[] iSimilarity);
	}
		public static float compareFace(String path1,String path2){
	FaceLibTools faceLib=FaceLibTools.FaceLib;
	long sdds=faceLib.InitFaceSDK();
	faceLib.SetDetectFaceParam(10, 0, 0);
	int feature1[] =new int[1];
	feature1[0]=faceLib.GetFeatureLength();
	byte [] featureByte1=new byte[feature1[0]];
	int feature2[] =new int[1];
	feature2[0]=faceLib.GetFeatureLength();
	byte [] featureByte2=new byte[feature2[0]];
	int iNum1[] = new int[1];
	iNum1[0] = 1;
	int iNum2[] = new int[1];
	iNum2[0] = 1;
	if(path1!=""&&!"".equals(path1)){
		s_FREyeFace sd1=new s_FREyeFace();
		byte []s=getBytes(path1);
		int reval=faceLib.DetectDTFace(s, s.length, sd1, iNum1); //检测人脸
		if(iNum1[0]==0){
			System.out.println("iNum1û����");
			return 0.0f;
		}
		int dd=	faceLib.ExtractFaceFeatureFromFile(path1,sd1,featureByte1,feature1);//从人脸中提取特种
	}if(path2!=""&&!"".equals(path2)){
		s_FREyeFace sd2=new s_FREyeFace();
		byte []s=getBytes(path2);
		int returnval=faceLib.DetectDTFace(s, s.length, sd2, iNum2); 
		if(iNum2[0]==0){
			System.out.println("iNum2û����");
			return 0.0f;
		}
		int dd=	faceLib.ExtractFaceFeatureFromFile(path2,sd2,featureByte2,feature2);
	}
	float fSim[] = new float[1];
	fSim[0] = 0;
	int retur = faceLib.CompareFaceVsFace(featureByte1,feature1[0],featureByte2,feature2[0],fSim);
	return fSim[0];
	}
	public static void main(String[] args) {
	System.out.println("123");
	System.out.println(compareFace("C:\\Users\\luhui\\Desktop\\20160115131636824_3.jpg",
			"C:\\Users\\luhui\\Desktop\\20160115131636706_10.jpg"));	
	}
	  public static byte[] getBytes(String filePath){  
	        byte[] buffer = null;  
	        try {  
	            File file = new File(filePath);  
	            FileInputStream fis = new FileInputStream(file);  
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            byte[] b = new byte[1000];  
	            int n;  
	            while ((n = fis.read(b)) != -1) {  
	                bos.write(b, 0, n);  
	            }  
	            fis.close();  
	            bos.close();  
	            buffer = bos.toByteArray();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer;  
	    }
	  
}