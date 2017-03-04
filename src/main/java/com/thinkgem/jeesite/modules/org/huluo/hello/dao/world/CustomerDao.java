/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.dao.world;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Customer;

/**
 * 一对多增删改查DAO接口
 * @author 胡洛
 * @version 2017-03-04
 */
@MyBatisDao
public interface CustomerDao extends CrudDao<Customer> {
	
}