package com.thinkgem.jeesite.modules.org.huluo.hello.service.world;

import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring*.xml")
@WebAppConfiguration
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testDelete() throws Exception {
        Customer customer = new Customer();
//        customerService.delete1();

    }

}