package com.face.mapper;

import com.face.po.Userinfo;
import com.face.po.UserinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserinfoMapper {
    int countByExample(UserinfoExample example);

    int deleteByExample(UserinfoExample example);

    int deleteByPrimaryKey(String username);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoExample example);

    Userinfo selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}