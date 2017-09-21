package com.quanmin.dao.mapper;

import com.quanmin.model.Pushmessage;
import com.quanmin.model.PushmessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushmessageMapper {
	int countByExample(PushmessageExample example);

	int deleteByExample(PushmessageExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Pushmessage record);

	int insertSelective(Pushmessage record);

	List<Pushmessage> selectByExample(PushmessageExample example);

	Pushmessage selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Pushmessage record, @Param("example") PushmessageExample example);

	int updateByExample(@Param("record") Pushmessage record, @Param("example") PushmessageExample example);

	int updateByPrimaryKeySelective(Pushmessage record);

	int updateByPrimaryKey(Pushmessage record);

	List<Pushmessage> selectByUserId(@Param("id") String id);
}