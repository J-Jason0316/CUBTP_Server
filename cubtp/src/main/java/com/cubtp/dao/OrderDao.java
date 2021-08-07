package com.cubtp.dao;

import java.util.List;

import com.cubtp.vo.Order;

public interface OrderDao {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> getOrderByUserId(String userId);
    
    List<Order> getSoldOrderByUserId(String userId);
}