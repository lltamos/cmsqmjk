package com.quanmin.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanmin.dao.mapper.SysPermissionMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.SysPermission;
import com.quanmin.model.SysPermissionExample;
import com.quanmin.model.SysUser;
import com.quanmin.model.SysUserExample;
import com.quanmin.model.SysUserExample.Criteria;


/**
 * 自定义realm
 * 
 * @author quanmin
 *
 */
@Component
public class CustomRealm extends AuthorizingRealm {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	// 授权为认证后的登录人赋予某种功能权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 创建权限对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前用户信息
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		List<SysPermission> list = null;
		// 判断角色查询所拥有权限
		if (user.getUsername().equals("admin")) {
			// 查询所有权限
			SysPermissionExample example = new SysPermissionExample();
			list = sysPermissionMapper.selectByExample(example);

		} else {
			// 通过用户id查询对应的权限结果集
//			list = sysPermissionMapper.selectPermissionByRoleId(user.getId());
		}

		for (SysPermission sysPermission : list) {
			//获取需要授权的信息。在页面上使用shiro标签进行判断权限的字段
			info.addStringPermission(sysPermission.getPermissionName());
		}
		return info;
	}

	// 认证:登录时为了认证获取登录人的信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token是当前用户的访问令牌对象,将用户的用户名和密码放进去,令牌就形成了
		// 将controller层传过来的token转换成AuthenticationToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		// 根据用户名查找
		SysUserExample example = new SysUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<SysUser> user = sysUserMapper.selectByExample(example);
		// 判断用户名是否存在
		if (user == null||user.size()==0) {
			return null;
		}
		// 验证密码,第一个参数随意,可以放一个需要向前端传递的值,第二个就是密码了,第三个是realm的名字
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.get(0), user
				.get(0).getPassword(), this.getName());

		return info;

	}

}
