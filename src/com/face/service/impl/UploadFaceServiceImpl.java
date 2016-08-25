package com.face.service.impl;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.mapper.UploadFaceCustomMapper;
import com.face.mapper.UploadFaceMapper;
import com.face.po.UploadFace;
import com.face.service.UploadFaceService;
import com.face.util.FileUtil;
@Service
public class UploadFaceServiceImpl implements UploadFaceService{
	@Autowired
	private UploadFaceMapper uploadFaceMapper;
	@Autowired
	private UploadFaceCustomMapper uploadFaceCustomMapper;
	@Override
	public int addUploadFace(UploadFace uploadFace) {
		return this.uploadFaceMapper.insert(uploadFace);
	}
	@Override
	public List<UploadFace> getAllUploadFaceList() {
		return uploadFaceMapper.selectByExample(null);
	}
	@Override
	public List<UploadFace> getAllUploadFaceListByPage(int pageNo, int pageSize) {
		return uploadFaceCustomMapper.selectByPage(pageSize,pageNo, (pageNo-1)*pageSize);
	}
	@Override
	public int getAllUploadFaceCount() {
		return uploadFaceCustomMapper.getUploadFaceSize();
	}
	@Override
	public void deleteUploadFace(UploadFace uploadFace) {
		try {
			//删除缩略图
			File fileThumbNail=FileUtil.newUploadFileThumbnail(this,FileUtil.getUploadFile(this, uploadFace.getUploadimg()));
			if(fileThumbNail.exists()){
				fileThumbNail.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//删除图片
			File fileSrc=FileUtil.getUploadFile(this, uploadFace.getUploadimg());
			if(fileSrc.exists()){
				fileSrc.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//删除数据库中记录
			uploadFaceMapper.deleteByPrimaryKey(uploadFace.getId()==null?new Long(0):uploadFace.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
