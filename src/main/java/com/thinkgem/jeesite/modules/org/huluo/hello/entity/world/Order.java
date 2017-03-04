/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.entity.world;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 一对多增删改查Entity
 * @author 胡洛
 * @version 2017-03-04
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderName;		// order_name
	private Customer customerId;		// customer_id 父类
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	public Order(Customer customerId){
		this.customerId = customerId;
	}

	@Length(min=0, max=10, message="order_name长度必须介于 0 和 10 之间")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

}