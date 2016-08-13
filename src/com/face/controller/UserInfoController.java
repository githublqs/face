package com.face.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
