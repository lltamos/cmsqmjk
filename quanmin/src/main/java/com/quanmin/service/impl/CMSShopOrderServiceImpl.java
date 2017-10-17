package com.quanmin.service.impl;

import com.alipay.api.AlipayApiException;
import com.quanmin.dao.jpa.*;
import com.quanmin.dao.mapper.PushmessageMapper;
import com.quanmin.model.Pushmessage;
import com.quanmin.model.jpapo.*;
import com.quanmin.model.po.ExportOrderPo;
import com.quanmin.qmmq.job.CmsBootstrap;
import com.quanmin.qmmq.job.SMSResendJob;
import com.quanmin.service.APPPayService;
import com.quanmin.service.CMSIShopOrderService;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SortUtils;
import com.quanmin.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 * ShopOrderServiceImpl
 *
 * @author llsmp
 * @date 2017 /7/25
 */
@SuppressWarnings("ALL")
@Service
@Transactional
public class CMSShopOrderServiceImpl implements CMSIShopOrderService {


    @Resource
    private ShopOrderDao shopOrderDao;
    @Resource
    private ShopReceiptAddressDao shopReceiptAddressDao;

    @Resource
    private ShopCommodityDao shopCommodityDao;

    @Resource
    private ShopCommidityOrderDao shopCommidityOrderDao;

    @Resource
    private ShopRefundRecordDao shopRefundRecordDao;

    @Resource
    private APPPayService appPayService;

    @Resource
    private BusinessAccountDao accountDao;

    @Resource
    private PushmessageMapper pushmessageMapper;

    @Resource
    private CmsBootstrap qmBootstrap;

    @Override
    public ResultUtils findAll(Integer page, Integer size, Integer type, String querystr) {

        if (page < 1) {
            return ResultUtils.returnFail();
        }
        PageRequest pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
        Page<ShopOrder> shopOrders;
        if (type == null || type == 0) {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findAll(pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderNumLikeOrUserNickNameLikeOrReceiveNameLikeOrReceivePhoneLike(querystr, querystr, querystr, querystr, pageable);
            }
        } else if (type == 4) {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findByOrderStatusEqualsAndTypeEquals(type, 2, pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderStatusAndTypeliked(querystr, type, 2, pageable);
            }
        } else {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findByOrderStatusEqualsAndTypeEquals(type, 0, pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderStatusAndTypeliked(querystr, type, 0, pageable);
            }
        }
        List<ShopOrder> result=shopOrders.getContent();

        if (result == null || result.size() == 0) {
            return ResultUtils.returnFail();
        }
        return ResultUtils.returnSucess(result, shopOrders.getTotalPages());
    }


    @Override
    public ResultUtils orderdetails(Long orderId) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        if (shopOrder == null) {
            return ResultUtils.returnException();
        }

        long createTime=shopOrder.getCreateTime().getTime();
        long time=System.currentTimeMillis();
        long ms=time - createTime;
        int ss=1000;
        int mi=ss * 60;
        int hh=mi * 60;
        int dd=hh * 24;
        long day=ms / dd;
        long hour=(ms - day * dd) / hh;

        Map<String, Object> map=new HashMap<>();
        List<Map> list=new ArrayList<>();
        Long id=shopOrder.getId();
        List<ShopCommodityOrder> lists=shopCommidityOrderDao.findAllByOrderIdEquals(id);

        for (ShopCommodityOrder o : lists) {
            ShopCommodity one=shopCommodityDao.findOne(o.getCommodityId());
            Map<String, Object> temp=new HashMap<>();
            temp.put("ShopCommodity", one);
            temp.put("ShopCommodityOrder", o);
            list.add(temp);
        }

        Long receiptAddressId=shopOrder.getReceiptAddressId();
        ShopReceiptAddress receiptAddress=shopReceiptAddressDao.findOne(receiptAddressId);
        map.put("receiptAddress", receiptAddress);
        map.put("order", shopOrder);
        map.put("list", list);
        map.put("day", day);
        map.put("hours", hour);
        return ResultUtils.returnSucess(map);
    }


    //退款页面
    @Override
    public ResultUtils refundOrderList(Integer type, Integer status, Integer auditStatus, Integer page, Integer size, String querystr) {
        if (page < 1) {
            return ResultUtils.returnFail();
        }

        Pageable pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
        Page<ShopRefundRecord> refundOrder;
        if (status == 4) {
            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIs(type, pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByBackTypeIsLikes(querystr, type, pageable);
            }

        } else if (status == 2) {

            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIsAndAuditStatusIs(type, status, auditStatus, pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIsAndAuditStatusIsLikes(querystr, type, status, auditStatus, pageable);
            }

        } else {
            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIs(type, status, pageable);
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByReFoundTypeLikes(querystr, type, status, pageable);
            }
        }

        if (refundOrder == null || refundOrder.getContent() == null || refundOrder.getContent().size() == 0) {
            return ResultUtils.returnFail();
        }
        return ResultUtils.returnSucess(refundOrder);
    }

    //退款审核
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public ResultUtils refundAudit(Long refundId, Integer type) throws AlipayApiException {
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundId);
        if (refundRecord == null) {
            return ResultUtils.returnException();
        }


        if (refundRecord.getBackType() != 0) {
            return ResultUtils.returnException();
        }

        if (refundRecord.getAuditStatus() != 0) {
            return ResultUtils.returnFail("该订单以处理");
        }


        if (refundRecord.getShopOrderId() == null) {
            return ResultUtils.returnException();
        }


        //允许
        if (type == 0) {
            if (refundRecord.getStatus() == 0) {
                ShopCommodityOrder shopCommodityOrders=shopCommidityOrderDao.findOne(refundRecord.getShopCommodityOrderId());
                ShopCommodity shopCommodity=shopCommodityDao.findOne(shopCommodityOrders.getCommodityId());
                shopCommodity.setTotalNum(shopCommodity.getTotalNum() + shopCommodityOrders.getAmount());
                shopCommodityOrders.setStatus(3);
                refundRecord.setStatus(2);
                refundRecord.setAuditStatus(2);
                refundRecord.setEndTime(new Date());
                refundRecord.setAuditTime(new Date());
                shopRefundRecordDao.save(refundRecord);
                shopCommidityOrderDao.save(shopCommodityOrders);

                List<ShopCommodityOrder> commodityOrderList=shopCommidityOrderDao.findByOrderIdIsAndStatusIs(refundRecord.getShopOrderId(), 1);
                if (commodityOrderList == null || commodityOrderList.size() == 0) {
                    ShopOrder shopOrder=shopOrderDao.findOne(refundRecord.getShopOrderId());
                    shopOrder.setType(1);
                    shopOrder.setIsRefound(1);
                    shopOrderDao.saveAndFlush(shopOrder);
                }

                ResultUtils resultUtils=appPayService.submitRebate(refundRecord.getId());

                BusinessAccount account=new BusinessAccount();
                account.setCreateTime(refundRecord.getCreateTime());
                account.setBookedTime(new Date());
                account.setUpdateTime(new Date());
                account.setType(2);
                account.setFromType(refundRecord.getFromType());
                account.setOrderId(refundRecord.getShopOrderId());
                account.setOrderNum(refundRecord.getShopOrderNum());
                account.setRefundNum(refundRecord.getRefundNum());
                account.setUserId(refundRecord.getUserId());
                account.setUserNickName(refundRecord.getUserNickName());
                account.setUserPhone(refundRecord.getUserPhone());
                account.setMoney(refundRecord.getBackMoney());
                accountDao.save(account);


                if (!Objects.equals(resultUtils.getResultCode(), "200")) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultUtils.returnFail("退款失败");
                }
                // Integer sendSMS=SendSMSUtil.sendSMS(refundRecord.getUserPhone(), "203742", new String[]{refundRecord.getRefundNum()});
                SMSResendJob smsJob=new SMSResendJob();
                smsJob.setPhone(refundRecord.getUserPhone());
                smsJob.setStrings(new String[]{refundRecord.getRefundNum()});
                smsJob.setTemplateId("203743");
                qmBootstrap.addJob(smsJob);

                Pushmessage pushmessage=new Pushmessage();
                pushmessage.setType(2);
                pushmessage.setTitle("亲，退款申请已审批。");
                pushmessage.setUserId(refundRecord.getUserId());
                pushmessage.setReadStatus("0");
                pushmessage.setText("【全民健康】亲，您的订单" + refundRecord.getRefundNum() + "（退款编号）退款成功，退款将在3-7个工作日退还至原支付卡，请关注您的退款入账记录，祝您生活愉快");
                pushmessageMapper.insertSelective(pushmessage);
                return ResultUtils.returnSucess();
            }
        }
        //拒绝
        if (type == 1) {
            ShopCommodityOrder shopCommodityOrders=shopCommidityOrderDao.findOne(refundRecord.getShopCommodityOrderId());
            ShopOrder shopOrder=shopOrderDao.findOne(refundRecord.getShopOrderId());
            shopCommodityOrders.setStatus(1);
            shopOrder.setIsRefound(0);
            refundRecord.setStatus(2);
            refundRecord.setAuditStatus(1);
            refundRecord.setEndTime(new Date());
            refundRecord.setAuditTime(new Date());
            return ResultUtils.returnSucess();
        }
        return ResultUtils.returnFail();
    }


    @Override
    public ResultUtils refundCargoAudit(Long refundId, Integer type) throws AlipayApiException {
        ShopOrder shopOrder;
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundId);
        if (refundRecord == null) {
            return ResultUtils.returnException();
        }

        if (refundRecord.getShopOrderId() == null) {
            return ResultUtils.returnException();
        }

        if (refundRecord.getBackType() != 1) {
            return ResultUtils.returnException();
        }


        ShopCommodityOrder shopCommodityOrder=shopCommidityOrderDao.findOne(refundRecord.getShopCommodityOrderId());

        if (shopCommodityOrder == null) {
            return ResultUtils.returnFail();
        }

        ShopCommodityOrder shopCommodityOrders=shopCommidityOrderDao.findOne(refundRecord.getShopCommodityOrderId());

        if (type != 2) {
            if (refundRecord.getAuditStatus() != 0) {
                return ResultUtils.returnFail("该订单以处理");
            }
        }

        //同意退货申请
        if (type == 0) {
            refundRecord.setAuditStatus(2);
            refundRecord.setStatus(1);
            refundRecord.setAuditTime(new Date());
            // Integer sendSMS=SendSMSUtil.sendSMS(refundRecord.getUserPhone(), "203742", new String[]{refundRecord.getRefundNum()});

            SMSResendJob smsJob=new SMSResendJob();
            smsJob.setPhone(refundRecord.getUserPhone());
            smsJob.setStrings(new String[]{refundRecord.getRefundNum()});
            smsJob.setTemplateId("203742");
            qmBootstrap.addJob(smsJob);

            Pushmessage pushmessage=new Pushmessage();
            pushmessage.setType(3);
            pushmessage.setTitle("亲，退货申请已审批。");
            pushmessage.setUserId(refundRecord.getUserId());
            pushmessage.setReadStatus("0");
            pushmessage.setText("【全民健康】亲，您的订单" + refundRecord.getRefundNum() + "（退款编号）退货申请已经通过，请7日内将商品寄回，祝您生活愉快~");
            pushmessageMapper.insertSelective(pushmessage);
            return ResultUtils.returnSucess();
        }
        //拒绝退货申请
        if (type == 1) {
            refundRecord.setAuditTime(new Date());
            refundRecord.setAuditStatus(1);
            refundRecord.setStatus(2);
            refundRecord.setEndTime(new Date());
            shopCommodityOrders.setStatus(1);
            return ResultUtils.returnSucess();

        }
        //收到货同意退款
        if (type == 2) {
            if (refundRecord.getStatus() == 1 && refundRecord.getAuditStatus() == 2) {

                ResultUtils resultUtils=appPayService.submitRebate(refundRecord.getId());

                ShopCommodity shopCommodity=shopCommodityDao.findOne(shopCommodityOrders.getCommodityId());
                shopCommodity.setTotalNum(shopCommodity.getTotalNum() + shopCommodityOrders.getAmount());

                if (!Objects.equals(resultUtils.getResultCode(), "200")) {
                    return ResultUtils.returnFail();
                }
                shopCommodityOrders.setStatus(3);
                refundRecord.setStatus(2);
                refundRecord.setEndTime(new Date());

                List<ShopCommodityOrder> commodityOrderList=shopCommidityOrderDao.findAllByOrderIdEquals(refundRecord.getShopOrderId());

                boolean smark=true;
                if ((commodityOrderList != null && commodityOrderList.size() > 0)) {
                    for (ShopCommodityOrder sc : commodityOrderList) {
                        Integer status=sc.getStatus();
                        if (status != 3) {
                            smark=false;
                        }
                    }
                    if (smark) {
                        shopOrder=shopOrderDao.findOne(refundRecord.getShopOrderId());
                        shopOrder.setType(1);
                        shopOrder.setIsRefound(1);
                    }
                }

                //   Integer sendSMS=SendSMSUtil.sendSMS(refundRecord.getUserPhone(), "203745", new String[]{refundRecord.getRefundNum()});

                SMSResendJob smsJob=new SMSResendJob();
                smsJob.setPhone(refundRecord.getUserPhone());
                smsJob.setStrings(new String[]{refundRecord.getRefundNum()});
                smsJob.setTemplateId("203745");
                qmBootstrap.addJob(smsJob);

                Pushmessage pushmessage=new Pushmessage();
                pushmessage.setType(4);
                pushmessage.setTitle("亲，退货成功。");
                pushmessage.setUserId(refundRecord.getUserId());
                pushmessage.setReadStatus("0");
                pushmessage.setText("【全民健康】亲，您的订单" + refundRecord.getRefundNum() + "（退货编号）退货成功，退款将在3-7个工作日退还至原支付卡，请关注您的退款入账记录，祝您生活愉快");
                pushmessageMapper.insertSelective(pushmessage);
                return ResultUtils.returnSucess();

            }
        }
        return ResultUtils.returnFail();
    }

    @Override
    public ResultUtils refundorderDetails(Long refundID) {
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundID);
        ShopCommodity commoditye=shopCommodityDao.findOne(refundRecord.getCommodityId());
        Map<String, Object> m=new HashMap<>();
        m.put("refundRecord", refundRecord);
        m.put("commodity", commoditye);
        return ResultUtils.returnSucess(m);
    }

    @Override
    public ResultUtils exportOrder(Integer type, String querystr) {


        List<ExportOrderPo> result;
        ExportOrderPo exportOrderPo;
        List<ShopOrder> shopOrders;
        if (type == null || type == 0) {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findAll(SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderNumLikeOrUserNickNameLikeOrReceiveNameLikeOrReceivePhoneLike(querystr, querystr, querystr, querystr, SortUtils.DESCCreateTime());
            }
        } else if (type == 4) {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findByOrderStatusEqualsAndTypeEquals(type, 2, SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderStatusAndTypeliked(querystr, type, 2, SortUtils.DESCCreateTime());
            }
        } else {
            if (StringUtils.isBlank(querystr)) {
                shopOrders=shopOrderDao.findByOrderStatusEqualsAndTypeEquals(type, 0, SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                shopOrders=shopOrderDao.findByOrderStatusAndTypeliked(querystr, type, 0, SortUtils.DESCCreateTime());
            }
        }
        if (shopOrders == null || shopOrders.size() == 0) {
            return ResultUtils.returnFail();
        }

        result=new ArrayList<>();
        for (ShopOrder shopOrder : shopOrders) {
            List<ShopCommodityOrder> all=shopCommidityOrderDao.findAllByOrderIdEquals(shopOrder.getId());

            for (ShopCommodityOrder commodityOrder : all) {
                exportOrderPo=new ExportOrderPo();
                exportOrderPo.setShopCommodityOrder(commodityOrder);
                exportOrderPo.setShopOrder(shopOrder);
                result.add(exportOrderPo);
            }
        }

        return ResultUtils.returnSucess(result);

    }

    @Override
    public ResultUtils exportRefund(Integer type, Integer status, Integer auditStatus, String querystr) {

        List<ExportOrderPo> result;
        ExportOrderPo exportOrderPo;
        List<ShopRefundRecord> refundOrder;


        if (status == 4) {
            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIs(type, SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByBackTypeIsLikes(querystr, type, SortUtils.DESCCreateTime());
            }
        } else if (status == 2) {

            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIsAndAuditStatusIs(type, status, auditStatus, SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIsAndAuditStatusIsLikes(querystr, type, status, auditStatus, SortUtils.DESCCreateTime());
            }

        } else {
            if (StringUtils.isBlank(querystr)) {
                refundOrder=shopRefundRecordDao.findByBackTypeIsAndStatusIs(type, status, SortUtils.DESCCreateTime());
            } else {
                querystr=StringUtil.parseFix(querystr);
                refundOrder=shopRefundRecordDao.findByReFoundTypeLikes(querystr, type, status, SortUtils.DESCCreateTime());
            }
        }

        if (refundOrder == null || refundOrder.size() == 0) {
            return ResultUtils.returnFail();
        }
        AppointOrder appointOrder= new AppointOrder();
        appointOrder.setCreateTime(new Date());



        result=new ArrayList<>();
        for (ShopRefundRecord refundRecord : refundOrder) {
            ShopOrder shopOrder=shopOrderDao.findOne(refundRecord.getShopOrderId());
            exportOrderPo=new ExportOrderPo();
            exportOrderPo.setShopRefundRecord(refundRecord);
            exportOrderPo.setShopOrder(shopOrder);
            result.add(exportOrderPo);
        }
        return ResultUtils.returnSucess(result);
    }

    @Override
    public ResultUtils sendOut(Long orderId, Integer expressCompanyId, String expressCompanyNameSimple, String expressCompanyName, String expressNum, Integer type, String expressSendName) {

        if (expressSendName == null) {
            return ResultUtils.returnException();
        }

        ShopOrder shopOrder=shopOrderDao.findOne(orderId);

        if (shopOrder == null) {
            return ResultUtils.returnException();
        }

        if (shopOrder.getIsRefound() == 1) {
            return ResultUtils.returnFail("该订单已申请退款");
        }


        if (type == 0) {
            shopOrder.setExpressSendName(expressSendName);
            shopOrder.setExpressSendTime(new Date());
            shopOrder.setExpressCompanyId(expressCompanyId);
            shopOrder.setExpressCompanyName(expressCompanyName);
            shopOrder.setExpressNum(expressNum);
            shopOrder.setOrderStatus(3);
            shopOrder.setExpressCompanyNameSimple(expressCompanyNameSimple);
            Long time=System.currentTimeMillis();
            time=time + 1000 * 60 * 60 * 24 * 7;
            shopOrder.setReceiptTime(new Date(time));
//          Integer sendSMS=SendSMSUtil.sendSMS(shopOrder.getUserPhone(), "203735", new String[]{shopOrder.getOrderNum()});
            SMSResendJob smsJob=new SMSResendJob();
            smsJob.setPhone(shopOrder.getUserPhone());
            smsJob.setStrings(new String[]{shopOrder.getOrderNum()});
            smsJob.setTemplateId("203735");
            qmBootstrap.addJob(smsJob);

            Pushmessage pushmessage=new Pushmessage();
            pushmessage.setType(5);
            pushmessage.setTitle("亲，发货通知");
            pushmessage.setUserId(shopOrder.getUserId());
            pushmessage.setReadStatus("0");
            pushmessage.setText("【全民健康】亲，您的订单" + shopOrder.getOrderNum() + "（订单编号）已经发货，请您注意查收，祝您生活愉快~");
            pushmessageMapper.insertSelective(pushmessage);


        }
        if (type == 1) {
            shopOrder.setExpressCompanyId(expressCompanyId);
            shopOrder.setExpressCompanyName(expressCompanyName);
            shopOrder.setExpressNum(expressNum);
            shopOrder.setUpdateTime(new Date());
        }
        return ResultUtils.returnSucess(shopOrder);
    }


//    @Override
//    public ResultUtils findRemindSendOutList(Integer page, Integer size) {
//        if (page < 1) {
//            return ResultUtils.returnFail();
//        }
//        ShopOrder s=new ShopOrder();
//        s.setRemindStatus(1);
//        s.setOrderStatus(2);
//        s.setPayStatus(1);
//        Example<ShopOrder> example=Example.of(s);
//
//        PageRequest pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
//        Page shopOrderPage=shopOrderDao.findAll(example, pageable);
//
//        return ResultUtils.returnSucess(shopOrderPage);
//    }


}
