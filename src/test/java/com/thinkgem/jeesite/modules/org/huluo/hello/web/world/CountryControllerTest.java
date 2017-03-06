package com.thinkgem.jeesite.modules.org.huluo.hello.web.world;

import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring*.xml")
@WebAppConfiguration
public class CountryControllerTest {

    @Autowired
    private CountryController countryController;
    @Test
    public void testGet() throws Exception {

        Country country = countryController.get("dad72a1568e7471ca1cf5aa17434d3a2");
        System.out.println(country.getCityList().get(0).getCityName());
    }


    @Test
    public void testSave() throws Exception {


    }
}