package com.cubtp.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cubtp.vo.User;

@Service
public class GetCookie {
	/**
	 *  判断cookie 里面是否存在userInfo  
	 *	@param   request
	 */
	public Cookie isExist(HttpServletRequest req, String c_name) {
		
		Cookie cookie =null;
		Cookie[] cs = req.getCookies();
		System.out.println(cs.length);
		for (Cookie c : cs) {
			if (c.getName().equals(c_name)) {
				cookie = c;
			}
		}
		
		return cookie;
	}

	/**
	 *  获取cookie 中的值 
	 *	@param   request
	 */
	public User getCookieValue(Cookie cookie) {
		return (User) JSON.parseObject(cookie.getValue(), User.class); 
	}

}
