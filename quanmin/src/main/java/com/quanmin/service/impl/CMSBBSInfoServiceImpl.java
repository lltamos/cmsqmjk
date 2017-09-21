package com.quanmin.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.quanmin.dao.mapper.BBSCommentMapper;
import com.quanmin.dao.mapper.BBSInformationMapper;
import com.quanmin.model.*;
import com.quanmin.model.custom.BBSInformationAndUser;
import com.quanmin.model.custom.CommentInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSBBSInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yang on 2017/6/28.
 */
@Service

public class CMSBBSInfoServiceImpl implements CMSBBSInfoService {

    @Autowired
    private BBSInformationMapper informationMapper;
    @Autowired
    private BBSCommentMapper bbsCommentMapper;

    @Override
    public List<BBSInformationAndUser> getBBsListByCondition(SearchCondition searchCondition) {
        List<BBSInformationAndUser> list = informationMapper.showBBSList(searchCondition);
        return list;
    }

    @Override
    public Integer deleteByCondition(SearchCondition searchCondition) {
        BBSInformation bbsInformation = new BBSInformation();
        bbsInformation.setId(null != searchCondition.getBbsInformationId() ? Integer.parseInt(searchCondition.getBbsInformationId()) : 0);
        bbsInformation.setDelStatus(1);
        return informationMapper.updateByPrimaryKeySelective(bbsInformation);
    }

    @Override
    public BBSInformation showCMSBBSInfoById(SearchCondition searchCondition) {
        BBSInformation bbsInformation = informationMapper.selectByPrimaryKey(null != searchCondition.getBbsInformationId() ? Integer.parseInt(searchCondition.getBbsInformationId()) : 0);
        return bbsInformation;
    }

    @Override
    public List<CommentInfo> showCMSBBSCommentById(SearchCondition searchCondition) {
        // 查看评论信息
        BBSCommentExample example = new BBSCommentExample();
        example.setOrderByClause(" create_time desc");
        BBSCommentExample.Criteria criteria = example.createCriteria();
        criteria.andBbsInformationIdEqualTo(null != searchCondition.getBbsInformationId() ? Integer.parseInt(searchCondition.getBbsInformationId()) : 0);
        List<CommentInfo> list = bbsCommentMapper.selectByInformationId(null != searchCondition.getBbsInformationId() ? Integer.parseInt(searchCondition.getBbsInformationId()) : 0);
        return list;
    }

    @Override
    @Transactional
    public Integer deleteCommentByCOmmentId(Integer commentId) {
        int i = 0;
        BBSComment bbsComment = bbsCommentMapper.selectByPrimaryKey(commentId);
        i = bbsCommentMapper.deleteByPrimaryKey(commentId);
        if (bbsComment.getType() == 0) {
            BBSCommentExample bbsCommentExample = new BBSCommentExample();
            BBSCommentExample.Criteria criteria = bbsCommentExample.createCriteria();
            criteria.andParentIdEqualTo(bbsComment.getId());
            i =i+ bbsCommentMapper.deleteByExample(bbsCommentExample);
        }

        BBSInformation baseBbsInformation = informationMapper.selectByPrimaryKey(bbsComment.getBbsInformationId());
        BBSInformation newBbsInformation = new BBSInformation();
        newBbsInformation.setUpdateTime(new Date());
        newBbsInformation.setId(baseBbsInformation.getId());
        newBbsInformation.setCommentnum(baseBbsInformation.getCommentnum()-i);
        informationMapper.updateByPrimaryKeySelective(newBbsInformation);
        return i;

    }
}
