/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.service.world;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Customer;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CustomerDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Order;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.OrderDao;

/**
 * 一对多增删改查Service
 * @author 胡洛
 * @version 2017-03-04
 */
@Service
@Transactional(readOnly = true)
public class CustomerService extends CrudService<CustomerDao, Customer> {

	@Autowired
	private OrderDao orderDao;
	
	public Customer get(String id) {
		Customer customer = super.get(id);
		customer.setOrderList(orderDao.findList(new Order(customer)));
		return customer;
	}
	
	public List<Customer> findList(Customer customer) {
		return super.findList(customer);
	}
	
	public Page<Customer> findPage(Page<Customer> page, Customer customer) {
		return super.findPage(page, customer);
	}
	
	@Transactional(readOnly = false)
	public void save(Customer customer) {
		super.save(customer);
		for (Order order : customer.getOrderList()){
			if (order.getId() == null){
				continue;
			}
			if (Order.DEL_FLAG_NORMAL.equals(order.getDelFlag())){
				if (StringUtils.isBlank(order.getId())){
					order.setCustomerId(customer);
					order.preInsert();
					orderDao.insert(order);
				}else{
					order.preUpdate();
					orderDao.update(order);
				}
			}else{
				orderDao.delete(order);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) {
		super.delete(customer);
		orderDao.delete(new Order(customer));
	}
	
}