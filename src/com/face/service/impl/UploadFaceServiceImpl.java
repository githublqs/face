package com.face.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.mapper.UploadFaceCustomMapper;
import com.face.mapper.UploadFaceMapper;
import com.face.po.UploadFace;
import com.face.service.UploadFaceService;
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


}
