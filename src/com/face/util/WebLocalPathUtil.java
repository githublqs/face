package com.face.util;

import java.io.File;

public class WebLocalPathUtil {
	public static String getRootPath(Object obj){
		String path=obj.getClass().getResource("/").toString();//file:/G:/apache-tomcat-7.0.67/webapps/SSM/WEB-INF/classes/
		path=path.substring(6,path.length()).split("/WEB-INF/classes/")[0];//G:/apache-tomcat-7.0.67/webapps/SSM
		path=path.replace("/", File.separator);//G:\apache-tomcat-7.0.67\webapps\SSM
		path=path.replace("\\", File.separator);//G:\apache-tomcat-7.0.67\webapps\SSM
		return path;
	}

}
