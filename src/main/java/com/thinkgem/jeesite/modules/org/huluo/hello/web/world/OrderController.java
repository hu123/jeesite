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
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Order;
import com.thinkgem.jeesite.modules.org.huluo.hello.service.world.OrderService;

/**
 * 一对多增删改查Controller
 * @author 胡洛
 * @version 2017-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hello/world/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public Order get(@RequestParam(required=false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderService.get(id);
		}
		if (entity == null){
			entity = new Order();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Order> page = orderService.findPage(new Page<Order>(request, response), order); 
		model.addAttribute("page", page);
		return "huluo/hello/world/orderList";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "huluo/hello/world/orderForm";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "save")
	public String save(Order order, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, order)){
			return form(order, model);
		}
		orderService.save(order);
		addMessage(redirectAttributes, "保存一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/order/?repage";
	}
	
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.delete(order);
		addMessage(redirectAttributes, "删除一对多增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/hello/world/order/?repage";
	}

}