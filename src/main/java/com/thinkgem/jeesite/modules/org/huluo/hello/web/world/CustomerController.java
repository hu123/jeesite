/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.web.world;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CustomerDao;
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
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Customer;
import com.thinkgem.jeesite.modules.org.huluo.hello.service.world.CustomerService;

/**
 * 一对多增删改查Controller
 * @author 胡洛
 * @version 2017-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hello/world/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerDao customerDao;

	/*
	@ModelAttribute
	public Customer get(@RequestParam(required=false) String id) {
		logger.warn("调用了");
		Customer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerService.get(id);
			logger.warn(entity.getCustomerName());
		}
		if (entity == null){
			entity = new Customer();
		}
		return null;
	}
*/
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Customer> page = customerService.findPage(new Page<Customer>(request, response), customer); 
		model.addAttribute("page", page);
		return "huluo/hello/world/customerList";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "form")
	public String form(Customer customer, Model model) {
		model.addAttribute("customer", customer);
		return "huluo/hello/world/customerForm";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "save")
	public String save(Customer customer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customer)){
			return form(customer, model);
		}
		customerService.save(customer);
		addMessage(redirectAttributes, "保存一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/customer/?repage";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
	 Customer	customer = new Customer();
		customer.setId(id);
//		customerService.delete(customer);
		customerDao.delete(customer);
		addMessage(redirectAttributes, "删除一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/customer/?repage";
	}

}