/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.web.world;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Country;
import com.thinkgem.jeesite.modules.org.huluo.hello.service.world.CountryService;

/**
 * cityCountry一对多增删改查Controller
 * @author 胡洛
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/hello/world/country")
public class CountryController extends BaseController {

	@Autowired
	private CountryService countryService;
	
	@ModelAttribute
	public Country get(@RequestParam(required=false) String id) {
		logger.warn("get方法得到调用");
		Country entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = countryService.get(id);
		}
		if (entity == null){
			entity = new Country();
		}
		return entity;
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Country country, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.warn("list方法得到调用");
		Page<Country> page = countryService.findPage(new Page<Country>(request, response), country);
		model.addAttribute("page", page);
		return "huluo/hello/world/countryList";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "form")
	public String form(Country country, Model model) {
		logger.warn("form方法得到调用");
		model.addAttribute("country", country);
		return "huluo/hello/world/countryForm";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "save")
	public String save(Country country, Model model, RedirectAttributes redirectAttributes) {
		logger.warn("save方法得到调用");
		if (!beanValidator(model, country)){
			return form(country, model);
		}
		countryService.save(country);
		addMessage(redirectAttributes, "保存cityCountry一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/country/?repage";
	}
	
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "delete")
	public String delete(Country country, RedirectAttributes redirectAttributes) {
		logger.warn("delete方法得到调用");
		countryService.delete(country);
		addMessage(redirectAttributes, "删除cityCountry一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/country/?repage";
	}

}