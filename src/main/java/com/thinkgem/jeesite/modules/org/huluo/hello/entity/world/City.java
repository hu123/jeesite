/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.entity.world;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * cityCountry一对多增删改查Entity
 * @author 胡洛
 * @version 2017-03-05
 */
public class City extends DataEntity<City> {
	
	private static final long serialVersionUID = 1L;

	private String cityName;		// city_name
	private Country country;		// country_id 父类
	
	public City() {
		super();
	}

	public City(String id){
		super(id);
	}

	public City(Country country){
		this.country = country;
	}

	@Length(min=0, max=10, message="city_name长度必须介于 0 和 10 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=4, message="country_id长度必须介于 0 和 4 之间")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "城市名是:" + this.cityName;
	}
}