package com.quanmin.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转
 * @author heasy
 *
 */
@Controller
public class PageController {
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/tologin")
	public String toLogin(){
		return "/login";
	}
	/**
	 * 每个页面的跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}

	@RequestMapping("/test")
	@ResponseBody
	public String showPage(){
		return "aaaaaaaa";
	}
	
}
