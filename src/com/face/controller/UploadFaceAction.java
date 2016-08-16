package com.face.controller;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import com.face.po.UploadFace;
import com.google.gson.Gson;
import com.mangofactory.swagger.annotations.ApiIgnore;
/*import com.face.entity.UploadFace;
import com.face.entity.UserfaceImg;
import com.face.service.UploadFaceService;
import com.face.service.UserfaceImgService;
import com.google.gson.Gson;*/
@ApiIgnore
@Controller
@RequestMapping(value="/")
public class UploadFaceAction {
/*@Autowired
private UploadFaceService uploadFaceService;
@Autowired
private UserfaceImgService userfaceImgService;*/
@RequestMapping(value="/addUploadFace")

/**
 * 添加到数据库中
 * @param uploadFace
 * @return
 */
public String addUploadFace(@ModelAttribute(value="uploadFace")UploadFace uploadFace,HttpServletResponse response) throws Exception
{
	response.setCharacterEncoding("utf-8");
	/*uploadFaceService.addUploadFace(uploadFace);
	PrintWriter out= response.getWriter();
	Map<String, String> resultMap=new HashMap<String, String>();
	resultMap.put("username", "小明");
	resultMap.put("matching", "yes");//这个接口怎么用示范一下
	Gson gson=new Gson();
	//对比方法---需要您来写---这里你来做算法的写入
	String path=this.getClass().getResource("/").toString();
	//磁盘绝对路径
	path=path.substring(6,path.length()).split("/WEB-INF/classes/")[0];
	//取得对比图片集和
	List<UserfaceImg>userfaceImgs=userfaceImgService.getAllUserfaceImgs();
	//现在假设我们只对比一张图片
	String urlComp1=path+File.separator+"uploadface"+File.separator+userfaceImgs.get(0).getFaceimg();//从数据库中取出的用户图片
	String urlComp2=path+File.separator+"uploadface"+File.separator+uploadFace;//这个是拍摄图片；
	//现在路径都写好了---等尼调用接口了
	//double c=test.compareFace(urlComp1, urlComp2);
	
	//System.out.println(c);
	boolean flag=true;//test.compareFace(urlComp1, urlComp2)>0.6;
	if (flag) {//如果登录成功
		
		//接口需要的参数时 那我写个方法从这里取出参数 en
		
		out.print(gson.toJson(resultMap));
	}else {//如果登录不成功
		resultMap.put("matching", "no");
		out.print(gson.toJson(resultMap));
	}*/
	
	Map<String, String> resultMap=new HashMap<String, String>();
	resultMap.put("username", "小明");
	resultMap.put("matching", "yes");//这个接口怎么用示范一下
	Gson gson=new Gson();
	PrintWriter out= response.getWriter();
	out.print(gson.toJson(resultMap));
	
	return null;
}
}
