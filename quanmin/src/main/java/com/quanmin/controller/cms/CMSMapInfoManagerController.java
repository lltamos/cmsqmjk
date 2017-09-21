package com.quanmin.controller.cms;

import com.quanmin.model.SysArea;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSAreaService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.TreeMap;


/**
 *
 */
@RestController
@RequestMapping("/cms/1/")
public class CMSMapInfoManagerController {

//	@Autowired
//	private CMSUserInfoManagerService userInfoManagerService;

    @Autowired
    private CMSAreaService areaService;

    /**
     * 查询所有用户报告列表
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/arealist")
    @ResponseBody
    public String userReportListBySearchCondition(HttpServletRequest httpServletRequest,
                                                  HttpServletResponse httpServletResponse, SearchCondition condition) {
        ResultUtils resultUtils = new ResultUtils();
        TreeMap<String, Object> map = new TreeMap<>();

        List<SysArea> list = areaService.getAreaListAll(condition);


        if (null != list && list.size() > 0) {
            map.put("list", list);
            resultUtils.setMsg("查询成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
            resultUtils.setValue(map);
        } else {
            resultUtils.setMsg("查询失败");
            resultUtils.setResultCode("500");
            resultUtils.setSuccess(false);
        }
        return JsonUtils.objectToJson(resultUtils);
    }

}
