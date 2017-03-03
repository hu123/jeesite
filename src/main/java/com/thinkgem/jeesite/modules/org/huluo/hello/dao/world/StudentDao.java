/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.org.huluo.hello.dao.world;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Student;

/**
 * 完成学生单表的增删改查DAO接口
 * @author 胡洛
 * @version 2017-03-03
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	
}