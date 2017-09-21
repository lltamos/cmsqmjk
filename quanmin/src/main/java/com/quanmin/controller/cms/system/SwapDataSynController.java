package com.quanmin.controller.cms.system;

import com.quanmin.model.po.ProjectPo;
import com.quanmin.model.po.RecuperatePo;
import com.quanmin.service.ICmsSwapDataSynService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by llsmp on 2017/7/20.
 * 数据同步controller
 */
@Controller
@RequestMapping("/system/swap")
public class SwapDataSynController {

    @Resource
    private ICmsSwapDataSynService swapDataSynService;

    /**
     * @param type 0  删除 1 修改
     */
    @RequestMapping("/project")
    @ResponseBody
    public ResultUtils synProject(Integer type, Integer projectId, ProjectPo project) {


        project.setId( projectId );


        return swapDataSynService.synProject( type, project );
    }


    /**
     * @param type 0  删除 1 修改
     */

    @RequestMapping("/recuperate")
    @ResponseBody
    public ResultUtils synRecuperate(Integer type, Integer recuperateId, RecuperatePo recuperate) {
        recuperate.setId( recuperateId );


        return swapDataSynService.synRecuperate( type, recuperate );
    }


}
