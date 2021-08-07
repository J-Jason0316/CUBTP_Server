package com.cubtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cubtp.dao.UserDao;
import com.cubtp.vo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 用户登录API
	 * @param user
	 * @return JSONObject
	 * 
	 * */
	public User login(User user) {

		//对密码进行加密
		String md5Password = DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes());
		//System.out.println(md5Password);
		//查询用户
		User u =  userDao.selectByPrimaryKey(user.getUserId());
		//密码比对
		if(u !=null) {
			if (u.getUserPwd().equals(md5Password)) {
				return u;
			}
		}
				
		return null;
	}
	
	/**
	  *  用户注册API
	 * @param user
	 * @return JSONObject
	 * 
	 * */
	public boolean register(User user) {
		boolean flag = false;
		//对密码进行加密
		String md5Password = DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes());
		user.setUserPwd(md5Password);
		user.setUserHeadImg("default_headImage.jpg");
		user.setUserRole(0);
		//添加用户
		int status =  userDao.insertSelective(user);
		
		if(status == 1) {			
			flag = true;
		}
				
		return flag;
	}
	
	/**
	  *  检测用户是否存在API
	 * @param userId
	 * @return JSONObject
	 * 
	 * */
	public boolean userIdCheck(String userId) {
		
		boolean flag = false;
		
		User user = userDao.selectByPrimaryKey(userId);
		//判断用户是否存在
		if (user==null ) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 *  获取用户信息
	 * @param userId
	 * @return JSONObject
	 * 
	 * */
	public User getUserInfo(String userId) {

		User u =  userDao.selectByPrimaryKey(userId);
		//密码比对
		if(u !=null) {
			return u;
		}		
		return null;
	}
	
	/**
	 * 用户信息修改
	 * 
	 * @param user
	 * @return boolean
	 * 
	 */
	public boolean updateUser(User user) {
		boolean flag = false;
		int status = userDao.updateByPrimaryKeySelective(user);
		if(status == 1) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 用户密码修改
	 * 
	 * @param userId oldPwd newPwd
	 * @return boolean
	 * 
	 */
	public boolean changePwd(String userId, String oldPwd, String newPwd) {
		boolean flag = false;
		User user = userDao.selectByPrimaryKey(userId);
		String oldMd5Password = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
		if (user.getUserPwd().equals(oldMd5Password)) {
			String newMd5Password = DigestUtils.md5DigestAsHex(newPwd.getBytes());
			user.setUserPwd(newMd5Password);
			
			int status = userDao.updateByPrimaryKeySelective(user);
			
			if (status == 1) {
				flag = true ;
			}
		} 
		return flag;
	}
	
	/**
	 * 获取所有用户
	 * 
	 * @param null
	 * @return JSONObject
	 * 
	 */
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return JSONObject
	 * 
	 */
	public boolean deleteUser(String userId) {
		boolean flag = false;
		int status = userDao.deleteByPrimaryKey(userId);
		if(status == 1) {
			flag = true;
		}
		return flag;
	}
}
