package com.ley.city.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ley.city.dao.CityDao;
import com.ley.city.entity.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class TestCityDao {

	@Autowired
	CityDao cityDao;
	
	
	@Test
	public void testGetCityById(){
		System.out.println(cityDao.getCityById(1));
	}
	
	
	@Test
	public void testInsertCity(){
		City city=new City("天津西青", "CHN", "天津", 19000000,null);
		cityDao.insertCity(city);
	}
	
	
	@Test
	public void testDeleteCity(){
		cityDao.deleteCityById(1);
		System.out.println(cityDao.getCityById(1));
	}
	
	
	@Test
	public void testUpdateCity(){
		City city=cityDao.getCityById(4089);
		city.setName("天津南开");
		cityDao.updateCityById(city);
	}
	
	@Test
	public void testListCitiesByConditionAndPage(){
		City city=new City();
		//city.setName("u");
		city.setName("");
		System.out.println("city"+city.getName());
		city.setCountryCode("CHN");
		city.setPopulation(2000000);
		List<City> cities=cityDao.listCitiesByConditionAndPage(city, 1, 10);
		for(City condition : cities){
			System.out.println(condition);
		}
	}
}
