package com.face.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.face.mapper.UserfaceImgMapper;
import com.face.mapper.UserinfoMapper;
import com.face.po.UserfaceImg;
import com.face.po.UserfaceImgExample;
import com.face.po.Userinfo;
import com.face.po.UserinfoCustom;
import com.face.po.UserinfoExample;
import com.face.service.UsernifosService;
import com.sun.jna.platform.win32.Netapi32Util.UserInfo;

public class UsernifosServiceImpl implements UsernifosService{
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private UserfaceImgMapper userfaceImgMapper;
	@Override
	public List<Userinfo> getAllUserInfos() {
		
		UserinfoExample example = new UserinfoExample();
		
		return userinfoMapper.selectByExample(example);
	}
	@Override
	public boolean userRegisted(Userinfo userinfo) {
		
		return userinfoMapper.selectByPrimaryKey(userinfo.getUsername())!=null;
	}
	@Override
	public boolean userRegisted(UserinfoCustom userinfoCustom) {
		return userinfoMapper.selectByPrimaryKey(userinfoCustom.getUsername())!=null;
	}
	@Override
	public boolean saveUserInfo(UserinfoCustom userinfoCustom) {
		try {
			Userinfo userInfo=new Userinfo();
			userInfo.setGender(userinfoCustom.getGender());
			userInfo.setNickname(userinfoCustom.getNickname());
			userInfo.setPassword(userinfoCustom.getPassword());
			userInfo.setUsername(userinfoCustom.getUsername());
			userinfoMapper.insert(userInfo);
			UserfaceImg userfaceImg=new UserfaceImg();
			userfaceImg.setFaceimg(userinfoCustom.getFaceimg());
			userfaceImg.setUsername(userinfoCustom.getUsername());
			userfaceImgMapper.insert(userfaceImg);
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public UserfaceImg getUserfaceImg(Userinfo userinfo) {
		UserfaceImgExample example = new UserfaceImgExample();
		example.createCriteria().andUsernameEqualTo(userinfo.getUsername());
		List<UserfaceImg> list = userfaceImgMapper.selectByExample(example);
		
		return list!=null&&list.size()==1?list.get(0):null;
	}
	@Override
	public UserinfoCustom getUserinfoCustom(Userinfo userinfo) {
		UserfaceImg userfaceImg = getUserfaceImg(userinfo);
		return new UserinfoCustom(userfaceImg==null?0:userfaceImg.getId(), userfaceImg==null?null:userfaceImg.getFaceimg(), userinfo.getUsername(), userinfo.getPassword(), userinfo.getNickname(), userinfo.getGender());
	}
}
