package com.thinkgem.jeesite.modules.org.huluo.hello.service.world;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CityDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.dao.world.CountryDao;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring*.xml")
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;
    @Test
    public void testGet() throws Exception {
        Country country = countryService.get("dad72a1568e7471ca1cf5aa17434d3a2");

        System.out.println(country.getCountryName());
        System.out.println(country.getCityList().get(0).getCityName());
    }


    @Test
    public void testFindList() throws Exception {

        Country country = new Country();
        country.setId("dad72a1568e7471ca1cf5aa17434d3a2");
        List<Country> list = countryDao.findList(country);

        System.out.println(list.size());
    }


    @Test
    public void testSave() throws Exception {

        for (int i = 0; i < 10; i++) {


        Country country = new Country();
//        country.setId(UUID.randomUUID().toString());
        country.setCountryName("german");
//        System.out.println(country.getId());
//        country.setIsNewRecord(true);
        countryService.save(country);
        }
    }

    @Test
    public void testDelete() throws Exception {
        Country country = countryDao.get("f59766b3-0310-4b31-856d-18c0c8563baa");
        countryService.delete(country);

    }

    @Test
    public void testFindPage() throws Exception {
        Country country = new Country();
        countryService.findPage(new Page<Country>(), country);

    }
}