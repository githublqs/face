package com.face.util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class FileUtil {
	public static void saveFile(String base64Str,File storeFile ) throws FileNotFoundException{
		  InputStream instream = new ByteArrayInputStream(base64Str.getBytes());
		FileOutputStream output = new FileOutputStream(storeFile);
		try {
			byte b[] = new byte[1024];
			int j = 0;
			while ((j = instream.read(b)) != -1) {
				output.write(b, 0, j);
			}
			output.flush();
			output.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Closing the input stream will trigger connection release
			try {
				instream.close();
			} catch (Exception ignore) {
			}
		}
		
	}

	public static File newUploadFile(Object obj){
		String path=WebLocalPathUtil.getRootPath(obj);
		File parent=new File(path+File.separator+"uploadface"+File.separator);
		if(!parent.exists()){
			parent.mkdirs();	
		}
		 SingleEncrypUtil singleEncrypUtil=new SingleEncrypUtil();
		String uploadImg= singleEncrypUtil.getHexString(singleEncrypUtil.getMD5Sole("加密字符串"))+".jpg";
		return new File(parent,uploadImg);
	}
	public static File newUploadFileThumbnail(Object obj,File uploadFile){
		String path=WebLocalPathUtil.getRootPath(obj);
		String thumbHZ="thumb";
		int hzPosition=uploadFile.getName().lastIndexOf(".");
		String fileNameNoHZ=uploadFile.getName().substring(0, hzPosition);
		String fileNameHZ=uploadFile.getName().substring(hzPosition);
		File parent=new File(path+File.separator+"uploadface"+File.separator+thumbHZ+File.separator);
		if(!parent.exists())
		{
			parent.mkdirs();	
		}
		return new File(parent,fileNameNoHZ+"_"+thumbHZ+fileNameHZ);
	}
	public static File getUploadFile(Object obj,String uploadFileName){
		String path=WebLocalPathUtil.getRootPath(obj);
		File parent=new File(path+File.separator+"uploadface"+File.separator);
		if(!parent.exists()){
			parent.mkdirs();	
		}
		return new File(parent,uploadFileName);
	}
	/**
	  * 将文件转成base64 字符串
	  * @param path文件路径
	  * @return  * 
	  * @throws Exception
	  */

	 public static String encodeBase64File(String path) throws Exception {
	  File file = new File(path);;
	  FileInputStream inputFile = new FileInputStream(file);
	  byte[] buffer = new byte[(int) file.length()];
	  inputFile.read(buffer);
	  inputFile.close();
	  return new BASE64Encoder().encode(buffer);

	 }

	 /**
	  * 将base64字符解码保存文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void decoderBase64File(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();

	 }

	 /**
	  * 将base64字符保存文本文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void toFile(String base64Code, String targetPath)
	   throws Exception {

	  byte[] buffer = base64Code.getBytes();
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();
	 }


}
