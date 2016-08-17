package com.face.service;

import java.util.List;

import com.face.po.UserfaceImg;
import com.face.po.Userinfo;
import com.face.po.UserinfoCustom;
public interface UsernifosService {
	public List<Userinfo> getAllUserInfos();
	public boolean userRegisted(Userinfo userinfo);
	public boolean userRegisted(UserinfoCustom userinfoCustom);
	public boolean saveUserInfo(UserinfoCustom userinfoCustom);
	public UserfaceImg getUserfaceImg(Userinfo userinfo);
	
	public UserinfoCustom getUserinfoCustom(Userinfo userinfo);
}
