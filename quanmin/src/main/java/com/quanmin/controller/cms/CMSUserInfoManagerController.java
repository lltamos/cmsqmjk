package com.quanmin.controller.cms;

/**
 * Created by yang on 2017/9/5.
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.call.UserReportPDFInfo;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.UserCountInfo;
import com.quanmin.service.CMSUserService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
@RestController
@RequestMapping("/cms/1/")
public class CMSUserInfoManagerController {

    //	@Autowired
    //	private CMSUserInfoManagerService userInfoManagerService;

    @Autowired
    private CMSUserService userService;

    /**
     * 查询所有用户信息列表
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/userlist")
    @ResponseBody
    public String userListBySearchCondition(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse, SearchCondition condition) {
        ResultUtils resultUtils = new ResultUtils();
        TreeMap<String, Object> map = new TreeMap<>();


        PageHelper.startPage(condition.getPage(), condition.getSize(), true);
        List<SysUser> list = userService.getUserListBySearchCondition(condition);

        if (null != list && list.size() > 0) {
            map.put("list", list);
            resultUtils.setMsg("查询成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
            resultUtils.setValue(map);
            resultUtils.setCount(((Page) list).getPages());
        } else {
            resultUtils.setMsg("查询失败");
            resultUtils.setResultCode("500");
            resultUtils.setSuccess(false);
        }
        return JsonUtils.objectToJson(resultUtils);
    }

    /**
     * 根据用户id查询用户信息
     */
    @RequestMapping("/userdetailinfo")
    @ResponseBody
    public String userDetailInfoById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                     String id) {
        ResultUtils resultUtils = new ResultUtils();
        TreeMap<String, Object> map = new TreeMap<>();
        // 查询用户详细信息
        SysUser user = userService.getUserDetailInfoById(id);
        // 查询用户id查询家庭成员
        List<FamilyInfo> list = userService.getUserFamilyDetailInfoByUserId(id);

        List<Map<String, String>> userReportPDFList = UserReportPDFInfo.getUserReportPDFInfoByPhoneAndIdNo(user.getPhone(), user.getIdNo());


        /**
         * 查询报告信息
         */
        // TODO
        if (null != list && list.size() > 0) {
            map.put("familylist", list);
        } else {
            map.put("familylist", "");
        }
        if (null != userReportPDFList && userReportPDFList.size() > 0) {
            map.put("reportlist", userReportPDFList);
        } else {
            map.put("reportlist", "");
        }
        map.put("userinfo", user);

        resultUtils.setMsg("查询成功");
        resultUtils.setResultCode("200");
        resultUtils.setSuccess(true);
        resultUtils.setValue(map);

        return JsonUtils.objectToJson(resultUtils);
    }


    /**
     * 校验用户名称
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param username
     * @return
     */
    @RequestMapping("/checkusername")
    @ResponseBody
    public ResultUtils checkUsernameByUserName(HttpServletRequest httpServletRequest,
                                               HttpServletResponse httpServletResponse, String username) {

        ResultUtils resultUtils = new ResultUtils();

        Integer count = userService.checkUsernameByUserName(username);
        if (null != count && count == 0) {
            resultUtils.setMsg("成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
        } else {
            resultUtils.setMsg("失败");
            resultUtils.setResultCode("500");
            resultUtils.setSuccess(false);
        }
        return resultUtils;
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param userId
     * @return
     */
    @PostMapping("/deleteuser")
    @ResponseBody
    public ResultUtils deleteUserByUserId(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse, Integer userId) {

        Integer result = userService.deleteUserByUserId(userId);
        return result > 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();
    }

    /**
     * 获取日周月用户总数
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/addusercount")
    @ResponseBody
    public ResultUtils addUserCount(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        List<UserCountInfo> result = userService.addUserCount();
        return null != result && result.size() > 0 ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

    /**
     * 获取日周月用户总数图表
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param data                日期数据
     * @param type                1,按时，2按天，3按月，4按年
     * @return
     */
    @PostMapping("/addusercountmap")
    @ResponseBody
    public ResultUtils addUserCountMap(HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse, String data, Integer type) {
        return userService.addUserStatisticsMap(data, type);

    }

    /**
     * 活跃用户统计
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/activeusercount")
    @ResponseBody
    public ResultUtils activeUserCount(HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) {
        List<UserCountInfo> result = userService.activeUserCount();
        return null != result && result.size() > 0 ? ResultUtils.returnSucess(result) : ResultUtils.returnFail();
    }

    /**
     * 获取活跃用户总数图表
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param data                日期数据
     * @param type                1,按时，2按天，3按月，4按年
     * @return
     */
    @PostMapping("/activeusercountmap")
    @ResponseBody
    public ResultUtils activeUserCountMap(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse, String data, Integer type) {
        return userService.activeUserCountMap(data, type);

    }
}
