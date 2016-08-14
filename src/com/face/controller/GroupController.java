package com.face.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;







import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.face.po.UamGroup;
import com.face.tool.json.JacksonJsonUntil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
/**
 * Created by yuananyun on 2015/11/23.
 */
@Controller
@RequestMapping(value = "/group", produces = {"application/json;charset=UTF-8"})
@Api(value = "/group", description = "群组的相关操作")
public class GroupController {
    @RequestMapping(value = "/addGroup", method = RequestMethod.PUT)
   
    @ApiOperation(notes = "addGroup", httpMethod = "POST", value = "添加一个新的群组")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public UamGroup addGroup(@ApiParam(required = true, value = "group data") @RequestBody UamGroup group) {
        return group;
    }
    
    @RequestMapping(value = "/getAccessibleGroups", method = RequestMethod.GET)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "返回json列表")})
    @ApiOperation(notes = "getAccessibleGroups", httpMethod = "GET", value = "获取我可以访问的群组的列表")
    public @ResponseBody /*String*/ /*ModelAndView*/ /*Map*//*void*/List<UamGroup> getAccessibleGroups(Model model,HttpServletRequest request,  
            HttpServletResponse response) throws Exception {
        UamGroup group1 = new UamGroup();
        group1.setGroupId("1");
        group1.setName("testGroup1");

        UamGroup group2 = new UamGroup();
        group2.setGroupId("2");
        group2.setName("testGroup2");

        List<UamGroup> groupList = new LinkedList<UamGroup>();
        groupList.add(group1);
        groupList.add(group2);
       //Forwarding to resource [/WEB-INF/jsp/group/getAccessibleGroups.jsp] in InternalResourceView 'group/getAccessibleGroups'
        return groupList;
        
        
        
        
       /* model.addAttribute("groupList", groupList);//如果返回类型为void，此时相当于return null;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("summary","组列表");
        //return null;//可见返回值类型为非空时，如果返回 空 则  服务器相应内容也为空
        modelAndView.setViewName(null);//不设置或者设置null 则 /WEB-INF/jsp/group/getAccessibleGroups.jsp 
        return modelAndView;//modelAndView 默认值
*/        
        
        
        // 实例Jackson对象  
        /*JacksonJsonUntil jacksonJsonUntil = new JacksonJsonUntil();  
        jacksonJsonUntil.beanToJson(response, groupList);*/
        //void直接返回 json 与@ResponseBody +public  List<UamGroup> 返回json 效果一致
       
        
       /* Map<String,List<UamGroup>> map=new HashMap<String, List<UamGroup>>();  
      	map.put("groupList", groupList);  
      	return map;*/ 
      	//返回 Map相当于返回 ModelAndView 
      	/*modelAndView.addObject("userinfos",userInfoList);
		modelAndView.setViewName("userinfos/userInfoList");*/
    }
}