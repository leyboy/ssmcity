package com.ley.city.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ley.city.dao.CityDao;
import com.ley.city.entity.City;
import com.ley.city.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	CityDao cityDao;
	

	@Override
	public boolean addCity(City city) {
		// TODO Auto-generated method stub
		return cityDao.insertCity(city);
	}

	@Override
	public boolean updateCity(City city) {
		// TODO Auto-generated method stub
		return cityDao.updateCityById(city);
	}

	@Override
	public boolean removeCity(int cityId) {
		// TODO Auto-generated method stub
		return cityDao.deleteCityById(cityId);
	}

	@Override
	public City getCityById(int cityId) {
		// TODO Auto-generated method stub
		return cityDao.getCityById(cityId);
	}

	@Override
	public List<City> listAllCitiesByPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		City condition = new City();
		condition.setName("");
		return cityDao.listCitiesByConditionAndPage(condition, pageNum, pageSize);
	}

	@Override
	public List<City> listCitiesByConditionAndPage(City condition, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return cityDao.listCitiesByConditionAndPage(condition, pageNum, pageSize);
	}

}
