/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.service.world;

import java.util.List;

import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Country;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CountryDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.City;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CityDao;

/**
 * cityCountry一对多增删改查Service
 * @author 胡洛
 * @version 2017-03-05
 */
@Service
@Transactional(readOnly = true)
public class CountryService extends CrudService<CountryDao, Country> {

	@Autowired
	private CityDao cityDao;
	
	public Country get(String id) {
		Country country = super.get(id);
		logger.warn("拿到的country是"+ country);
		List<City> list = cityDao.findList(new City(country));
		System.out.println("拿到的与country对象关联的集合是" + list);
		country.setCityList(list);
		return country;
	}
	
	public List<Country> findList(Country country) {
		return super.findList(country);
	}
	
	public Page<Country> findPage(Page<Country> page, Country country) {
		return super.findPage(page, country);
	}
	
	@Transactional(readOnly = false)
	public void save(Country country) {
		super.save(country);
		logger.warn("走到这里应该是没问题的");
		for (City city : country.getCityList()){
			if (city.getId() == null){
				continue;
			}
			if (City.DEL_FLAG_NORMAL.equals(city.getDelFlag())){
				if (StringUtils.isBlank(city.getId())){
					System.out.println("能进来么???");
					System.out.println(city);
//					country.setId("1");
					city.setCountry(country);
					city.preInsert();
					cityDao.insert(city);
				}else{
					city.preUpdate();
					cityDao.update(city);
				}
			}else{
				cityDao.delete(city);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Country country) {
		super.delete(country);
		cityDao.delete(new City(country));
	}
	
}