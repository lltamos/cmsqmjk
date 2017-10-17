package com.quanmin.service.impl;

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.dao.jpa.AppointOrderDao;
import com.quanmin.dao.mapper.AppointOrderMapper;
import com.quanmin.model.custom.AppointParam;
import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.model.jpapo.PayApliyNotifyInfo;
import com.quanmin.qmmq.job.HttpSenderJob;
import com.quanmin.qmmq.job.CmsBootstrap;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.*;
import com.quanmin.util.alipayutil.PayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.quanmin.util.Commons.YIHUYISHEN_TOKEN;
import static com.quanmin.util.Commons.YIHU_HOST;

/**
 * @author DELL
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor=Exception.class)
public class AppointOrderServiceImpl implements AppointOrderService {

    private final Logger logger=LoggerFactory.getLogger(AppointOrderServiceImpl.class);
    private final String YIHUYISHEN_HOST=LoadPropertiesDataUtils.getValue(YIHU_HOST);

    @Resource
    private AppointOrderDao appointOrderDao;

    @Resource
    private AppointOrderMapper appointOrderMapper;

    @Resource
    private SerialNumberUtils numberUtils;

    @Resource
    private CmsBootstrap qmBootstrap;

    @Override
    public ResultUtils productionOrder(AppointParam appointParam) {

        List<NameValuePair> param=new ArrayList<>();
        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
            getToken();
        }
        param.add(new BasicNameValuePair("m", "createOrder"));
        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
        param.add(new BasicNameValuePair("platform", appointParam.getPlatform()));
        param.add(new BasicNameValuePair("deviceType", appointParam.getDeviceType()));
        param.add(new BasicNameValuePair("version", "1.0"));
        param.add(new BasicNameValuePair("consultantId", appointParam.getConsultantId()));
        param.add(new BasicNameValuePair("date", appointParam.getDate()));
        param.add(new BasicNameValuePair("segment", appointParam.getSegment()));
        param.add(new BasicNameValuePair("isTemporary", appointParam.getIsTemporary()));
        param.add(new BasicNameValuePair("visitInfoId", appointParam.getVisitInfoId()));
        param.add(new BasicNameValuePair("type", appointParam.getType()));
        param.add(new BasicNameValuePair("caseId", appointParam.getCaseId()));
        param.add(new BasicNameValuePair("name", appointParam.getName()));
        param.add(new BasicNameValuePair("gender", appointParam.getGender()));
        param.add(new BasicNameValuePair("age", appointParam.getAge()));
        param.add(new BasicNameValuePair("mobile", appointParam.getMobile()));
        param.add(new BasicNameValuePair("cardNo", appointParam.getCardNo()));
        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);

        if (m != null) {

            Object success=m.get("success");
            if (success != null && "true".equals(success.toString())) {
                Map<String, Object> item=(Map) m.get("item");
                String orderNo=String.valueOf(item.get("orderNo"));
                String deptName=String.valueOf(item.get("deptName"));

                Integer timeLimit=Integer.parseInt(item.get("timeLimit").toString());
                Integer status=Integer.parseInt(item.get("status").toString());
                Integer type=Integer.parseInt(item.get("type").toString());
                String treatmentHospital=String.valueOf(item.get("treatmentHospital"));
                String scheduleTime=String.valueOf(item.get("scheduleTime"));
                Double price=Double.parseDouble(item.get("price").toString());
                String address=String.valueOf(item.get("address"));
                String hospitalName=String.valueOf(item.get("hospitalName"));
                String consultantName=String.valueOf(item.get("consultantName"));
                Integer consultantId=Integer.parseInt(item.get("consultantId").toString());

                AppointOrder appointOrder=new AppointOrder();

                appointOrder.setPayType(1);
                appointOrder.setCreateTime(new Date());
                appointOrder.setUpdateTime(new Date());
                appointOrder.setOrderNo(orderNo);
                appointOrder.setDeptName(deptName);
                appointOrder.setTimeLimit(timeLimit);
                appointOrder.setType(type);
                appointOrder.setTreatmentHospital(treatmentHospital);
                appointOrder.setTreatmentHospitalAddress(address);
                appointOrder.setScheduleTime(scheduleTime);
                appointOrder.setPrice(price);
                appointOrder.setHospitalName(hospitalName);
                appointOrder.setConsultantId(consultantId);
                appointOrder.setConsultantName(consultantName);

                appointOrder.setCity(appointParam.getCity());
                appointOrder.setVisitInfoId(appointParam.getVisitInfoId());
                appointOrder.setIdCard(appointParam.getCardNo());
                appointOrder.setPatientAge(appointParam.getAge());
                appointOrder.setPatientGender(appointParam.getGender());
                appointOrder.setPatientName(appointParam.getName());
                appointOrder.setPatientPhone(appointParam.getMobile());
                appointOrder.setUserNickName(appointParam.getUserNickName());
                appointOrder.setUserPhone(appointParam.getUserPhone());
                appointOrder.setPayStatus(0);
                appointOrder.setAppointType(1);
                appointOrder.setOrderStatus(2);
                appointOrder.setCaseId(appointParam.getCaseId());
                appointOrder.setUserId(appointParam.getUserId());
                appointOrder.setLifeType(1);
                appointOrder.setIsCallBack(0);
                appointOrder.setTreatmentTime(scheduleTime);
                appointOrder.setExpirationTime(new Date(appointOrder.getCreateTime().getTime() + timeLimit - 60000));
                appointOrder.setUrlHeader(appointParam.getUrlHeader());
                appointOrder.setRank(appointParam.getRank());

                appointOrder.setIsNotifyYihu(0);
                GregorianCalendar ca=new GregorianCalendar();
                ca.setTime(new Date(Long.parseLong(scheduleTime)));
                appointOrder.setPeriod(ca.get(GregorianCalendar.AM_PM));
                appointOrder.setTreatmentPeriod(appointOrder.getPeriod());

                AppointOrder order=appointOrderDao.save(appointOrder);
                if (order != null) {
                    return ResultUtils.returnSucess(order);
                }
            }
        }
        YIHUYISHEN_TOKEN=null;
        return ResultUtils.returnFail("一呼token失效");
    }


    @Override
    public ResultUtils productionExtraOrder(AppointOrder appointOrder) {
        appointOrder.setAppointType(2);
        appointOrder.setLifeType(1);
        appointOrder.setCreateTime(new Date());
        appointOrder.setUpdateTime(new Date());
        appointOrder.setOrderStatus(1);
        appointOrder.setPayStatus(0);
        appointOrder.setPayType(1);
        appointOrder.setIsCallBack(0);
        appointOrder.setOrderNo(numberUtils.createAppointNum());
        appointOrder.setIsNotifyYihu(0);
        AppointOrder order=appointOrderDao.save(appointOrder);
        if (order != null) {
            return ResultUtils.returnSucess(order);
        }
        return ResultUtils.returnFail("请稍候再试");
    }

    @Override
    public ResultUtils factorQueryList(PageArgs pageArgs, AppointOrder appointOrder) {
        appointOrder.setExt1(pageArgs.getQueryStr());
        if (pageArgs.isDisPart()) {
            //不分页
            List<Map<String, Object>> maps=appointOrderMapper.factorQueryList(appointOrder);
            return maps != null && maps.size() > 0 ? ResultUtils.returnSucess(maps) : ResultUtils.returnFail();
        } else {
            //分页
            PageHelper.startPage(pageArgs.getPage(), pageArgs.getSize(), true);
            List<Map<String, Object>> maps=appointOrderMapper.factorQueryList(appointOrder);
            Page page=(Page) maps;
            return maps != null && maps.size() > 0 ? ResultUtils.returnSucess(maps, page.getPages()) : ResultUtils.returnFail();
        }

    }

    @Override
    public ResultUtils completeAppoint(PageArgs args, Integer userId) {
        Pageable pageable=new PageRequest(args.getPage() - 1, args.getSize(), SortUtils.DESCCreateTime());
        org.springframework.data.domain.Page<AppointOrder> page=appointOrderDao.findByUserIdIsAndLifeTypeIn(userId, Collections.singletonList(2), pageable);
        return ResultUtils.returnSucess(page);
    }

    @Override
    public ResultUtils progressAppoint(PageArgs args, Integer userId) {
        Pageable pageable=new PageRequest(args.getPage() - 1, args.getSize(), SortUtils.DESCCreateTime());
        org.springframework.data.domain.Page<AppointOrder> page=appointOrderDao.findByUserIdIsAndLifeTypeIn(userId, Arrays.asList(1, 3), pageable);
        return ResultUtils.returnSucess(page);
    }

    @Override
    public ResultUtils appointDitails(Long appointId, Integer userId) {

        AppointOrder appointOrder=appointOrderDao.findOne(appointId);

        if (appointOrder == null || !Objects.equals(appointOrder.getUserId(), userId)) {
            return ResultUtils.returnFail();
        }

        if (appointOrder.getAppointType() == 2) {
            Map<String, Object> m=new HashMap<>();
            m.put("appointType", appointOrder.getAppointType());
            m.put("localAppointOrder", appointOrder);
            return ResultUtils.returnSucess(m);
        }
        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
            getToken();
        }

        List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("m", "getOrderInfo"));
        param.add(new BasicNameValuePair("orderNo", appointOrder.getOrderNo()));
        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
        param.add(new BasicNameValuePair("version", "1.0"));
        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
        if (m != null) {
            String success=String.valueOf(m.get("success"));
            if (success != null && "true".equals(success)) {
                m.put("appointType", appointOrder.getAppointType());
                m.put("localAppointOrder", appointOrder);
                return ResultUtils.returnSucess(m);
            }
        }
        YIHUYISHEN_TOKEN=null;

        return ResultUtils.returnFail("网络异常,请稍后再试");
    }


    @Override
    public ResultUtils returnAppiont(Long appointId, Integer userId, String desc) {
        AppointOrder appointOrder=appointOrderDao.findOne(appointId);
        if (appointOrder == null || !Objects.equals(appointOrder.getUserId(), userId)) {
            return ResultUtils.returnFail();
        }

        BigDecimal decimal=new BigDecimal(appointOrder.getPrice() * 0.8);
        BigDecimal setScale=decimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        double doubleValue=setScale.doubleValue();

        if (appointOrder.getAppointType() == 2) {

            Map<String, String> stringMap=new HashMap<>();
            stringMap.put("orderNo", appointOrder.getOrderNo());
            stringMap.put("price", appointOrder.getPrice() + "");
            stringMap.put("desc", desc);
            HttpSenderJob mengJob=new HttpSenderJob(HttpSenderJob.From.APPOINTRETURN, stringMap);
            qmBootstrap.addJob(mengJob);
            appointOrder.setLifeType(3);
            return ResultUtils.returnSucess();
        }

        String treatmentTime=appointOrder.getTreatmentTime();
        Date date=new Date(Long.parseLong(treatmentTime));
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        Date time=calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format=simpleDateFormat.format(time);
//        try {
//            Date parse=simpleDateFormat.parse(format);
//            if (parse.getTime()<System.currentTimeMillis()){
//                return ResultUtils.returnFail("就诊前一天17：00之前可退改，17：00之后不可退改。");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("m", "cancelOrder"));
        param.add(new BasicNameValuePair("orderNo", appointOrder.getOrderNo()));
        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
        param.add(new BasicNameValuePair("version", "1.0"));
        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
        if (m != null && StringUtils.equals(String.valueOf(m.get("success")), "true")) {
            //调用退款
            Map<String, String> stringMap=new HashMap<>();
            stringMap.put("orderNo", appointOrder.getOrderNo());
            stringMap.put("price", appointOrder.getPrice() + "");
            stringMap.put("desc", desc);
            HttpSenderJob mengJob=new HttpSenderJob(HttpSenderJob.From.APPOINTRETURN, stringMap);
            qmBootstrap.addJob(mengJob);
            appointOrder.setLifeType(3);
            return ResultUtils.returnSucess();
        } else {
            return ResultUtils.returnFail("退款失败,请稍候再试");
        }
    }

    @Override
    public ResultUtils payAppoint(Long appointId, Integer userId, String body, String desc) {
        AppointOrder appointOrder=appointOrderDao.findOne(appointId);
        if (appointOrder == null || !Objects.equals(appointOrder.getUserId(), userId)) {
            return ResultUtils.returnFail();
        }
        try {
            String notifyURLNo=LoadPropertiesDataUtils.getValue("alipay.appoint.notifyURLNo");
            String responce=PayUtils.aliPay(appointOrder.getOrderNo(), appointOrder.getPrice(), body, desc, notifyURLNo);
            if (responce == null) {
                return ResultUtils.returnFail("网络开小差了`请稍候再试");
            }
            return ResultUtils.returnSucess(responce);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ResultUtils.returnFail("网络开小差了`请稍候再试");
        }
    }

    @Override
    public void callbackalipay(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> map;
        PayApliyNotifyInfo payApliyNotifyInfo;
        try {
            map=PayUtils.callbackAliPayToMap(request);
            payApliyNotifyInfo=PayUtils.callbackAliPayToPojo(map);

            String outTradeNo=payApliyNotifyInfo.getOutTradeNo();
            String tradeNo=payApliyNotifyInfo.getTradeNo();

            AppointOrder appointOrder=appointOrderDao.findByOrderNo(outTradeNo);
            if (appointOrder.getIsCallBack() == null || appointOrder.getIsCallBack() == 1) {
                return;
            }
            appointOrder.setOrderStatus(3);
            appointOrder.setPayStatus(1);
            appointOrder.setIsCallBack(1);
            appointOrder.setPayOrderNum(tradeNo);
            appointOrder.setUpdateTime(new Date());
            getToken();
            List<NameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("m", "confirmOrder"));
            param.add(new BasicNameValuePair("orderNo", appointOrder.getOrderNo()));
            param.add(new BasicNameValuePair("platform", "YongHeHealth"));
            param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
            param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
            param.add(new BasicNameValuePair("version", "1.0"));

            HttpSenderJob mengJob=new HttpSenderJob(HttpSenderJob.From.YIHUQUEREN, param);
            mengJob.setV3(appointOrder.getOrderNo());
            qmBootstrap.addJob(mengJob);

            //Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
            appointOrder.setIsNotifyYihu(1);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getToken() {
        List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("m", "login"));
        param.add(new BasicNameValuePair("account", "YongHeHealth"));
        param.add(new BasicNameValuePair("password", "a918ca3dcd8cbebd41f96d2cbefee499"));
        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
        param.add(new BasicNameValuePair("version", "1.0"));
        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
        if (m != null && StringUtils.equals(String.valueOf(m.get("success")), "true")) {
            Map item=(Map) m.get("item");
            YIHUYISHEN_TOKEN=(String) item.get("token");
            return YIHUYISHEN_TOKEN;
        }
        return null;
    }

    //获取地区
    @Override
    public ResultUtils getAreas() {

//        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
//            getToken();
//        }
//        List<NameValuePair> param=new ArrayList<>();
//        param.add(new BasicNameValuePair("m", "getCityList"));
//        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
//        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
//        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
//        param.add(new BasicNameValuePair("version", "1.0"));
//        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
//        if (m != null) {
//            return ResultUtils.returnSucess(m);
//        }
//        YIHUYISHEN_TOKEN=null;
//        return ResultUtils.returnFail("网络不稳定,稍后再试");

        List<Object[]> citys=appointOrderDao.findCitys();
        return ResultUtils.returnSucess(citys);


    }

    //获取医院列表
    @Override
    public ResultUtils getAreasHospital(String city) {

//        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
//            getToken();
//        }
//        List<NameValuePair> param=new ArrayList<>();
//        param.add(new BasicNameValuePair("m", "getHospitalList"));
//        param.add(new BasicNameValuePair("cityId", cityId));
//        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
//        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
//        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
//        param.add(new BasicNameValuePair("version", "1.0"));
//        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
//        if (m != null) {
//            return ResultUtils.returnSucess(m);
//        }
//
//        YIHUYISHEN_TOKEN=null;
//        return ResultUtils.returnFail("网络不稳定,稍后再试");
        List<Object[]> hospitalNames=appointOrderDao.findHospitalNames(city);
        return ResultUtils.returnSucess(hospitalNames);
    }

    //获取医院列表
    @Override
    public ResultUtils getHospitalOffice(String city, String hospital) {

//        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
//            getToken();
//        }
//        List<NameValuePair> param=new ArrayList<>();
//        param.add(new BasicNameValuePair("m", "getDeptList"));
//        param.add(new BasicNameValuePair("cityId", cityId));
//        param.add(new BasicNameValuePair("hospitalId", hospitalId));
//        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
//        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
//        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
//        param.add(new BasicNameValuePair("version", "1.0"));
//        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
//        if (m != null) {
//            return ResultUtils.returnSucess(m);
//        }
//        YIHUYISHEN_TOKEN=null;
//        return ResultUtils.returnFail("网络不稳定,稍后再试");


        List<Object[]> deptNames=appointOrderDao.finddeptNames(city, hospital);
        return ResultUtils.returnSucess(deptNames);


    }

    @Override
    public ResultUtils cancelAppointOrder(Long appointId, Integer userId) {
        AppointOrder appointOrder=appointOrderDao.findOne(appointId);
        if (appointOrder == null || !Objects.equals(appointOrder.getUserId(), userId)) {
            return ResultUtils.returnFail();
        }

        if (appointOrder.getLifeType() != 1) {
            return ResultUtils.returnFail();
        }

        if (appointOrder.getOrderStatus() == 1 || appointOrder.getOrderStatus() == 2) {

            if (appointOrder.getAppointType() == 2) {
                appointOrder.setLifeType(3);
                return ResultUtils.returnSucess(appointOrder);
            }
            try {
                List<NameValuePair> param=new ArrayList<>();
                param.add(new BasicNameValuePair("m", "cancelOrder"));
                param.add(new BasicNameValuePair("orderNo", appointOrder.getOrderNo()));
                param.add(new BasicNameValuePair("platform", "YongHeHealth"));
                param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
                param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
                param.add(new BasicNameValuePair("version", "1.0"));
                Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);
                if (m != null && StringUtils.equals(String.valueOf(m.get("success")), "true")) {
                    appointOrder.setLifeType(3);
                    return ResultUtils.returnSucess(appointOrder);
                }
                return ResultUtils.returnFail(m);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtils.returnException();
            }
        }
        return ResultUtils.returnFail("该订单已付款不能直接取消，已付款！！");
    }

    @Override
    public ResultUtils countConsultantOrder(Integer consultantId, Integer userId) {
        try {
            return ResultUtils.returnSucess(null, appointOrderDao.countByUserIdIsAndConsultantIdIs(userId, consultantId));
        } catch (Exception e) {
            return ResultUtils.returnFail();
        }
    }

    @Override
    public ResultUtils editAppointOrder(AppointOrder appointOrder) {
        AppointOrder order=appointOrderDao.findOne(appointOrder.getId());

        if (order.getOrderStatus() != 1) {
            return ResultUtils.returnFail("该订单状态不正确");
        }
        order.setPrice(appointOrder.getPrice());
        order.setTreatmentHospital(appointOrder.getTreatmentHospital());
        order.setTreatmentHospitalAddress(appointOrder.getTreatmentHospitalAddress());
        order.setTreatmentTime(appointOrder.getTreatmentTime());
        order.setScheduleTime(appointOrder.getTreatmentTime());
        order.setOrderStatus(2);
        order.setExpirationTime(new Date(System.currentTimeMillis() + 15 * 1000));
        order.setUpdateTime(new Date());

        order.setPeriod(appointOrder.getTreatmentPeriod());
        order.setTreatmentPeriod(appointOrder.getTreatmentPeriod());
        return ResultUtils.returnSucess(order);
    }

    @Override
    public ResultUtils fetchHospitallistByConsultant(String consultantId) {
        if (StringUtils.isBlank(YIHUYISHEN_TOKEN)) {
            getToken();
        }
        List<NameValuePair> param=new ArrayList<>();
        param.add(new BasicNameValuePair("m", "getConsultantVisitHospitalList"));
        param.add(new BasicNameValuePair("platform", "YongHeHealth"));
        param.add(new BasicNameValuePair("deviceType", "YongHeHealth"));
        param.add(new BasicNameValuePair("token", YIHUYISHEN_TOKEN));
        param.add(new BasicNameValuePair("version", "1.0"));
        param.add(new BasicNameValuePair("consultantId", consultantId));
        Map<String, Object> m=HttpRequestUtils.httpPost(YIHUYISHEN_HOST, param);

        if (m != null) {
            return ResultUtils.returnSucess(m);
        }
        YIHUYISHEN_TOKEN=null;

        return ResultUtils.returnFail("网络不稳定,稍后再试");

    }


}
