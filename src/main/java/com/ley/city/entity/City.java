package com.ley.city.entity;

import org.apache.ibatis.type.Alias;

@Alias("_city")
public class City {

	private Integer id; // id
	private String name; // 城市名
	private String countryCode; // 城市区号
	private String district; // 城市归属地
	private Integer population; // 人口数
	private String imagePath; // 上传城市的照片路径
	private Boolean delete; // 是否删除city

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String name, String countryCode, String district, Integer population, String imagePath) {
		super();
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryCode=" + countryCode + ", district=" + district
				+ ", population=" + population + ", imagePath=" + imagePath + ", delete=" + delete + "]";
	}

}
