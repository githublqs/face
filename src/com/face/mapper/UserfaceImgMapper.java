package com.face.mapper;

import com.face.po.UserfaceImg;
import com.face.po.UserfaceImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserfaceImgMapper {
    int countByExample(UserfaceImgExample example);

    int deleteByExample(UserfaceImgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserfaceImg record);

    int insertSelective(UserfaceImg record);

    List<UserfaceImg> selectByExample(UserfaceImgExample example);

    UserfaceImg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserfaceImg record, @Param("example") UserfaceImgExample example);

    int updateByExample(@Param("record") UserfaceImg record, @Param("example") UserfaceImgExample example);

    int updateByPrimaryKeySelective(UserfaceImg record);

    int updateByPrimaryKey(UserfaceImg record);
}