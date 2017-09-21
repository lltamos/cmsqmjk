package com.quanmin.dao.mapper;

import com.quanmin.model.BBSComment;
import com.quanmin.model.BBSCommentExample;
import com.quanmin.model.custom.CommentInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBSCommentMapper {
    int countByExample(BBSCommentExample example);

    int deleteByExample(BBSCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSComment record);

    int insertSelective(BBSComment record);

    List<BBSComment> selectByExample(BBSCommentExample example);

    BBSComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSComment record, @Param("example") BBSCommentExample example);

    int updateByExample(@Param("record") BBSComment record, @Param("example") BBSCommentExample example);

    int updateByPrimaryKeySelective(BBSComment record);

    int updateByPrimaryKey(BBSComment record);
    /**
     * 根据消息id查看评论
     * @param bbsInformationId
     * @return
     */
	List<CommentInfo> selectByInformationId(Integer bbsInformationId);
	
	
	/**
	 * 查询出最大的sort
	 * @param record
	 * @return
	 */
	Integer selectByInformationIdAndParentIdAndType(BBSComment record);
}