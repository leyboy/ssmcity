package com.ley.city.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ley.city.entity.City;
import com.ley.city.service.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class TestCityService {

	@Autowired
	@Qualifier("cityService")
	CityService cityService;
	
	@Test
	public void testGetCityById(){
		System.out.println(cityService.getCityById(1));
	}
	
	
	@Test
	public void testListAllCitiesByPage(){
		List<City> cities=cityService.listAllCitiesByPage(1, 10);
		for(City city : cities){
			System.out.println(city);
		}
	}
	
	
	@Test
	public void testListCitiesByConditionAndPage(){
		City city=new City();
		//city.setCountryCode("CHN");
		city.setName("u");
		List<City> cities=cityService.listCitiesByConditionAndPage(city, 1, 10);
		for(City city2 : cities){
			System.out.println(city2);
		}
	}
}
