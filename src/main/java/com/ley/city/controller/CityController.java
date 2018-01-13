package com.ley.city.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ley.city.entity.City;
import com.ley.city.service.CityService;
import static com.ley.city.util.Constant.EXCEL_HEADERS;
import static com.ley.city.util.Constant.DEFAULT_PAGE_SIZE;
import com.ley.city.util.ExcelUtil;
import com.ley.city.util.FileUtil;

@Controller
public class CityController {

	private static final Logger LOGGER = Logger.getLogger(CityController.class);

	/**
	 * Save execl cities
	 **/
	private static List<City> excelCities;

	@Autowired
	@Qualifier("cityService")
	CityService cityService;

	/**
	 * select city
	 **/
	@RequestMapping(value = "city/selectCity.do")
	public String selectCity(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "pageNum", required = false) Integer pageNum, @ModelAttribute City city,
			Model model) {
		LOGGER.info(city + "--->" + pageNum);
		if (pageNum == null) {
			pageNum = 1;
		}
		City target = city;
		if (!"".equals(request.getParameter("population"))) {
			target.setPopulation(Integer.parseInt(request.getParameter("population")));
		}
		List<City> cities = cityService.listCitiesByConditionAndPage(city, pageNum, DEFAULT_PAGE_SIZE);
		excelCities = cities;
		PageInfo<City> page = new PageInfo<>(cities);
		LOGGER.info(page.toString());
		model.addAttribute("page", page);
		model.addAttribute("cities", cities);
		LOGGER.info(cities.get(0).getImagePath());
		model.addAttribute("target", target);
		session.setAttribute("queryCity", target);
		return "city/city";
	}

	@RequestMapping(value = "city/cityJson.do",method = RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> getCityJson()
    {
		List<City> cities=cityService.listCitiesByConditionAndPage(new City(), 2, 50);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("cities", cities);
        return map;
    }
	/**
	 * update city
	 * 
	 * @throws Exception
	 **/
	@RequestMapping(value = "city/updateCity.do")
	public ModelAndView updateCity(HttpServletRequest request, String flag, @ModelAttribute City city, ModelAndView mv,
			MultipartFile imageFile) throws Exception {
		if ("1".equals(flag)) {
			int cityId = Integer.parseInt(request.getParameter("cityId"));
			City target = cityService.getCityById(cityId);
			mv.addObject("city", target);
			mv.setViewName("city/updateCity");
		} else {
			FileUtil.setMultipartFile(imageFile);
			City target = city;
			target.setImagePath(FileUtil.getName());
			FileUtil.upload(request);
			cityService.updateCity(target);
			mv.setViewName("redirect:/city/selectCity.do?population=0");
		}
		return mv;
	}

	/**
	 * add city
	 * 
	 * @throws Exception
	 **/
	@RequestMapping(value = "city/addCity.do")
	public ModelAndView addCity(HttpServletRequest request, String flag, @ModelAttribute City city, ModelAndView mv,
			MultipartFile imageFile) throws Exception {
		if ("1".equals(flag)) {
			mv.setViewName("city/addCity");
		} else {
			FileUtil.setMultipartFile(imageFile);
			City target = city;
			target.setImagePath(FileUtil.getFileName());
			FileUtil.upload(request);
			cityService.addCity(city);
			mv.setViewName("redirect:/city/selectCity.do?population=0");
		}
		return mv;
	}

	/**
	 * Removes city
	 **/
	@RequestMapping(value = "city/removeCity.do")
	public ModelAndView removeCity(Integer cityId, ModelAndView mv) {
		cityService.removeCity(cityId);
		mv.setViewName("redirect:/city/selectCity.do?population=0");
		return mv;
	}

	/**
	 * @throws Exception
	 * 
	 **/
	@RequestMapping(value = "city/exportExcel.do")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExcelUtil.setCities(excelCities);
		ExcelUtil.setHeaders(EXCEL_HEADERS);
		ExcelUtil.exportExcel(request, response);
		return null;
	}
}
