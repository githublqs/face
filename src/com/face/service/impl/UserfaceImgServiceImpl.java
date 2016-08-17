package com.face.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.mapper.UserfaceImgMapper;
import com.face.po.UploadFace;
import com.face.po.UserfaceImg;
import com.face.po.UserfaceImgExample;
import com.face.po.Userinfo;
import com.face.service.UserfaceImgService;
@Service
public class UserfaceImgServiceImpl implements UserfaceImgService {
@Autowired
private UserfaceImgMapper userfaceImgMapper;
@Override
public List<UserfaceImg>getAllUserfaceImgs(){
	return userfaceImgMapper.selectByExample(null);
}
@Override
public void saveUserfaceImg(Userinfo userinfo, UploadFace uploadFace) {
	try {
		UserfaceImg userfaceImg = new UserfaceImg();
		userfaceImg.setUsername(userinfo.getUsername());
		userfaceImg.setFaceimg(uploadFace.getUploadimg());
		userfaceImgMapper.insert(userfaceImg);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}


@Override
public UserfaceImg getUserfaceImg(Userinfo userinfo) {
	UserfaceImgExample example=new UserfaceImgExample();
	example.createCriteria().andUsernameEqualTo(userinfo.getUsername());
	List<UserfaceImg> list=userfaceImgMapper.selectByExample(example);
	return list!=null&list.size()>0?list.get(0):null;
}
@Override
public void saveOrUpdateUserfaceImg(String img0, Userinfo userinfo) {
	try {
		UserfaceImg userfaceImg=getUserfaceImg(userinfo);
		if(userfaceImg==null){
			userfaceImg = new UserfaceImg();
			userfaceImg.setUsername(userinfo.getUsername());
			userfaceImg.setFaceimg(img0);
			userfaceImgMapper.insert(userfaceImg);
		}else{
			userfaceImg.setFaceimg(img0);
			userfaceImgMapper.updateByPrimaryKey(userfaceImg);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
};

}
