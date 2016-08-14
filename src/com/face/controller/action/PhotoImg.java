package com.face.controller.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.face.util.SingleEncrypUtil;

@MultipartConfig()
@WebServlet(asyncSupported=true,urlPatterns={"/urlImg"})//设置该controlle支持异步请求，并设置访问路径为
public class PhotoImg  extends HttpServlet {
	/**
	 * 标示
	 */
	private static final long serialVersionUID = -2198646898342111940L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}
/**
 * 将上传的拍照图片写入
 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path=this.getClass().getResource("/").toString();//file:/G:/apache-tomcat-7.0.67/webapps/SSM/WEB-INF/classes/
		path=path.substring(6,path.length()).split("/WEB-INF/classes/")[0];//G:/apache-tomcat-7.0.67/webapps/SSM
		path=path.replace("/", File.separator);//G:\apache-tomcat-7.0.67\webapps\SSM
		path=path.replace("\\", File.separator);//G:\apache-tomcat-7.0.67\webapps\SSM
	    Part part=	 request.getPart("img");
	  //所用文件全部存为jpg格式
	    SingleEncrypUtil singleEncrypUtil=new SingleEncrypUtil();
	    String uploadImg= singleEncrypUtil.getHexString(singleEncrypUtil.getMD5Sole("加密字符串"))+".jpg";
		part.write(path+File.separator+"uploadface"+File.separator+uploadImg);//uploadImg 文件夹不存在会报错
		request.getRequestDispatcher("addUploadFace.action?uploadimg="+uploadImg).forward(request, response);//写入文件后写入到数据库中

	}
}
