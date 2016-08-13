package com.face.mapper;

import com.face.po.UploadFace;
import com.face.po.UploadFaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadFaceMapper {
    int countByExample(UploadFaceExample example);

    int deleteByExample(UploadFaceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UploadFace record);

    int insertSelective(UploadFace record);

    List<UploadFace> selectByExample(UploadFaceExample example);

    UploadFace selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UploadFace record, @Param("example") UploadFaceExample example);

    int updateByExample(@Param("record") UploadFace record, @Param("example") UploadFaceExample example);

    int updateByPrimaryKeySelective(UploadFace record);

    int updateByPrimaryKey(UploadFace record);
}