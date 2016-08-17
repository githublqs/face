package com.face.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.mapper.UploadFaceMapper;
import com.face.po.UploadFace;
import com.face.service.UploadFaceService;
@Service
public class UploadFaceServiceImpl implements UploadFaceService{
	@Autowired
	private UploadFaceMapper uploadFaceMapper;
	@Override
	public int addUploadFace(UploadFace uploadFace) {
		return this.uploadFaceMapper.insert(uploadFace);
	}
	@Override
	public List<UploadFace> getAllUploadFaceList() {
		return uploadFaceMapper.selectByExample(null);
	}


}
