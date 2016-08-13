package com.face.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.face.mapper.UserinfoMapper;
import com.face.po.Userinfo;
import com.face.po.UserinfoExample;
import com.face.service.UsernifosService;

public class UsernifosServiceImpl implements UsernifosService{
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Override
	public List<Userinfo> getAllUserInfos() {
		
		UserinfoExample example = new UserinfoExample();
		
		return userinfoMapper.selectByExample(example);
	}

}
