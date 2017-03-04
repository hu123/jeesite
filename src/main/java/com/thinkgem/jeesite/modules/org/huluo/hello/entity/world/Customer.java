/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.entity.world;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 一对多增删改查Entity
 * @author 胡洛
 * @version 2017-03-04
 */
public class Customer extends DataEntity<Customer> {
	
	private static final long serialVersionUID = 1L;
	private String customerName;		// customer_name
	private List<Order> orderList = Lists.newArrayList();		// 子表列表
	
	public Customer() {
		super();
	}

	public Customer(String id){
		super(id);
	}

	@Length(min=0, max=10, message="customer_name长度必须介于 0 和 10 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}