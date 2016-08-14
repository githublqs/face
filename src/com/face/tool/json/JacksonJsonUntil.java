package com.face.tool.json;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonJsonUntil {
	 public JsonGenerator jsonGenerator=null;  
	 public ObjectMapper objectMapper=null;  
	      
	    /** 
	     * 将对象转换为json字符串 
	     * @param response 向前台传递数据时的格式 
	     * @param obj 需要转换的对象（可以为list） 
	     * @throws Exception 异常处理 
	     */  
	    public void beanToJson(HttpServletResponse response,Object obj) throws Exception{  
	        response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码  
	        objectMapper =new ObjectMapper();  
	        try {  
	            String json=objectMapper.writeValueAsString(obj);//将对象转成json  
	              
	            PrintWriter out=response.getWriter();//打印到前台  
	            out.write(json);  
	        } catch (Exception e) {  
	            // TODO: handle exception  
	            e.printStackTrace();  
	        }  
	    }  
}
