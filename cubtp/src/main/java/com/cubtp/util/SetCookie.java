package com.cubtp.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cubtp.vo.User;

@Service
public class SetCookie {

	public void setCookie(User user, Boolean rememberMe, HttpServletRequest req, HttpServletResponse resp) {

		if (!user.getUserId().equals("") && !user.getUserPwd().equals("")) {

			Cookie userCookie = new Cookie("userId", user.getUserId());
			Cookie sessionCookie = new Cookie("JSESSIONID", req.getSession().getId());

			// 设置Cookie的父路经
//			 userCookie.setPath(req.getContextPath() + "/");
			System.out.println(req.getContextPath());
			// 全路径共享Cookie
			userCookie.setPath("/");

			// 获取是否保存Cookie（例如：复选框的值）

			System.out.println(rememberMe);
			if (rememberMe == true) {
				// 保存Cookie的时间长度，单位为秒
				System.out.println("保存Cookie存在7天");
				userCookie.setMaxAge(7 * 24 * 60 * 60);
				sessionCookie.setMaxAge(7 * 24 * 60 * 60);
				req.getSession().setMaxInactiveInterval(7 * 24 * 60 * 60);
				req.getSession().setAttribute("user", user);
				//设置COOKIE中的SESSIONID
			} else {
				// 不保存Cookie
				req.getSession().setAttribute("user", user);
				System.out.println("不保存Cookie");
				//userCookie.setMaxAge(0);
				// userCookie.setHttpOnly(false);
			}

			// 加入Cookie到响应头
			resp.addCookie(sessionCookie);
			resp.addCookie(userCookie);

		}
	}

}
