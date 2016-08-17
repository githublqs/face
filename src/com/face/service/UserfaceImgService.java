package com.face.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.face.po.UploadFace;
import com.face.po.UserfaceImg;
import com.face.po.Userinfo;
@Service
public interface UserfaceImgService {
	public List<UserfaceImg>getAllUserfaceImgs();
	public UserfaceImg getUserfaceImg(Userinfo userinfo);
	public void saveUserfaceImg(Userinfo userinfo,UploadFace uploadFace);
	public void saveOrUpdateUserfaceImg(String img0, Userinfo userinfo);
}
