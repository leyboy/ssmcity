package com.ley.city.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.http.MediaType;

import com.ley.city.entity.City;

/**
 * Excel util
 **/
public final class ExcelUtil {

	/**
	 * 导出的city数据
	 **/
	private static List<City> cities;

	/**
	 * default column width
	 **/
	private static final int DEFAULT_COLUMN_WIDTH = 20;

	/**
	 * default font name
	 **/
	private static final String DEFAULT_FONT_NAME = "微软雅黑";

	private static final Logger LOGGER = Logger.getLogger(ExcelUtil.class);

	/**
	 * excel headers
	 **/
	private static String[] headers;

	public static void setCities(List<City> cities) {
		ExcelUtil.cities = cities;
	}

	public static void setHeaders(String[] headers) {
		ExcelUtil.headers = headers;
	}

	/**
	 * Exports excel
	 * 
	 * @throws Exception
	 **/
	public static void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Objects.nonNull(cities);
		Objects.nonNull(headers);
		// 声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 声明一个表格
		HSSFSheet sheet = workbook.createSheet();
		// 设置表格默认列宽度
		sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);

		HSSFRow row = sheet.createRow(0);
		// 设置表头属性值
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		//遍历数据,设置excel数据内容
		Iterator<City> cityIterator = cities.iterator();
		int index = 0;
		while (cityIterator.hasNext()) {
			index++;
			row = sheet.createRow(index);
			City city = cityIterator.next();
			
			
			Field[] fields = city.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				LOGGER.info(methodName);
				try {
					Class<?> clazz = city.getClass();
					Method getMethod = clazz.getMethod(methodName);
					Object value = getMethod.invoke(city);
					String textValue = null; //文本转换

					// 如果是时间类型
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						textValue = sdf.format(date);
					} else {
						// 其他数据类型当做字符串
						if (value == null) {
							textValue = "";
						} else {
							textValue = value.toString();
						}
					}
					
					//设置excel每行的数据内容
					HSSFRichTextString richString = new HSSFRichTextString(textValue);
					HSSFFont font = workbook.createFont();
					font.setColor(HSSFColor.BLACK.index);// 定义Excel数据颜色
					font.setFontName(DEFAULT_FONT_NAME);
					richString.applyFont(font);
					cell.setCellValue(richString);
				} finally {
					// TODO: handle finally clause
				}
			}
		}
		try {
			// response octet-stream
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			response.setHeader("Content-disposition", "attachment;filename=cityList.xlsx");// 默认Excel名称
			response.flushBuffer();
			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} finally {
			// TODO: handle finally clause
			// close output stream
			response.getOutputStream().close();
		}
	}
}
