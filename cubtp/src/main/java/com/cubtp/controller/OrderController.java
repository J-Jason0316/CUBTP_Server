package com.cubtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cubtp.service.OrderService;
import com.cubtp.util.ReturnDataInit;
import com.cubtp.vo.Order;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private ReturnDataInit returnDataInit;
	@Autowired
	private OrderService orderService;

	/**
	 * 添加订单信息
	 * 
	 * @param order
	 * @return JSONObject
	 */

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOrder(@RequestBody Order order) {
		
		System.out.println(order.getOrderUserName());
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = orderService.addOrder(order);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}	

		return jsonObject;
	}
	
	/**
	  * 根据userId获取订单信息
	 * 
	 * @param userId
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getOrderByuserId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getOrderByuserId(String userId) {
		
		System.out.println(userId);
		JSONObject jsonObject = returnDataInit.initSetting();

		List<Order> orderList = orderService.getOrderByuserId(userId);

		if (orderList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", orderList);
		}

		return jsonObject;
	}
	
	/**
	  * 根据orderId删除订单信息
	 * 
	 * @param orderId
	 * @return JSONObject
	 */
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject deleteOrder(int orderId) {
		
		System.out.println(orderId);
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = orderService.deleteOrder(orderId);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}
	
	/**
	  * 确认收货
	 * 
	 * @param Order
	 * @return JSONObject
	 */
	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject confirmOrder(@RequestBody Order Order) {
		
		System.out.println(Order.getOrderStatus());
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = orderService.updateOrder(Order);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}
	
	/**
	  * 取消订单
	 * 
	 * @param Order
	 * @return JSONObject
	 */
	
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject cancelOrder(@RequestBody Order Order) {
		
		System.out.println(Order.getOrderStatus());
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = orderService.updateOrder(Order);

		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}
	
	/**
	 * 获取该用户所有已出售书籍的订单列表
	 * @param userId
	 * @return list
	 * 
	 * */
	@RequestMapping(value = "/getSoldOrderByUserId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getSoldOrderByUserId(String userId) {
		
		System.out.println(userId);
		JSONObject jsonObject = returnDataInit.initSetting();

		List<Order> soldOrderList = orderService.getSoldOrderByUserId(userId);

		if (soldOrderList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", soldOrderList);
		}

		return jsonObject;
	}
}
