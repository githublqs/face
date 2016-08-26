package com.face.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.face.config.Constant;
import com.face.facepp.FacePPApi;
import com.face.facepp.entities.Center;
import com.face.facepp.entities.Detectresult;
import com.face.facepp.entities.Face;
import com.face.facepp.entities.Position;
import com.face.po.PageInfo;
import com.face.po.UploadFace;
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
import com.face.service.UploadFaceService;
import com.face.service.UserfaceImgService;
import com.face.service.UsernifosService;
import com.face.util.FileUtil;
import com.face.util.HttpRequest;
import com.face.util.ImageResizer;
import com.face.util.WebLocalPathUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
@Api(value = "/faceRec", description = "人脸识别接口")
@Controller
@RequestMapping("/faceRec")
public class FaceController {
	@Autowired
	private UploadFaceService uploadFaceService;
	///....人脸检测
	@ApiOperation(notes = "faceDetBase64", httpMethod = "POST", value = "人脸检测(发送图像)")
	@RequestMapping(value="/faceDetBase64" ,method = RequestMethod.POST)
	/*ID = iD;
	this.face_num = face_num;
	this.face_rect = face_rect;
	this.errorcode = errorcode;*/
	public @ResponseBody FaceResultDet faceDetBase64(@RequestBody FaceInputEntityBase64 fieBase64,HttpServletRequest request,HttpServletResponse response){
		try {
			if(fieBase64!=null){
				//将图片存入本地
				File storeFile=FileUtil.newUploadFile(this);
				FileUtil.decoderBase64File(fieBase64.getImg(),storeFile.getAbsolutePath() );
				UploadFace uploadFace=new UploadFace();
				uploadFace.setId(new Long(0));
				uploadFace.setUploadimg(storeFile.getName());
				//将路径存入 数据库
				uploadFaceService.addUploadFace(uploadFace);
				//调用face++接口
				Detectresult detectresult = FacePPApi.faceDet(request, response, storeFile.getName());
				//请求 url指向的图片拿到图像数据
				List<Face_Rect> face_rect=new ArrayList<Face_Rect>();
				StringBuilder sb=new StringBuilder();
				//从接口中解析出人脸区域
				if(detectresult!=null){
					int img_width = detectresult.getImg_width();//1请求图片的宽度
					int img_height=detectresult.getImgHeight();//2请求图片的高度
					List<Face> listFace = detectresult.getFace();
					
					int i=0;
					for (Face face : listFace) {
						Position position = face.getPosition();
						//根据上面5个值计算人脸相对于图片左上角的矩形区域坐标
						Face_Rect faceRect = FacePPApi.newFaceRect(img_width,img_height,position);
						face_rect.add(faceRect);
						if(i>0){
							sb.append(",");
						}
						sb.append(face.getFace_id());
						i++;
						
					}
				}
				FaceResultDet result=new FaceResultDet(sb.toString(),face_rect.size(),face_rect,0);
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

	/*@ApiOperation(notes = "/uploadFaceList", httpMethod = "POST", value = "返回所有已经上传的图片集合")
	public void uploadFaceList(HttpServletRequest request,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<UploadFace> list=uploadFaceService.getAllUploadFaceList();
		JsonArray jsonArray = new JsonArray();
		 for (UploadFace uploadFace : list) {
			 JsonObject jo=new JsonObject();
			 File thumbnail = generateThumbnail(uploadFace.getUploadimg(),false);
			 jo.addProperty("thumbnailImgFileName",thumbnail==null?"":thumbnail.getName());
			 jo.addProperty("imgFileiName",uploadFace.getUploadimg());//这个接口怎么用示范一下
			 jo.addProperty("date",uploadFace.getUploaddate().toGMTString());//这个接口怎么用示范一下
			 jsonArray.add(jo);
		}
         out.print(jsonArray.toString());
	}*/
	
	/*
	 * 分页查询 todo 的
	 */
	@ApiOperation(notes = "/uploadFaceList", httpMethod = "POST", value = "分页返回已经上传的图片集合")
	@RequestMapping(value="/uploadFaceList" ,method = RequestMethod.POST)
	public void uploadFaceListByPage(
			@RequestBody(required=true) PageInfo pageInfo,
			HttpServletRequest request,HttpServletResponse response){
		boolean isRetListDirect=pageInfo.isRetListDirect();
		if(pageInfo.getPageNo()<1){
			pageInfo.setPageNo(1);
		}
		if(pageInfo.getPageSize()<1||pageInfo.getPageSize()>100){
			pageInfo.setPageSize(10);
		}
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<UploadFace> list=uploadFaceService.getAllUploadFaceListByPage(pageInfo.getPageNo(), pageInfo.getPageSize());
		JsonArray jsonArray = new JsonArray();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String rootPath = WebLocalPathUtil.getRootPath(this);
		String thumbnailDirPath = rootPath+File.separator
				+"uploadface"+File.separator
				+"thumb"+File.separator;	
		File thumbnailDir=new File(thumbnailDirPath);
		if(!thumbnailDir.exists()){
			thumbnailDir.mkdirs();
		}
		 for (UploadFace uploadFace : list) {
			 JsonObject jo=new JsonObject();
			 File thumbnail = generateThumbnail(rootPath,thumbnailDir,uploadFace.getUploadimg(),false);
			 jo.addProperty("thumbnailImgFileName",thumbnail==null?"":thumbnail.getName());
			 jo.addProperty("id",uploadFace.getId());
			 jo.addProperty("imgFileiName",uploadFace.getUploadimg());//这个接口怎么用示范一下
			 String dateString = formatter.format(uploadFace.getUploaddate());
			 jo.addProperty("date",dateString);//这个接口怎么用示范一下
			 jsonArray.add(jo);
		}
		 if(isRetListDirect){
			 out.print(jsonArray.toString());
			 return;
		 }
	      //得到数据的条数
	      int rowCount =uploadFaceService.getAllUploadFaceCount();
	      //通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算
	      if(rowCount%pageInfo.getPageSize()!=0)
	      {
	        rowCount = rowCount / pageInfo.getPageSize() + 1;
	      }
	      else
	      {
	        rowCount = rowCount / pageInfo.getPageSize();
	      }
	      
	      //转成Json格式
	      String strResult = "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+pageInfo.getPageNo()+
	    		  ",\"list\":" +
	    		  jsonArray.toString()
	    		  + "}";
	     
	      out.print(strResult);
         //out.print(jsonArray.toString());
	}
	
	@ApiOperation(notes = "/getUploadFaceSize", httpMethod = "GET", value = "获得已上传的图片数目")
	@RequestMapping(value="/getUploadFaceSize" ,method = RequestMethod.GET)
	public void getUploadFaceSize(HttpServletRequest request,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 JsonObject jo=new JsonObject();
		 jo.addProperty("count",uploadFaceService.getAllUploadFaceCount());
         out.print(jo.toString());
	}
	/**
	 * 
	 * @param srcFileName
	 * @param coverOld
	 * @return 
	 * @throws IOException 
	 */
	private File generateThumbnail(String rootPath,File thumbnailDir,String srcFileName,boolean coverOld){
		try {
			String thumbnailSubffixWithDot=srcFileName .substring(srcFileName.lastIndexOf("."));
			String thumbnailNameNoSubffix=srcFileName .substring(0,srcFileName.lastIndexOf("."))
					+"_thumb";
			String thumbnailName=thumbnailNameNoSubffix+thumbnailSubffixWithDot;
			File thumbnail= new File(thumbnailDir,thumbnailName);
			boolean thumbnailExist =thumbnail.exists();
			File srcFile = new File(rootPath+File.separator
					+"uploadface"+File.separator+srcFileName);
			
			if(thumbnailExist){
				if(!coverOld){
					return thumbnail;
				}else{
					ImageResizer.zoomImageScale(srcFile, thumbnail.getAbsolutePath(), 100);
				}
			}else{
				ImageResizer.zoomImageScale(srcFile, thumbnail.getAbsolutePath(), 100);
			}
			
			return thumbnail;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/deletUploadFace" ,method = RequestMethod.POST)
	public void deletUploadFace(@RequestBody UploadFace uploadFace,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JsonObject jo=new JsonObject();
		try{
			
		 uploadFaceService.deleteUploadFace(uploadFace);
		 jo.addProperty("deleteSuccess",true);
         out.print(jo.toString());
		}catch(Exception e){
			e.printStackTrace();
			jo.addProperty("deleteSuccess",true);
	         out.print(jo.toString());
		}
	}
	@ApiIgnore
	@RequestMapping(value="/uploadFaceManage" ,method = RequestMethod.GET)
	public String uploadFaceManage() throws Exception{
			//throw new Exception("异常测试");	
		return "faceRec/uploadFaceManage";
		
	}
	@RequestMapping(value="/faceppTest" ,method = RequestMethod.GET)
	public void faceppTest(HttpServletRequest request,HttpServletResponse response){
		String picName="head_120.png";
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+path+"/"; //+":"+request.getServerPort()
		//测试 人脸检测与分析//detection/detect
		//String httpUrl=Constant.generateFaceInterfaceUrl(Constant.FACEPP_detection_detect_format, basePath+"images/"+picName);
		/*public static final String FACEPP_API_Key="DR1RqAkKmld582oEnD-e2eKUL-9WD7kh";
		public static final String FACEPP_API_Secret="a620ebdf215750bf9bfe532fe91d53e0";*/
		//httpUrl=http://dojochinaextjs.imwork.net/SSM/images/head_120.png
		String httpUrl="http://apicn.faceplusplus.com/v2/detection/detect";
		//http://dojochinaextjs.imwork.net/SSM/images/head_120.png
		String url=basePath+"images/"+picName;
		String param="api_key=a620ebdf215750bf9bfe532fe91d53e0&api_secret=qNt3vYHpNK9t0SIQUQzyfZquRTeiDAs2&url="+url+"&attribute=glass,pose,gender,age,race,smiling";
		String ret= HttpRequest.sendGet(httpUrl, param);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.write(ret.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
