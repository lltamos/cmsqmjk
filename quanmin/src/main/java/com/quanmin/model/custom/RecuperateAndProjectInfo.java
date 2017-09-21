package com.quanmin.model.custom;

import java.math.BigDecimal;

/**
 * 项目和调理方案
 * @author yang
 *
 */
public class RecuperateAndProjectInfo {
	private Integer id;

	private String name;

	private BigDecimal price;

	private String info;

	private String device;

	private String attendingFunction;

	private String ban;

	private String coverUrl;

	private String type;

	private Integer times;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAttendingFunction() {
		return attendingFunction;
	}

	public void setAttendingFunction(String attendingFunction) {
		this.attendingFunction = attendingFunction;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
	
}
