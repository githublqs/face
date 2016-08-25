package com.face.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.face.po.UploadFace;
@Service
public interface UploadFaceService {
	public int addUploadFace(UploadFace uploadFace);
	public List<UploadFace> getAllUploadFaceList();
	public List<UploadFace> getAllUploadFaceListByPage(int pageNo,int pageSize);
	public int getAllUploadFaceCount();
	/**
	 * // 根据UploadFace 的 id 删除上传图片及数据库中记录
	 * @param uploadFace
	 */
	public void deleteUploadFace(UploadFace uploadFace);
	
}
