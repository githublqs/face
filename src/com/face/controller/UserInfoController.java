package com.face.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.face.po.Userinfo;
import com.face.service.UsernifosService;

@Controller
@RequestMapping("/userinfos")
public class UserInfoController {
	@Autowired
	private UsernifosService usernifosService;
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
	@RequestMapping(value="/login",method = {RequestMethod.POST})
	public ModelAndView userLogin(/*@RequestParam(value="aa1", required=true) aa,*/ Userinfo userInfo/*,HttpServletRequest request*/){
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
	
	
}
