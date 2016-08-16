package com.face.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.face.protocol.entiy.FaceResultComputeTwoFeatureSimilarity;
import com.face.protocol.entiy.FaceResultDet;
import com.face.protocol.entiy.FaceInputComputeFeatureBase64;
import com.face.protocol.entiy.FaceInputComputeFeatureImgUrl;
import com.face.protocol.entiy.FaceInputEntityBase64;
import com.face.protocol.entiy.FaceInputEntityImgUrl;
import com.face.protocol.entiy.FaceInputEntitySimilarityComputeTwoFeature;
import com.face.protocol.entiy.FaceResultComputeFeature4MaxFaceRect;
import com.face.protocol.entiy.FaceResultComputeFeature4Multiple;
import com.face.protocol.entiy.FaceResultComputeFeatureOnSpecifyFaceRect;
import com.face.protocol.entiy.Face_Rect;
import com.face.protocol.entiy.Face_RectAndFeature;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
@Api(value = "/faceRec", description = "人脸识别接口")
@Controller
@RequestMapping("/faceRec")
public class FaceController {
	///....人脸检测
	@ApiOperation(notes = "faceDetBase64", httpMethod = "POST", value = "人脸检测(发送图像)")
	@RequestMapping(value="/faceDetBase64" ,method = RequestMethod.POST)
	/*ID = iD;
	this.face_num = face_num;
	this.face_rect = face_rect;
	this.errorcode = errorcode;*/
	public @ResponseBody FaceResultDet faceDetBase64(@RequestBody FaceInputEntityBase64 fieBase64){
		try {
			if(fieBase64!=null){
				//请求 url指向的图片拿到图像数据
				List<Face_Rect> face_rect=new ArrayList<Face_Rect>();
				face_rect.add(new Face_Rect(12, 34, 120, 120));
				face_rect.add(new Face_Rect(12, 34, 120, 120));
				FaceResultDet result=new FaceResultDet(fieBase64.getId(),2,face_rect,0);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultDet("000000000",0,null,-9);
	}
	@ApiOperation(notes = "faceDetImageUrl", httpMethod = "POST", value = "人脸检测(发送图像url)")
	@RequestMapping(value="/faceDetImageUrl" ,method = RequestMethod.POST)
	public @ResponseBody FaceResultDet faceDetImageUrl(@RequestBody FaceInputEntityImgUrl fieImgUrl){
		try {
			if(fieImgUrl!=null){
				//请求 url指向的图片拿到图像数据
				List<Face_Rect> face_rect=new ArrayList<Face_Rect>();
				face_rect.add(new Face_Rect(12, 34, 120, 120));
				face_rect.add(new Face_Rect(12, 34, 120, 120));
				FaceResultDet result=new FaceResultDet(fieImgUrl.getId(),2,face_rect,0);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultDet("000000000",0,null,-9);
	}
	
	///...........特征计算
	//计算指定人脸区域的特征（图像流）
	@ApiOperation(notes = "/cumputeFeatureOnSpecifyFaceRectDetBase64", httpMethod = "POST", value = "计算指定人脸区域的特征（图像流）")
	@RequestMapping(value="/cumputeFeatureOnSpecifyFaceRectDetBase64" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeFeatureOnSpecifyFaceRect cumputeFeatureOnSpecifyFaceRectDetBase64(@RequestBody()FaceInputComputeFeatureBase64 feature){
		try {
			if(feature!=null){
				//请求 url指向的图片拿到图像数据
				FaceResultComputeFeatureOnSpecifyFaceRect result=new FaceResultComputeFeatureOnSpecifyFaceRect(feature.getId(),"long feature string",0);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeatureOnSpecifyFaceRect("000000000",null,-9);
	}
	@ApiOperation(notes = "/cumputeFeatureOnSpecifyFaceRectDetWithImageUrl", httpMethod = "POST", value = "计算指定人脸区域的特征（URL图像）")
	@RequestMapping(value="/cumputeFeatureOnSpecifyFaceRectDetWithImageUrl" ,method = RequestMethod.POST)
	//计算指定人脸区域的特征（URL图像）
	public @ResponseBody() FaceResultComputeFeatureOnSpecifyFaceRect cumputeFeatureOnSpecifyFaceRectDetWithImageUrl(@RequestBody()FaceInputComputeFeatureImgUrl feature ){
		try {
			if(feature!=null){
				//请求 url指向的图片拿到图像数据
				FaceResultComputeFeatureOnSpecifyFaceRect result=new FaceResultComputeFeatureOnSpecifyFaceRect(feature.getId(),"long feature string",0);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeatureOnSpecifyFaceRect("000000000",null,-9);
	}
	
	
	
	//计算最大人脸区域的特征（图像流）
	@ApiOperation(notes = "/cumputeFeature4MaxFaceRectDetBase64", httpMethod = "POST", value = "计算最大人脸区域的特征（图像流）")
	@RequestMapping(value="/cumputeFeature4MaxFaceRectDetBase64" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeFeature4MaxFaceRect cumputeFeature4MaxFaceRectDetBase64(@RequestBody()FaceInputEntityBase64 fieBase64){
		try {
			if(fieBase64!=null){
				//请求 url指向的图片拿到图像数据
				Face_RectAndFeature face_rect=new Face_RectAndFeature(12, 34, 120, 120, "long feature string");
				FaceResultComputeFeature4MaxFaceRect result=new FaceResultComputeFeature4MaxFaceRect(fieBase64.getId(),0,face_rect);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeature4MaxFaceRect("000000000",-9,null);
	}
	/*ID = iD;
	this.errorcode = errorcode;
	this.face_rect = face_rect;*/
	
	//计算最大人脸区域的特征（URL图像）
	@ApiOperation(notes = "/cumputeFeature4MaxFaceRectDetWithImageUrl", httpMethod = "POST", value = "计算最大人脸区域的特征（URL图像）")
	@RequestMapping(value="/cumputeFeature4MaxFaceRectDetWithImageUrl" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeFeature4MaxFaceRect cumputeFeature4MaxFaceRectDetWithImageUrl(@RequestBody()FaceInputEntityImgUrl fieImgUrl){
		try {
			if(fieImgUrl!=null){
				//请求 url指向的图片拿到图像数据
				Face_RectAndFeature face_rect=new Face_RectAndFeature(12, 34, 120, 120, "long feature string");
				FaceResultComputeFeature4MaxFaceRect result=new FaceResultComputeFeature4MaxFaceRect(fieImgUrl.getId(),0,face_rect);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeature4MaxFaceRect("000000000",-9,null);
	}
	
	
	//计算多人脸的特征（图像流）
	@ApiOperation(notes = "/cumputeMultipleFeatureBase64", httpMethod = "POST", value = "计算多人脸的特征（图像流）")
	@RequestMapping(value="/cumputeMultipleFeatureBase64" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeFeature4Multiple cumputeMultipleFeatureBase64(@RequestBody()FaceInputEntityBase64 fieBase64 ){
		try {
			if(fieBase64!=null){
				//从 Base64 字符串中拿到图像数据
				List<Face_RectAndFeature> face_rect=new ArrayList<Face_RectAndFeature>();
				face_rect.add(new Face_RectAndFeature(12, 34, 120, 120, "long feature string"));
				face_rect.add(new Face_RectAndFeature(12, 34, 120, 120, "long feature string"));
				FaceResultComputeFeature4Multiple result=new FaceResultComputeFeature4Multiple(fieBase64.getId(),2,0,face_rect);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeature4Multiple("000000000",0,-9,null);
	}
	
	//计算多人脸的特征（URL图像）
	@ApiOperation(notes = "/cumputeMultipleFeatureImageUrl", httpMethod = "POST", value = "计算多人脸的特征（URL图像）")
	@RequestMapping(value="/cumputeMultipleFeatureImageUrl" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeFeature4Multiple cumputeMultipleFeatureImageUrl(@RequestBody()FaceInputEntityImgUrl fieImgUrl ){
		try {
			if(fieImgUrl!=null){
				//请求 url指向的图片拿到图像数据
				List<Face_RectAndFeature> face_rect=new ArrayList<Face_RectAndFeature>();
				face_rect.add(new Face_RectAndFeature(12, 34, 120, 120, "long feature string"));
				face_rect.add(new Face_RectAndFeature(12, 34, 120, 120, "long feature string"));
				FaceResultComputeFeature4Multiple result=new FaceResultComputeFeature4Multiple(fieImgUrl.getId(),2,0,face_rect);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeFeature4Multiple("000000000",0,-9,null);
	}
	
	
	
	///...特征计算相似度
	@ApiOperation(notes = "/computeTwoFeature", httpMethod = "POST", value = "特征计算相似度")
	@RequestMapping(value="/computeTwoFeature" ,method = RequestMethod.POST)
	public @ResponseBody() FaceResultComputeTwoFeatureSimilarity computeTwoFeature(@RequestBody()FaceInputEntitySimilarityComputeTwoFeature twoFeature ){
		try {
			if(twoFeature!=null){
				FaceResultComputeTwoFeatureSimilarity result=new FaceResultComputeTwoFeatureSimilarity(twoFeature.getId(), 0,0.9f);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FaceResultComputeTwoFeatureSimilarity("000000000", -9,0.0f);
	}
}
