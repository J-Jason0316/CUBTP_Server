package com.cubtp.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubtp.dao.BookDao;
import com.cubtp.dao.OrderDao;
import com.cubtp.vo.Book;
import com.cubtp.vo.Order;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BookDao bookDao;
	
	
	/**
	 * 添加订单信息
	 * @param order
	 * @return boolean
	 * */
	@Transactional
	public boolean addOrder(Order order) {
		boolean flag = false;		
		Date utilDate = new Date();
		Timestamp timeStamep = new Timestamp(utilDate.getTime());
        order.setOrderDate(timeStamep);
		int status1 = orderDao.insertSelective(order);
		
		
		Book book =  bookDao.selectByPrimaryKey(order.getOrderBookId());
		int  newBookNum = book.getBookNum() - order.getOrderNum();
		book.setBookNum(newBookNum);
		int status2 = bookDao.updateByPrimaryKeySelective(book);
		if (status1 == status2) {
			flag = true;
		}
		return flag;
	}
	
	/**
	  * 根据userId获取订单信息
	 * 
	 * @param userId
	 * @return List<Order>
	 */
	
	public List<Order> getOrderByuserId(String userId) {
		return orderDao.getOrderByUserId(userId);
	}
	
	/**
	  * 根据orderId删除订单信息
	 * 
	 * @param orderId
	 * @return boolean
	 */
	public boolean deleteOrder(int orderId) {
		boolean flag = false;		
		int status = orderDao.deleteByPrimaryKey(orderId);
		
		if (status ==1) {
			flag = true;
		}
		return flag;
	}
	
	/**
	  * 确认收货
	 * 
	 * @param order
	 * @return boolean
	 */
	public boolean updateOrder(Order order) {
		boolean flag = false;		
		int status1 = orderDao.updateByPrimaryKeySelective(order);
		
		Book book =  bookDao.selectByPrimaryKey(order.getOrderBookId());
		int  newBookNum = book.getBookNum() + order.getOrderNum();
		book.setBookNum(newBookNum);
		int status2 = bookDao.updateByPrimaryKeySelective(book);
		
		if (status1 == status2) {
			flag = true;
		}
		return flag;
	}
	
	/**
	  * 取消订单
	 * 
	 * @param orderId
	 * @return boolean
	 */
	public boolean cancelOrder(Order order) {
		boolean flag = false;		
		int status = orderDao.updateByPrimaryKeySelective(order);
		
		if (status ==1) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 获取该用户所有已出售书籍的订单列表
	 * @param userId
	 * @return list
	 * 
	 * */
	public List<Order> getSoldOrderByUserId(String userId) {
		return orderDao.getSoldOrderByUserId(userId);
	}
}
