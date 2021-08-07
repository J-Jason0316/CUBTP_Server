package com.cubtp.util;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class ReturnDataInit {
	
	//初始化一个JSON对象
	public JSONObject initSetting() {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
	        jsonObject.put("data", null);
	        jsonObject.put("msg", "success");
	        return jsonObject;
		}

}
