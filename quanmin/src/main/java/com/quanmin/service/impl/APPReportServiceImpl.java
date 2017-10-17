package com.quanmin.service.impl;

import com.quanmin.call.UserReportInfo;
import com.quanmin.call.UserReportListInfo;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.UserReportMapper;
import com.quanmin.model.SysUser;
import com.quanmin.model.UserReport;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPReportService;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SerializeUtil;
import com.quanmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@Service
public class APPReportServiceImpl implements APPReportService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserReportMapper userReportMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public ResultUtils showReportListByUserId(SearchCondition condition) {
        ResultUtils result = new ResultUtils();
        SysUser sysUser = null;
        // 获取用户信息
        if (!StringUtil.isEmpty(condition.getUserId())) {
            sysUser = sysUserMapper.selectByPrimaryKey(Integer.parseInt(condition.getUserId()));
        }
        if (null != sysUser) {
//            请求数据
            List<HashMap<String, Object>> list = UserReportListInfo.getUserReportListInfoByPhoneOrIdNo(sysUser, "1", null);

            if (null != list && list.size() > 0) {
                result.setMsg("查询成功");
                result.setResultCode("200");
                result.setSuccess(true);
                result.setValue(list);
                return result;
            }
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils showReportByUserId(SearchCondition condition, String hraid) {
        ResultUtils result = new ResultUtils();
        HashMap<String, Object> map = new HashMap<>();
        SysUser sysUser = null;
        // 获取用户信息
        if (!StringUtil.isEmpty(condition.getUserId())) {
            sysUser = sysUserMapper.selectByPrimaryKey(Integer.parseInt(condition.getUserId()));
        }

        if (null != sysUser) {
            List<HashMap<String, Object>> list = UserReportInfo.getUserReportInfoByPhoneOrIdNo(sysUser, "1", hraid);
            if (null != list && list.size() > 0) {
                Jedis jedis = jedisPool.getResource();
                UserReport jedisReportAndAnalyzeUrl = (UserReport) SerializeUtil.unserialize(jedis.get((null != hraid && !"".equals(hraid) ? hraid : list.get(0).get("hraid").toString()).getBytes()));
//            加入缓存

                if (null == jedisReportAndAnalyzeUrl) {
                    String generateReportUrl = this.generateReport(sysUser, null != hraid && !"".equals(hraid) ? hraid : list.get(0).get("hraid").toString(),list.get(0).get("result").toString());
                    String generateAnalyzeUrl = this.generateAnalyze(sysUser, null != hraid && !"".equals(hraid) ? hraid : list.get(0).get("hraid").toString());
                    UserReport userReport = new UserReport();
                    userReport.setAnalyzeUrl(null != generateAnalyzeUrl ? generateAnalyzeUrl : "");
                    userReport.setReportUrl(null != generateReportUrl ? generateReportUrl : "");
                    userReport.setCreateTime(new Date());
                    userReport.setHraId(null != hraid && !"".equals(hraid) ? hraid : list.get(0).get("hraid").toString());
                    userReport.setUserId(Integer.parseInt(condition.getUserId()));
                    int i = userReportMapper.insertSelective(userReport);
                    //插入redis缓存中
                    jedis.set((null != hraid && !"".equals(hraid) ? hraid : list.get(0).get("hraid").toString()).getBytes(), SerializeUtil.serialize(userReport));
                    map.put("reporturl", null != generateReportUrl ? generateReportUrl : "");
                } else {
                    map.put("reporturl", jedisReportAndAnalyzeUrl.getReportUrl());
                }
                jedis.close();
            }

            map.put("hraid", null != hraid && !"".equals(hraid) ? Integer.parseInt(hraid) : Integer.parseInt(list.get(0).get("hraid").toString()));
            map.put("userId", sysUser.getId());
            map.put("username", sysUser.getUsername());
            map.put("idCard", sysUser.getIdNo());
            map.put("phone", sysUser.getPhone());
        }


        if (null != map && map.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(map);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils showanalyzeByHraidId(SysUser user, String hraid) {
        ResultUtils result = new ResultUtils();
        HashMap<String, Object> map = new HashMap<>();
        Jedis jedis = jedisPool.getResource();
        UserReport jedisReportAndAnalyzeUrl = (UserReport) SerializeUtil.unserialize(jedis.get(hraid.getBytes()));
//        System.out.println("hraid:111:"+hraid);
//        System.out.println("report:1111"+jedisReportAndAnalyzeUrl);
//        System.out.println("report:2222"+jedisReportAndAnalyzeUrl.getAnalyzeUrl());
        if (null == jedisReportAndAnalyzeUrl) {
            String analyzeurl = this.generateAnalyze(user, hraid);
            map.put("analyzeurl", null != analyzeurl ? analyzeurl : "");
        } else {
            map.put("analyzeurl", jedisReportAndAnalyzeUrl.getAnalyzeUrl());
        }
        jedis.close();
        if (null != map && map.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(map);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    /**
     * 生成报告
     *
     * @param user
     * @param hraid
     * @return
     */
    public String generateReport(SysUser user, String hraid,String result) {

        String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.reporthtmlurl");
        String visitUrl = LoadPropertiesDataUtils.getValue("qm.visit.reporturl");
        String url = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<!doctype html><html lang=\"en\">");
        sb.append(
                "<head> <meta name=\"baidu-site-verification\" content=\"wQ4YSc4lBe\" /> <meta name=\"description\" content=\"\"> <meta name=\"keywords\" content=\"\"> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\" /> <title>查看报告</title> <link rel=\"stylesheet\" href=\"css/public.css\" type=\"text/css\"> <link rel=\"stylesheet\" href=\"css/report.css\" type=\"text/css\"> <script> var deviceWidth = document.documentElement.clientWidth; if(deviceWidth > 720) deviceWidth = 720; document.documentElement.style.fontSize = deviceWidth / 7.2 + 'px';</script><script src=\"js/jquery.js\" type=\"text/javascript\"></script> <script src=\"js/echarts.min.js\" type=\"text/javascript\"></script><script src=\"js/report.js\" type=\"text/javascript\"></script></head>");
        sb.append("<body>");
        sb.append(
                "<header> <ul class=\"ul-nav clearFloat\"> <li class=\"nav-active\"><a href=\"javascript:;\">风险图</a></li> <li><a href=\"javascript:;\">风险提示</a></li> <li><a href=\'javascript:;\'>检测结果</a></li> <li><a href=\"javascript:;\">脏器状态</a></li> </ul></header>");
        sb.append("<div class=\"empty\" style=\"margin:0.88rem 0 0 0;\"></div>");
        sb.append("<div class=\"cont-report\">");
        sb.append(
                "<section class=\"legend-section\"> <h2>风险图</h2> <div class=\"risk\" id=\"risk\"> </div> <div class=\"legend\"> <img src=\"images/legend.svg\" alt=\"图例\" width=\"100%\"> </div> </section>");
        sb.append(
                "<section> <h2>风险提示</h2> <div class=\"tips\"> <h3 class=\"clearFloat\"> 消化系统当前的状态 <span class=\"level\">[三级风险]</span> </h3> <dl class=\"clearFloat state\"> <dt></dt> <dd>肝和肠道上行感染的</dd> <dd>肝和肠道上行感染的倾向就更好打基本开始啦</dd> <dd>肝和肠道上行感染的倾向就很难</dd> <dd>肝和肠道上行感染的倾向看见不好发科技</dd> </dl> </div> </section>");
        sb.append("<section>");
        sb.append("<h2>检查结果</h2>");
        // 第一个表格
        sb.append(
                "<div class=\"sub-result\" style=\"padding:0.2rem 0 0 0\"> <h3>间质的离子分析</h3> <table class=\"lizifenxi_one\"> <thead> <tr> <th width=\"65%\">检测值</th> <th>正常值</th> </tr> </thead> <tbody> <tr> <td> <span class=\"ele\">钠</span> <span class=\"standard\">标准</span> </td> <td class=\"spectial\" rowspan=\"7\">-5~+5</td> </tr> <tr> <td> <span class=\"ele\">钾</span> <span class=\"standard\">-3</span> </td> </tr> <tr> <td> <span class=\"ele\">铝</span> <span class=\"standard\">-3</span> </td> </tr> <tr> <td> <span class=\"ele\">镁</span> <span class=\"standard\">-3</span> </td> </tr> <tr> <td> <span class=\"ele\">钙</span> <span class=\"standard\">-3</span> </td> </tr> <tr> <td> <span class=\"ele\">磷</span> <span class=\"standard\">-3</span> </td> </tr> <tr> <td> <span class=\"ele\">铁</span> <span class=\"standard\">-3</span> </td> </tr> </tbody> </table> </div>");
        // 第二个表格
        sb.append(
                "<div class=\"sub-result\"><h3>酸碱平衡</h3><table class=\"two lizifenxi_two\"><thead><tr><th width=\"65%\">检测值</th><th>正常值</th></tr></thead><tbody><tr><td><span class=\"ele two-ele\">PH</span><span class=\"zhi\">=7.44</span></td><td class=\"spectial\">7.29~7.37</td></tr><tr><td><span class=\"ele two-ele\">HCO3-</span><span class=\"zhi\">=26.84</span><span class=\"danwei\">mEq/l</span></td><td class=\"spectial\">22~26</td></tr><tr><td><span class=\"ele two-ele\">PaCO2</span><span class=\"zhi\">43.20</span><span class=\"danwei\">mmHg</span></td><td class=\"spectial\">41~51</td></tr><tr><td><span class=\"ele two-ele\">PaO2</span><span class=\"zhi\">87.30</span><span class=\"danwei\">mmHg</span></td><td class=\"spectial\">80.5~88.5</td></tr><tr><td><span class=\"ele two-ele\">[H+]</span><span class=\"zhi\">34.56</span><span class=\"danwei\">nM/L</span></td><td class=\"spectial\">42.6~51.3</td></tr><tr><td><span class=\"ele two-ele\">SBE</span><span class=\"zhi\">10.00</span><span class=\"danwei\"></span></td><td class=\"spectial\">-2~+2</td></tr><tr><td><span class=\"ele two-ele\">iSO2</span><span class=\"zhi\">98.00</span><span class=\"danwei\"></span></td><td class=\"spectial\">98%</td></tr></table></div>");
        // 第三个表格
        sb.append(
                "<div class=\"sub-result\"><h3>自由基水平</h3><table class=\"lizifenxi_three\"><thead><tr><th width=\"65%\">检测值</th><th>正常值</th></tr></thead><tbody><tr><td><span class=\"ele\">间质的过氧亚硝酸自由基</span><span class=\"standard\">0</span></td><td class=\"spectial\" rowspan=\"7\"><=(+10)</td></tr><tr><td><span class=\"ele\">间质的小分子自由基</span><span class=\"standard\">0</span></td></tr><tr><td><span class=\"ele\">间质的过氧化氢自由基</span><span class=\"standard\">10</span></td></tr><tr><td><span class=\"ele\">间质的超氧阴离子自由基</span><span class=\"standard\">10</span></td></tr><tr><td><span class=\"ele\">间质的羟自由基</span><span class=\"standard\">0</span></td></tr></tbody></table></div>");
        // 第四个表格
        sb.append(
                "<div class=\"sub-result\"><h3>神经递质</h3><table class=\"lizifenxi_four\"><thead><tr><th width=\"65%\">检测值</th><th>正常值</th></tr></thead><tbody><tr><td><span class=\"ele\">五羟色胺</span><span class=\"standard\">0</span></td><td class=\"spectial\" rowspan=\"7\" style=\"background: #fff;\">-10~+10</td></tr><tr><td><span class=\"ele\">多巴胺</span><span class=\"standard\">0</span></td></tr><tr><td><span class=\"ele\">儿茶酚胺</span><span class=\"standard\">10</span></td></tr><tr><td><span class=\"ele\">乙酰胆碱</span><span class=\"standard\">0</span></td></tr></tbody></table></div>");
        // 第五个表格
        sb.append(
                "<div class=\"sub-result\"><h3>生化相对指标</h3><table class=\"lizifenxi_five\"><thead><tr><th width=\"65%\">检测值</th><th>正常值</th></tr></thead><tbody><tr><td><span class=\"ele\">间质的甘油三脂</span><span class=\"standard\">0</span></td><td class=\"spectial\" rowspan=\"7\" style=\"background:#fff\">-5~+5</td></tr><tr><td><span class=\"ele\">谷草转氨酶<br>&nbsp;&nbsp;&nbsp;AST/谷丙转氨酶ALT</span><span class=\"standard\">0</span></td></tr><tr><td><span class=\"ele\">碱性磷酸酶ALP和转肽酶<br>&nbsp;&nbsp;&nbsp;GGT</span><span class=\"standard\">0</span></td></tr><tr><td><span class=\"ele\">间质的血糖</span><span class=\"standard\">0</span></td></tr><tr><td><span class=\"ele\">间质的LDL低密度脂蛋白</span><span class=\"standard\">0</span></td></tr></tbody></table></div>");
        // 第六个表格
        sb.append(
                "<div class=\"sub-result\"><h3>激素水平</h3><table class=\"lizifenxi_six\"><thead><tr><th width=\"65%\">检测值</th><th>正常值</th></tr></thead><tbody><tr><td><span class=\"ele\">间质的促甲状腺激素</span><span class=\"standard\">0</span></td><td class=\"spectial\" rowspan=\"15\">-20~+20</td></tr><tr><td><span class=\"ele\">间质的促卵泡激素</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">间质的脱氢表雄酮</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">间质的皮质醇</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">醛固酮</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">肾上腺髓质激素分泌量</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">间质的雌激素</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">胰岛素分泌量</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">甲状旁腺激素分泌量</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">甲状腺激素分泌量</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">间质的抗利尿激素</span><span class=\"standard\">=0</span></td></tr><tr><td><span class=\"ele\">间质的促肾上腺皮质激素</span><span class=\"standard\">=0</span></td></tr></tbody></table></div>");

        sb.append("</section>");
        sb.append("<section>");
        sb.append("<h2>各脏器生物活性状态</h2>");
        // 第一个表格
        sb.append(
                "<div class=\"sub-result\"><h3>呼吸系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_huxi\"><tbody><tr><td><span class=\"ele\">气管附近</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">支气管区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左肺上叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左肺下叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右肺上叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右肺中叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右肺下叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">胸部左侧区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">胸部右侧区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第二个表格
        sb.append(
                "<div class=\"sub-result\"><h3>耳鼻喉&nbsp;参考值-20~+20</h3><table class=\"zangQi_erbihou\"><tbody><tr><td><span class=\"ele\">左眼和泪腺区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右眼和泪腺区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左上颌窦区</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右上颌窦区</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧鼻前庭和固有鼻腔区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧鼻前庭和固有鼻腔区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左唾液腺</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右唾液腺</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左耳区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右耳区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第三个表格
        sb.append(
                "<div class=\"sub-result\"><h3>消化系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_xihua\"><tbody><tr><td><span class=\"ele\">食道上端</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">食道下端</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">胃区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">十二指肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">小肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">盲肠和阑尾区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">升结肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">结肠肝区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">结肠脾区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">降结肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">乙状结肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">直肠区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">肝左叶及胆管区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">肝右页</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">胆囊区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">胰腺区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第四个表格
        sb.append(
                "<div class=\"sub-result\"><h3>神经系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_shenjing\"><tbody><tr><td><span class=\"ele\">右侧额叶皮层</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧额叶皮层</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧颞叶</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧颞叶</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">垂体区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">下丘脑区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">丘脑</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左杏仁体</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右杏仁体</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧边缘系统</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧边缘系统(海马)</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧边缘系统(海马)</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧颅内血管</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧颅内血管</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第五个表格
        sb.append(
                "<div class=\"sub-result\"><h3>内分泌系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_neifenmi\"><tbody><tr><td><span class=\"ele\">左侧肾上腺髓质</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧肾上腺髓质</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">甲状腺区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">甲状腺左叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">甲状腺右叶区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左侧颈部区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右侧颈部区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第六个表格
        sb.append(
                "<div class=\"sub-result\"><h3>免疫系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_mianyi\"><tbody><tr><td><span class=\"ele\">胸腺</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">脾脏区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第七个表格
        sb.append(
                "<div class=\"sub-result\"><h3>泌尿生殖系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_miniao\"><tbody><tr><td><span class=\"ele\">左肾及输尿管区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右肾及输尿管区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">膀胱区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">前列腺/子宫区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左睾丸/卵巢区域</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第八个表格
        sb.append(
                "<div class=\"sub-result\"><h3>心血管系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_xinxuguan\"><tbody><tr><td><span class=\"ele\">左横膈膜神经区</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右横膈膜神经区</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左膝区域(腿部血管)</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右膝区域(腿部血管)</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左大腿神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右大腿神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左小腿神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右小腿神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左手神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右手神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左上臂神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右上臂神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左前臂神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右前臂神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左脚神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左脚神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右脚神经血管束</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">心脏区域</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左颈动脉</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右颈动脉</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">上腔静脉</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">下腔静脉</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左前庭压力受感器</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右前庭压力受感器</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">主动脉</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">冠状血管</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">心肺循环</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">门脉循环</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">心肌</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">左心室</span><span class=\"standard\">[-16]</span></td></tr><tr><td><span class=\"ele\">右心室</span><span class=\"standard\">[-16]</span></td></tr></tbody></table></div>");
        // 第九个表格
        sb.append(
                "<div class=\"sub-result\"><h3>骨骼系统&nbsp;参考值-20~+20</h3><table class=\"zangQi_guge\"><tbody><tr><td class=\"gu-td\"><span class=\"ele\">C1</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">Th9</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C2</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">Th10</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C3</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">Th11</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C4</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">Th12</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C5</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">L1</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C6</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">L2</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C7</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">L3</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">C8</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">L4</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th1</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">L5</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th2</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">S1</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th3</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">S2</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th4</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">S3</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th5</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">S4</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th6</span><span class=\"standard\">[-16]</span></td><td><span class=\"ele\">S5</span><span class=\"standard\">[-16]</span></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th7</span><span class=\"standard\">[-16]</span></td><td></td></tr><tr><td class=\"gu-td\"><span class=\"ele\">Th8</span><span class=\"standard\">[-16]</span></td><td></td></tr></tbody></table></div>");
        sb.append("</section>");
        sb.append("</div>");
        sb.append("</body>");
        if (null != hraid && !"".equals(hraid)) {
            hraid = hraid;
        } else {
            hraid = "";
        }
        sb.append("<script>chuanCan(\'" + user.getPhone() + "\',\'" + user.getIdNo() + "\',\'0\',\'" + hraid + "\',"+"\'"+result+"\'"
                + ");</script>");
        sb.append("</html>");
        // 写入文件
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "");
            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            url = basePath + fileName + ".html";
            FileOutputStream fop = new FileOutputStream(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = sb.toString().getBytes("UTF-8");
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            return visitUrl + fileName + ".html";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 生成建议
     *
     * @param user
     * @param hraid
     * @return
     */
    public String generateAnalyze(SysUser user, String hraid) {
        String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.reporthtmlurl");
        String visitUrl = LoadPropertiesDataUtils.getValue("qm.visit.reporturl");
        String url = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<!doctype html><html lang=\"en\">");
        sb.append(
                "<head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\" /><title>分析及建议</title><link rel=\"stylesheet\" href=\"css/public.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"css/analyze.css\" type=\"text/css\"><script>    var deviceWidth = document.documentElement.clientWidth;    if(deviceWidth > 720)      deviceWidth = 720;    document.documentElement.style.fontSize = deviceWidth / 7.2 + 'px';</script><script src=\"js/jquery.js\" type=\"text/javascript\"></script><script src=\"js/analyze.js\" type=\"text/javascript\"></script><script src=\"js/loading.js\" type=\"text/javascript\"></script></head>");
        sb.append("<body>");
        sb.append(
                "<header><ul class=\"ul-nav clearFloat\"><li class=\"nav-active\"><a href=\"javascript:;\">人体成分</a></li><li><a href=\"javascript:;\">饮食建议</a></li><li><a href=\"javascript:;\">微营养</a></li><li><a href=\"javascript:;\">烹调方法</a></li><li><a href=\"javascript:;\">推荐食物</a></li></ul></header>");
        sb.append("<div class=\"empty\" style=\"margin:0.88rem 0 0 0;\"></div>");
        sb.append("<div class=\"cont\">");
        // 人体成分
        sb.append(
                "<section id=\"person\"><h2>人体成分分析及建议</h2><p class=\"bmi\">BMI:<span>26.76</span></p><p class=\"weight\">平均体重:<span>60.97kg</span></p><p class=\"bmi\">瘦重(去脂重)偏差:<span>30%</span></p><p class=\"bmi\">脂肪重量:<span>20%</span></p><p class=\"bmi\">建议每日总卡路里:<span>2468</span></p><p class=\"bmi\">建议低热量饮食:<span>电话费of远红外的看法蒋宏伟四方宏伟IE哦IE有人佛问哦哦诶哦IE灰鸭绒i</span></p></section>");
        // 饮食建议
        sb.append(
                "<section id=\"yinShi\"><h2>饮食建议</h2><div class=\"sub_div\"><dl class=\"digest\"><dt>【消化系统】</dt><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd></dl><dl class=\"digest\"><dt>【消化系统】</dt><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd></dl><dl class=\"digest\"><dt>【消化系统】</dt><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd><dd>1.甲肝</dd></dl></div></section>");
        // 微营养
        sb.append(
                "<section id=\"weiYingYang\"><h2>微营养</h2><div class=\"sub_div\"><dl class=\"digest\"><dt>【维生素】</dt><dd>1.安化工I偶尔无I肉否偶尔红9迿I啊I偶尔红欧舒丹回复</dd></dl><dl class=\"digest\"><dt>【微量元素】</dt><dd>1.安化工I偶尔无I肉否偶尔红9迿I啊I偶尔红欧舒丹回复</dd></dl><dl class=\"digest\"><dt>【中草药治疗】</dt><dd>1.甲肝会不会</dd></dl></div></section>");
        // 烹调方法
        sb.append(
                "<section id=\"peng\"><h2>烹调方法</h2><div class=\"sub_div\"><dl class=\"digest\"><dt>【消化系统】</dt><dd>1.GS-E10477 人(Human)抗α干扰素抗体(IFNα-Ab)ELISA试剂盒 GS-E10478 人(Human)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl></div></section>");
        // 推荐食物
        sb.append(
                "<section id=\"tuijian\"><h2>推荐的食物</h2><div class=\"sub_div\"><dl class=\"digest\"><dt>【蔬菜】</dt><dd>1.GS-E10477 人(Human)抗α干扰素抗体(IFNα-Ab)ELISA试剂</dd></dl><dl class=\"digest\"><dt>【动物蛋白质】</dt><dd>1.GS-E10477 人(HumaA试剂盒 GS-E10478 人(Human)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【乳制品】</dt><dd>1.GS-E10477 人(Human)抗α干扰素抗体)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【碳水化合物】</dt><dd>1.GS-E10477 人(Human)抗α干扰素抗体(IFNα-Ab)ELISA试剂盒 GS-E10478 人1-70-Ab)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【饮料】</dt><dd>1.GS-E10477 人(Human)抗α干扰素抗</dd></dl><dl class=\"digest\"><dt>【水果】</dt><dd>1.GS-E10477 人man)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【草药】</dt><dd>1.GS-E10477 人(Human8 人(Human)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【油】</dt><dd>1.GS-E10477 人(Human8 人(Human)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl><dl class=\"digest\"><dt>【其他推荐】</dt><dd>1.GS-E10477 人(Human8 人(Human)抗Sc1-70抗体(Sc1-70-Ab)ELISA试剂盒</dd></dl></div></section>");
        sb.append("</div></body>");

        if (null != hraid && !"".equals(hraid)) {
            hraid = hraid;
        } else {
            hraid = "";
        }

        sb.append("<script>  analyzeFn(\'" + user.getPhone() + "\',\'" + user.getIdNo() + "\',\'" + hraid
                + "\');</script>");
        sb.append("</html>");
        // 写入文件
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "");
            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            url = basePath + fileName + ".html";
            FileOutputStream fop = new FileOutputStream(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = sb.toString().getBytes("UTF-8");
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            return visitUrl + fileName + ".html";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
