package com.face.controller;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import com.face.action.test;
import com.face.facepp.FacePPApi;
import com.face.facepp.entities.Detectresult;
import com.face.facepp.entities.Position;
import com.face.facepp.entities.recognition.compare.Similarity;
import com.face.po.UploadFace;
import com.face.po.UserfaceImg;
import com.face.po.Userinfo;
import com.face.po.UserinfoCustom;
import com.face.protocol.entiy.FaceCompareResult;
import com.face.protocol.entiy.Face_Rect;
import com.face.service.UploadFaceService;
import com.face.service.UserfaceImgService;
import com.face.service.UsernifosService;
import com.face.tool.json.JsonUtil;
import com.face.util.WebLocalPathUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mangofactory.swagger.annotations.ApiIgnore;
@ApiIgnore
@Controller
@RequestMapping(value="/")
public class UploadFaceAction {
@Autowired
private UploadFaceService uploadFaceService;
@Autowired
private UserfaceImgService userfaceImgService;

@Autowired
private UsernifosService usernifosService;
@RequestMapping(value="/addUploadFace")
/**
 * 添加到数据库中
 * @param uploadFace
 * @return
 */
public String addUploadFace(@ModelAttribute(value="uploadFace")UploadFace uploadFace,HttpServletRequest request,HttpServletResponse response) throws Exception
{
	response.setCharacterEncoding("utf-8");
	PrintWriter out= response.getWriter();
	//每次都是用默认用户 作用:屏蔽用户功能 适配原有的数据库表接口，保证现在的代码逻辑可以每次对比了账新上传的图片，并将图片相关信息写入数据库中
	Map<String, Object> resultMap=new HashMap<String, Object>();
	resultMap.put("username", "lqs");
	resultMap.put("matching", "yes");//这个接口怎么用示范一下
	resultMap.put("similarity", 1.0f);//这个接口怎么用示范一下
	resultMap.put("errorcode",0);//这个接口怎么用示范一下
	Gson gson=new Gson();
	uploadFace.setId(new Long(0));
	uploadFaceService.addUploadFace(uploadFace);
	Userinfo userinfo = new Userinfo();
	userinfo.setUsername( "lqs");
	userinfo.setPassword("123456");
	userinfo.setNickname("小明");
	userinfo.setGender("1");
	UserinfoCustom userinfoCustom=usernifosService.getUserinfoCustom(userinfo);
	if(request.getAttribute("img0")!=null){
		if(usernifosService.userRegisted(userinfo)){
			String img0=(String)request.getAttribute("img0");
			userfaceImgService.saveOrUpdateUserfaceImg(img0, userinfoCustom);;
			
		}else{
			//如果用户没有注册
			
			String img0=(String)request.getAttribute("img0");
			userinfoCustom=new UserinfoCustom(
					new Long(0), 
					img0,
					userinfo.getUsername(), 
					userinfo.getPassword(), 
					userinfo.getNickname(),
					userinfo.getGender());
			usernifosService.saveUserInfo(userinfoCustom);
		}
	}else{
		if(usernifosService.userRegisted(userinfo)){
			if(userinfoCustom.getId()==0){
				userfaceImgService.saveUserfaceImg(userinfo, uploadFace);
				out.print(gson.toJson(resultMap));
				return null;
			}
			
		}else{
			userinfoCustom=new UserinfoCustom(
					new Long(0), 
					uploadFace.getUploadimg(),
					userinfo.getUsername(), 
					userinfo.getPassword(), 
					userinfo.getNickname(),
					userinfo.getGender());
			usernifosService.saveUserInfo(userinfoCustom);
			out.print(gson.toJson(resultMap));
			return null;
		}
	}
	//如果用户不存在
	
	//对比方法---需要您来写---这里你来做算法的写入
	//String path=this.getClass().getResource("/").toString();
	//磁盘绝对路径
	//path=path.substring(6,path.length()).split("/WEB-INF/classes/")[0];
	String path=WebLocalPathUtil.getRootPath(this);
	//取得对比图片集和
	//List<UserfaceImg>userfaceImgs=userfaceImgService.getAllUserfaceImgs();
	UserfaceImg userfaceImg=userfaceImgService.getUserfaceImg(userinfo);
	
	//现在假设我们只对比一张图片
	//String urlComp1=path+File.separator+"uploadface"+File.separator+userfaceImgs.get(0).getFaceimg();//从数据库中取出的用户图片
	String urlComp1=path+File.separator+"uploadface"+File.separator+userfaceImg.getFaceimg();//从数据库中取出的用户图片
	
	//String urlComp2=path+File.separator+"uploadface"+File.separator+uploadFace;//这个是拍摄图片；
	String urlComp2=path+File.separator+"uploadface"+File.separator+uploadFace.getUploadimg();//这个是拍摄图片；
	//现在路径都写好了---等尼调用接口了
	//double c=test.compareFace(urlComp1, urlComp2);
	
	//System.out.println(c);
	float sim=0.0f;
	String matching="no";
	int errorcode=0;
	List<Face_Rect> face_Rects=new ArrayList<Face_Rect>();
	/*java.util.Random random=new java.util.Random();// 定义随机类
	int result=random.nextInt(10);//[0,10)
	sim=result*1.0f/10;*/
	Detectresult detectresult0 = FacePPApi.faceDet(request, response, (String)request.getAttribute("img0"));
	
	if(detectresult0.getFace().size()==1){
		Detectresult detectresult1 = FacePPApi.faceDet(request, response, uploadFace.getUploadimg());
		if(detectresult1.getFace().size()==1){
			String face_id1 = detectresult0.getFace().get(0).getFace_id();
			String face_id2 = detectresult1.getFace().get(0).getFace_id();
			try {
				Similarity similarity=FacePPApi.recognitionCompare(face_id1,face_id2);
				sim=(float) similarity.getSimilarity();
				
				face_Rects.add(FacePPApi.newFaceRect(detectresult0.getImg_width(), detectresult0.getImgHeight(),
						detectresult0.getFace().get(0).getPosition()));
				
				face_Rects.add(FacePPApi.newFaceRect(detectresult1.getImg_width(), detectresult1.getImgHeight(),
						detectresult1.getFace().get(0).getPosition()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			boolean flag=sim>0.6;//test.compareFace(urlComp1, urlComp2)>0.6;
			if (flag) {//如果登录成功
				//接口需要的参数时 那我写个方法从这里取出参数 en
			/*	resultMap.put("matching", "yes");//这个接口怎么用示范一下
				resultMap.put("similarity", sim);//这个接口怎么用示范一下
				resultMap.put("errorcode",0);//这个接口怎么用示范一下
			out.print(gson.toJson(resultMap));*/	
				matching="yes";
			}else {//如果登录不成功
				/*resultMap.put("matching", "no");//这个接口怎么用示范一下
				resultMap.put("similarity", sim);//这个接口怎么用示范一下
				resultMap.put("errorcode",0);//这个接口怎么用示范一下
				out.print(gson.toJson(resultMap));*/
				matching="no";
				
			}
			
		}
	}
	FaceCompareResult faceCompareResult=new FaceCompareResult( matching,  sim,  errorcode,
			 face_Rects);
	gson.toJson(faceCompareResult, FaceCompareResult.class, out);
	return null;
}

}
