package com.quanmin.controller.cms.system;

import com.quanmin.model.SysPermission;
import com.quanmin.model.SysRole;
import com.quanmin.model.SysUser;
import com.quanmin.service.CMSUserService;
import com.quanmin.service.system.SysRoleService;
import com.quanmin.service.system.SyspermissionService;
import com.quanmin.util.EncryptUtils;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/cms/1/")
public class CMSLoginController {

    @Autowired
    private CMSUserService userService;
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SyspermissionService syspermissionService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 用户登录
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultUtils userLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 String userName, String password) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String, Object> map = new HashMap<>();
        SysUser userLogin = userService.userLogin(userName, EncryptUtils.md5(password));
        if (null != userLogin && userLogin.getDelStatus() == 0) {
            String token = "c" + EncryptUtils.md5(userLogin.getPassword() + userLogin.getId());
            map.put("userinfo", userLogin);
            SysRole sysRole = sysRoleService.selectRoleByUserId(userLogin.getId());
            if (null != sysRole) {
                List<SysPermission> list = syspermissionService.selectPermissionByRoleId(sysRole.getId());
                map.put("permissionlist", list);
                map.put("rolelist",sysRole);
                Jedis jedis = jedisPool.getResource();
                jedis.set("cmsUserToken" + token, token+","+userLogin.getId());
                jedis.expire("cmsUserToken" + token, 12000);
                jedis.close();
                System.out.println(token);
                map.put("token", token);
            }
        }

        if (null != map && map.size() > 0) {

            resultUtils.setMsg("登录成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
            resultUtils.setValue(map);
        } else {
            resultUtils.setMsg("登录失败，没有此用户");
            resultUtils.setResultCode("600");
            resultUtils.setSuccess(false);
            resultUtils.setValue("");
        }

        return resultUtils;
    }

    /**
     * 验证密码输入密码是否正确
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param password
     * @return
     */
    @RequestMapping("/checkpassword")
    @ResponseBody
    public ResultUtils checkPassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                String password, String id) {
        ResultUtils resultUtils = new ResultUtils();
        Integer res = userService.checkPassword(password, id);
        if (null != res) {
            resultUtils.setMsg("旧密码输入错误");
            resultUtils.setResultCode("601");
            resultUtils.setSuccess(false);
        } else {
            resultUtils.setMsg("密码成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
        }
        return resultUtils;
    }

    /**
     * 修改密码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param newpassword
     * @param userId
     * @return
     */
    @RequestMapping("/updatepassword")
    @ResponseBody
    public ResultUtils updatePassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 String newpassword, String userId) {
        ResultUtils resultUtils = new ResultUtils();
        Integer i = userService.updatePassword(EncryptUtils.md5(newpassword), userId);
        if (i < 1) {
            resultUtils.setMsg("密码修改失败");
            resultUtils.setResultCode("601");
            resultUtils.setSuccess(false);
        } else {
            resultUtils.setMsg("密码修改成功");
            resultUtils.setResultCode("200");
            resultUtils.setSuccess(true);
        }
        return resultUtils;
    }

    /**
     * 退出登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/loginout")
    @ResponseBody
    public ResultUtils loginout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String token) {
        Jedis jedis = jedisPool.getResource();
        jedis.del("cmsUserToken" + token);
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setMsg("成功");
        resultUtils.setResultCode("200");
        resultUtils.setSuccess(true);
        jedis.close();
        return resultUtils;
    }


}
