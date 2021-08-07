package com.cubtp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cubtp.service.UserService;
import com.cubtp.util.GetCookie;
import com.cubtp.util.MySessionContext;
import com.cubtp.util.ReturnDataInit;
import com.cubtp.util.SetCookie;
import com.cubtp.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ReturnDataInit returnDataInit;
	@Autowired
	private SetCookie setCookie;
	@Autowired
	private GetCookie getCookie;

	/**
	 * 用户登录API
	 * 
	 * @param user
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp) {

		System.out.println(user.getUserId());

		JSONObject jsonObject = returnDataInit.initSetting();

		User u = userService.login(user);

		// 判断是否登录成功
		if (u != null) {
			// 创建Cookie
			setCookie.setCookie(u, user.isRememberMe(), req, resp);
			System.out.println("---------" + req.getSession().getId());
			u.setUserPwd(null);
			jsonObject.put("data", u);

		} else {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 用户注册API
	 * 
	 * @param user
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject register(@RequestBody User user) {

		System.out.println(user.getUserId());

		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = userService.register(user);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 检测用户是否存在API
	 * 
	 * @param userId
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/userIdCheck", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject userIdCheck(String userId) {

		System.out.println(userId);

		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = userService.userIdCheck(userId);

		// 如果flag为false，则用户存在
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getUserInfo(String userId) {
		JSONObject jsonObject = returnDataInit.initSetting();
		User user = userService.getUserInfo(userId);

		if (user != null) {
			user.setUserPwd(null);
			jsonObject.put("data", user);
		} else {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}

	/**
	 * 用户头像修改
	 * 
	 * @param file
	 * @return JSONObject
	 */
	@RequestMapping(value = "/updateHeadImage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateHeadImage(@RequestParam("file") CommonsMultipartFile file, User user,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonObject = returnDataInit.initSetting();

		System.out.println("fileName：" + file.getOriginalFilename());
		System.out.println(user.getUserId());
		System.out.println(file.getOriginalFilename());

		String path = "C:\\Users\\Jason\\Desktop\\cubtp\\src\\assets\\images\\user_head_img\\" + user.getUserId() + ".jpg";

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		user.setUserHeadImg(user.getUserId() + ".jpg");
//		user.setUserId(user.getUserId());

		boolean flag = userService.updateUser(user);
//		User u = userService.getUserInfo(user.getUserId());

		// 判断是否上传成功
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

//		setCookie.setCookie(u, user.isRememberMe(), req, resp);
		return jsonObject;
	}

	/**
	 * 用户信息修改
	 * 
	 * @param user
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateUser(@RequestBody User user) throws IOException {

		JSONObject jsonObject = returnDataInit.initSetting();

		System.out.println(user.getUserId());

		boolean flag = userService.updateUser(user);

		// 判断是否更新成功
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 用户密码修改
	 * 
	 * @param userId oldPwd newPwd
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateUser(@RequestBody Map<String, String> params) throws IOException {
		System.out.println(params);
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = userService.changePwd(params.get("userId"), params.get("oldPwd"), params.get("newPwd"));

		// 判断是否修改成功
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 用户注销
	 * 
	 * @param null
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject logout(HttpServletRequest req, HttpServletResponse resp) {

		Cookie cookie_session = getCookie.isExist(req, "JSESSIONID");
		String sessionId = cookie_session.getValue();
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession session = myc.getSession(sessionId);

		System.out.println("-------" + req.getContextPath() + "------" + session);

		if (session != null) {
			User user = (User) session.getAttribute("user");
			System.out.println("成功注销用户：" + user.getUserId() + "----" + session.getId());
			session.removeAttribute("user");
			session.setMaxInactiveInterval(0);
		}

		Cookie cookie = getCookie.isExist(req, "userId");

		if (cookie != null) {
			cookie.setMaxAge(0);// Cookie并不能根本意义上删除，只需要这样设置为0即可
			cookie.setPath("/");// 很关键，设置成跟写入cookies一样的，全路径共享Cookie
			resp.addCookie(cookie);// 重新响应
		}
		JSONObject jsonObject = returnDataInit.initSetting();

		return jsonObject;
	}

	/**
	 * 获取所有用户
	 * 
	 * @param null
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllUser() {
		JSONObject jsonObject = returnDataInit.initSetting();

		List<User> userList = userService.getAllUser();
		if (userList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", userList);

		}
		return jsonObject;
	}

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return JSONObject
	 * 
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject deleteUser(String userId) {
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = userService.deleteUser(userId);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}
}
