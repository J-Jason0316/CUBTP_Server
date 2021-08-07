package com.cubtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cubtp.service.OtherService;
import com.cubtp.util.ReturnDataInit;
import com.cubtp.vo.BookSubject;
import com.cubtp.vo.BookType;

@Controller
@RequestMapping("/other")
public class OtherController {
	@Autowired
	private ReturnDataInit returnDataInit;
	@Autowired
	private OtherService otherService;

	/**
	 * 获取所有的书籍学科
	 * 
	 * @param null
	 * @return List
	 */
	@RequestMapping(value = "/getAllBookSubject", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllBookSubject() {
		JSONObject jsonObject = returnDataInit.initSetting();
		
		List<BookSubject> subjectList  =  otherService.getAllBookSubject();
		
		if (subjectList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", subjectList);	
		}
		
		return jsonObject;
	}

	/**
	 * 获取所有的书籍类型
	 * 
	 * @param null
	 * @return List
	 */
	
	@RequestMapping(value = "/getAllBookType", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllBookType() {
		JSONObject jsonObject = returnDataInit.initSetting();
		
		List<BookType> typeList  =  otherService.getAllBookType();
		
		if (typeList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", typeList);	
		}
		
		return jsonObject;
	}

}
