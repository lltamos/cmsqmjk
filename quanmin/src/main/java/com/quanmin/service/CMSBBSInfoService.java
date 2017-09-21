package com.quanmin.service;

import com.quanmin.model.BBSInformation;
import com.quanmin.model.custom.BBSInformationAndUser;
import com.quanmin.model.custom.CommentInfo;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;

/**
 * Created by yang on 2017/6/28.
 */

public interface CMSBBSInfoService {
    /**
     * 根据条件，查询帖子信息
     * @param searchCondition
     * @return
     */
    List<BBSInformationAndUser> getBBsListByCondition(SearchCondition searchCondition);

    /**
     * 删除帖子
     * @param searchCondition
     * @return
     */
    Integer deleteByCondition(SearchCondition searchCondition);

    /**
     * 查询单个帖子
     * @param searchCondition
     * @return
     */
    BBSInformation showCMSBBSInfoById(SearchCondition searchCondition);

    /**
     * 查看回复信息
     * @param searchCondition
     * @return
     */
    List<CommentInfo> showCMSBBSCommentById(SearchCondition searchCondition);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    Integer deleteCommentByCOmmentId(Integer commentId);
}
