package com.thinkgem.jeesite.modules.org.huluo.hello.dao.world;

import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.City;
import com.thinkgem.jeesite.modules.org.huluo.hello.entity.world.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring*.xml")
@WebAppConfiguration
public class CityDaoTest {
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;

    @Test
    public void testName() throws Exception {

//        Country country = countryDao.get("ab889d0f-0777-4ecb-a40a-1e65d44101a4");

//        System.out.println(country.getId());
//        country.setCountryName("china");
//        countryDao.update(country);

//        countryDao.delete("ab889d0f-0777-4ecb-a40a-1e65d44101a4");


//        Country country = new Country();
//        country.setId(UUID.randomUUID().toString());
//        country.setCountryName("fengyeguo");
//        countryDao.insert(country);
    }


    @Test
    public void testName1() throws Exception {

//        cityDao.delete("1bbee80d-4684-45bd-8763-6812a0a732f2");

//        for(int i = 0 ; i < 10 ; i++) {
//            City city = new City();
//            city.setId(UUID.randomUUID().toString());
//            city.setCityName("taoshan" + i);
//
//            cityDao.insert(city);
//        }

        /*
        List<City> list = cityDao.findAllList();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCityName()  );
            if (null != list.get(i).getCountry()) {
                System.out.println(list.get(i).getCountry().getCountryName());
            }

        }
*/

        /*
        City city = new City();
        city.setId("0325cd41-ad74-495a-b466-cb16852d41db");

        Country country = new Country();
        country.setId("3c3c3afa-3782-4f17-9e2e-ad9b174d09b6");
        city.setCountry(country);
        List<City> list = cityDao.findList(city);
        */

        City city = cityDao.get("16accc51-3a00-4515-a835-e50d73acc7ac");

        List<City> list = cityDao.findList(city);
        System.out.println(list.get(0).getCountry().getCountryName());

    }
}