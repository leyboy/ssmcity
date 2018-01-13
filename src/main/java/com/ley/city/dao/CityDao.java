package com.ley.city.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ley.city.entity.City;

/**
 * city dao
 **/
@Repository
public interface CityDao {

	/**
	 * Gets city by city id
	 **/
	City getCityById(Integer cityId);

	/**
	 * Inserts city
	 **/
	boolean insertCity(City city);

	/**
	 * Deletes city and when delete city only make
	 * {@link City#setDelete(boolean)} to true.
	 **/
	boolean deleteCityById(Integer cityId);

	/**
	 * Updates city
	 **/
	boolean updateCityById(City city);

	/**
	 * Lists cities by condition and page
	 **/
	List<City> listCitiesByConditionAndPage(@Param("condition") City condition, @Param("pageNum") int pageNum,
			@Param("pageSize") int pageSize);
}
