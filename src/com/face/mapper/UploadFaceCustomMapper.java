package com.face.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.face.po.UploadFace;
public interface UploadFaceCustomMapper {
	 List<UploadFace> selectByPage(@Param("pageSize") int pageSize,@Param("pageNo") int  pageNo,@Param("temp") int  temp);
	 int getUploadFaceSize();
}
