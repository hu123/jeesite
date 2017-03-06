/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.entity.world;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * cityCountry一对多增删改查Entity
 * @author 胡洛
 * @version 2017-03-05
 */
public class Country extends DataEntity<Country> {
	
	private static final long serialVersionUID = 1L;
	private String countryName;		// country_name
	private List<City> cityList = Lists.newArrayList();		// 子表列表
	
	public Country() {
		super();
	}

	public Country(String id){
		super(id);
	}

	@Length(min=0, max=20, message="country_name长度必须介于 0 和 20 之间")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}