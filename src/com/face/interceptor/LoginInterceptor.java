package com.face.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	 public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
     
	    public void setAllowUrls(String[] allowUrls) {  
	        this.allowUrls = allowUrls;  
	    }  
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//获得请求的URL
		 String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
	        if(null != allowUrls && allowUrls.length>=1)  
	            for(String url : allowUrls) {    
	                if(requestUrl.contains(url)) {    
	                    return true;    
	                }    
	            }  
	          
		//公开地址
		HttpSession session =request.getSession();
		String userName=(String) session.getAttribute("username");
		if(userName!=null){
			//身份存在，放行
			return true;
		}
		//用户身份需要认证，跳转
		//request.getRequestDispatcher("/WEB-INF/jsp/userinfos/Login.jsp").forward(request, response);
		//return false;
		return true;
	}

}
