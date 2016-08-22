package com.face.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.face.config.Constant;
import com.face.po.UserfaceImg;
import com.face.po.Userinfo;
import com.face.po.UserinfoCustom;
import com.face.service.UsernifosService;
import com.face.util.FileUtil;
import com.face.util.SingleEncrypUtil;
import com.face.util.WebLocalPathUtil;
import com.google.gson.Gson;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiOperation;
@Api(value = "/userinfos", description = "用户管理")
@Controller
@RequestMapping("/userinfos")
public class UserInfoController {
	@Autowired
	private UsernifosService usernifosService;
	@ApiIgnore
	@RequestMapping("/queryUserInfos")
	public ModelAndView queryUserInfos(){
		List<Userinfo> userInfoList=null;
		userInfoList=usernifosService.getAllUserInfos();
		ModelAndView modelAndView=new ModelAndView();
		/*userInfoList=new ArrayList<Userinfo>();
		Userinfo userInfo = new Userinfo();
		userInfo.setUsername("lqs");
		userInfo.setPassword("123456");
		userInfoList.add(userInfo);*/
		modelAndView.addObject("userinfos",userInfoList);
		modelAndView.setViewName("userinfos/userInfoList");
		return modelAndView;
	}
	@ApiIgnore
	//测试json
	@RequestMapping("/queryUserInfosOutJson/{un}/{pd}")
	public @ResponseBody  List<Userinfo> queryUserInfosOutJson(
			@PathVariable("un") String un,
			@PathVariable("pd") String pd,
			@RequestBody Userinfo userInfo){
		List<Userinfo> userInfoList=new ArrayList<Userinfo>();
		Userinfo userInfo2 = new Userinfo();
		userInfo2.setUsername(un);
		userInfo2.setPassword(pd);
		userInfoList.add(userInfo);
		userInfoList.add(userInfo2);
		return userInfoList;
	}
	@ApiIgnore
	//测试输出json
	@RequestMapping("/queryUserInfosOnlyOutJson/{un}/{pd}")
	public @ResponseBody  List<Userinfo> queryUserInfosOnlyOutJson(
			@PathVariable("un") String un,
			@PathVariable("pd") String pd,
			@RequestParam("aliasName")String name1,
			Userinfo userInfo){
		List<Userinfo> userInfoList=new ArrayList<Userinfo>();
		Userinfo userInfo2 = new Userinfo();
		userInfo2.setUsername(un);
		userInfo2.setPassword(pd);
		userInfoList.add(userInfo);
		userInfoList.add(userInfo2);
		return userInfoList;
	}
	//@RequestMapping(value={"/index", "/hello"}, method = {RequestMethod.GET})
	//以上表示的就是可以处理index.action和hello.action的路径。
	@ApiIgnore
	@RequestMapping(value="/userLogin2",method = {RequestMethod.POST})
	public ModelAndView userLogin(/*@RequestParam(value="aa1", required=true) aa,*/  Userinfo userInfo/*,HttpServletRequest request*/){
		List<Userinfo> userInfoList=new ArrayList<Userinfo>();
		ModelAndView modelAndView=new ModelAndView();
		if(userInfo!=null){
			if(userInfo.getUsername()!=null&&userInfo.getPassword()!=null){
				userInfoList.add(userInfo);
				
				modelAndView.addObject("userinfos",userInfoList);
				modelAndView.setViewName("userinfos/userInfoList");
				return modelAndView;
			}
		}
		
		
		return null;
		
		
		
		//特点：url路径不发生改变，但是request共享。
		//request.getRequestDispacher(“页面名称”).forward(request,response);
		 //return "forward:/hello"; 
		
		 //特点：url路径发生改变，但是request不共享。
		 // 重定向到 /hello 资源 
		//response.sendRedirect(“页面名称”);
	    //return "redirect:/hello";  
		
		
		//返回的是void类型。有的handler的处理是不需要有返回页面的情况，那这个时候就采取返回的类型为void。
		//适用情况：请求json或者xml的数据格式，或者请求的就是一串字符串数据。
		//String strInfo =     jsonArray.toString();  
        //System.out.println(strInfo);  
        //PrintWriter pw = response.getWriter();  
        //pw.print(strInfo);  
	}
	
	
	
	@ApiIgnore
	@RequestMapping(value="/userLogin",method = {RequestMethod.POST})
	public void userLoin(@RequestBody Userinfo userinfo,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Gson gson=new Gson();
		 if (userinfo!=null&&userinfo.getUsername()!=null) {
			 if(!userinfo.getUsername().equals(request.getSession().getAttribute(Constant.USERNAME_KEY))){
				  if(usernifosService.userRegisted(userinfo)){
					  //用户未登陆，且已经注册过，session保存此次登陆结果
					   request.getSession().setAttribute(Constant.USERNAME_KEY, userinfo.getUsername());
					   //存储id信息，如果存在
					  /* UserfaceImg userfaceImg=usernifosService.getUserfaceImg(userinfo);
					   if(userfaceImg!=null){
						   request.getSession().setAttribute(Constant.ID_KEY, userfaceImg.getId());
					   }*/
					   resultMap.put("username",userinfo.getUsername());
					   resultMap.put("loginSuccess",true);//这个接口怎么用示范一下
					   resultMap.put("errorcode",0);//这个接口怎么用示范一下
					  
				  }else{
					  //用户没有注册
					  resultMap.put("username",userinfo.getUsername());
						resultMap.put("loginSuccess",false);//这个接口怎么用示范一下
						resultMap.put("errorcode",2);//这个接口怎么用示范一下
				  }
				  
			 }else{
				 //用户已经登陆
				  resultMap.put("username",userinfo.getUsername());
					resultMap.put("loginSuccess",false);//这个接口怎么用示范一下
					resultMap.put("errorcode",1);//这个接口怎么用示范一下
			 }
				 
	       
	     }else{
	    	 //非法参数
	    	 resultMap.put("username","");
				resultMap.put("loginSuccess",false);//这个接口怎么用示范一下
				resultMap.put("errorcode",-9);//这个接口怎么用示范一下
	    	 
	     }
		 out.print(gson.toJson(resultMap));
	}
	@ApiOperation(notes = "userRegister", httpMethod = "POST", value = "用户注册")
	@RequestMapping(value="/userRegister",method = {RequestMethod.POST})
	public void userRegister(@RequestBody UserinfoCustom userinfoCustom,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//保存图片
		String path=WebLocalPathUtil.getRootPath(this);
		File parent=new File(path+File.separator+"uploadface"+File.separator);
		if(!parent.exists()){
			parent.mkdirs();	
		}
		 SingleEncrypUtil singleEncrypUtil=new SingleEncrypUtil();
		 String uploadImg= singleEncrypUtil.getHexString(singleEncrypUtil.getMD5Sole("加密字符串"))+".jpg";
		 File storeFile = new File(parent,uploadImg);
		 try {
			FileUtil.saveFile(userinfoCustom.getFaceimg(),storeFile );
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		userinfoCustom.setFaceimg(uploadImg);
		 
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Gson gson=new Gson();
		 try {  
			
			   if(!usernifosService.userRegisted(userinfoCustom)){
				   
				   if(usernifosService.saveUserInfo(userinfoCustom)){
					   resultMap.put("username",userinfoCustom.getUsername());
						resultMap.put("registerSuccess",true);//这个接口怎么用示范一下
						resultMap.put("errorcode",0);//这个接口怎么用示范一下
				   }else{
					   resultMap.put("username",userinfoCustom.getUsername());
					   resultMap.put("registerSuccess",false);//这个接口怎么用示范一下
		
					   resultMap.put("errorcode",2);//
				   }
			   }else{
				   resultMap.put("username",userinfoCustom.getUsername());
				   resultMap.put("registerSuccess",false);//这个接口怎么用示范一下
				   resultMap.put("errorcode",1);//
			   }
	    		out.print(gson.toJson(resultMap));
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	            resultMap.put("username","");
				resultMap.put("registerSuccess",false);//这个接口怎么用示范一下
				resultMap.put("errorcode",-9);//这个接口怎么用示范一下
	            out.print(gson.toJson(resultMap));
	        }  
		 
	}
	@ApiIgnore
	@RequestMapping(value="/checkRegisted",method = {RequestMethod.POST})
	public void checkRegisted(@RequestBody Userinfo userinfo,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Gson gson=new Gson();
		boolean registed=usernifosService.userRegisted(userinfo);
		 resultMap.put("username",userinfo.getUsername());
		 resultMap.put("checkRegisted",registed);//这个接口怎么用示范一下
		 resultMap.put("errorcode",0);//这个接口怎么用示范一下
         out.print(gson.toJson(resultMap));
	}
	@ApiIgnore
	@RequestMapping(value="/Login",method = {RequestMethod.GET})
	public String Login(){
		return "userinfos/Login";
	}
	@ApiIgnore
	@RequestMapping(value="/Register",method = {RequestMethod.GET})
	public String Register(){
		return "userinfos/Register";
	}
}
