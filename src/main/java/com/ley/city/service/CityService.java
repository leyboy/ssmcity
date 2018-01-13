package com.ley.city.service;

import java.util.List;

import com.ley.city.entity.City;

/**
 * city service
 **/
public interface CityService {

	/**
	 * Adds city
	 * 
	 * @return Returns <code>true</code> when add success
	 **/
	boolean addCity(City city);

	/**
	 * Updates city
	 * 
	 * @return Returns <code>true</code> when update success
	 **/
	boolean updateCity(City city);

	/**
	 * Removes city
	 * 
	 * @return Returns <code>true</code> when remove success
	 **/
	boolean removeCity(int cityId);

	/**
	 * Gets city
	 **/
	City getCityById(int cityId);

	/**
	 * Lists all cities by page
	 * 
	 * @param pageNum
	 *            page num(页码)
	 * @param pageSize
	 *            page size(每页记录数)
	 **/
	List<City> listAllCitiesByPage(int pageNum, int pageSize);

	/**
	 * Lists cities by condition and page
	 * 
	 * @param condition
	 *            the condition contains via
	 *            {@link City#getCountryCode()},{@link City#getName()},
	 *            ,{@link City#getPopulation()}.
	 * @param pageNum
	 *            page num(页码)
	 * @param pageSize
	 *            page size(每页记录数)
	 **/
	List<City> listCitiesByConditionAndPage(City condition, int pageNum, int pageSize);
}
