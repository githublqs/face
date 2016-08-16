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

		
		
		/*for (Part part : request.getParts()) {
	        if (part != null && part.getName().startsWith("filename")) {
	            String filename = getFilename(part);
	            //因为上传框有多个，为了避免有的上传框没有选择文件导致出错，这里需要判断filename是否为null或为空
	            if (filename != null && !"".equals(filename))
	                part.write(filename);
	        }
	    }*/
		
	}
	
	/*private String getFilename(Part part) {
        if (part == null) {
            return null;
        }
        String fileName = part.getHeader("content-disposition");
        if (isBlank(fileName)) {
            return null;
        }
        return substringBetween(fileName, "filename=\"", "\"");
    }
	
	 public static boolean isBlank(String str) {
	        int strLen;
	        if (str == null || (strLen = str.length()) == 0)
	            return true;
	        for (int i = 0; i < strLen; i++) {
	            if (!Character.isWhitespace(str.charAt(i))) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public static String substringBetween(String str, String open, String close) {
	        if (str == null || open == null || close == null)
	            return null;
	        int start = str.indexOf(open);
	        if (start != -1) {
	            int end = str.indexOf(close, start + open.length());
	            if (end != -1)
	                return str.substring(start + open.length(), end);
	        }
	        return null;
	    }
*/
	/*@RequestMapping(value="/uploadFile/upload.ac", method = RequestMethod.POST)  
    public String uploadFile(HttpServletRequest request,  
            HttpServletResponse response) {  
        List<MultipartFile> multipartFiles = UploadHelper.getFileSet(request, 1024 * 20, null);  
        String path = "D:" + File.separator;  
        if (multipartFiles.size() == 0) {  
            // TODO 给出提示,不允许没选择文件点击上传  
  
        }  
        for (MultipartFile multipartFile : multipartFiles) {  
            try {  
                String filePath = UploadHelper.uploadFile(multipartFile, path);  
                System.out.println(filePath);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            // 拿到的imgPath就是图片的相对于contextPath的存储路径了  
        }  
        return null;  
    }  */
	
	
	
}
