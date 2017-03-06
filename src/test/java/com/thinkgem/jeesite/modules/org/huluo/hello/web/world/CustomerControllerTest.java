package com.thinkgem.jeesite.modules.org.huluo.hello.web.world;

import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring*.xml")
@WebAppConfiguration
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;
    @Test
    public void testDelete() throws Exception {

//        customerController.delete(new Customer(), null);
    }

}