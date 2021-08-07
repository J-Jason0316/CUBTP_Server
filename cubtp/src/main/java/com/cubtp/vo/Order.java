package com.cubtp.vo;

import java.util.Date;

public class Order {
    private Integer orderId;

    private String orderUserId;

    private Integer orderBookId;

    private Float orderPrice;

    private Integer orderNum;

    private String orderAddress;

    private String orderUserName;

    private String orderUserTel;

    private String orderUserRemark;

    private String orderSendStatus;

    private String orderReceiveStatus;

    private String orderStatus;

    private Date orderDate;
    
    private Book book;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Integer getOrderBookId() {
        return orderBookId;
    }

    public void setOrderBookId(Integer orderBookId) {
        this.orderBookId = orderBookId;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName == null ? null : orderUserName.trim();
    }

    public String getOrderUserTel() {
        return orderUserTel;
    }

    public void setOrderUserTel(String orderUserTel) {
        this.orderUserTel = orderUserTel == null ? null : orderUserTel.trim();
    }

    public String getOrderUserRemark() {
        return orderUserRemark;
    }

    public void setOrderUserRemark(String orderUserRemark) {
        this.orderUserRemark = orderUserRemark == null ? null : orderUserRemark.trim();
    }

    public String getOrderSendStatus() {
        return orderSendStatus;
    }

    public void setOrderSendStatus(String orderSendStatus) {
        this.orderSendStatus = orderSendStatus == null ? null : orderSendStatus.trim();
    }

    public String getOrderReceiveStatus() {
        return orderReceiveStatus;
    }

    public void setOrderReceiveStatus(String orderReceiveStatus) {
        this.orderReceiveStatus = orderReceiveStatus == null ? null : orderReceiveStatus.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}