package com.cubtp.vo;

import java.util.Date;

public class Book {
	private Integer bookId;

	private String bookName;

	private String bookCover;

	private String bookAuthor;

	private String bookType;

	private String bookSubject;

	private String bookPress;

	private String bookDesc;
	
	private Float bookOldPrice;

	private Float bookPrice;

	private Integer bookNum;

	private String bookDegree;

	private String bookSellerId;

	private Integer bookStatus;
	
	private String bookNotes;

	private Date bookUploadDate;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName == null ? null : bookName.trim();
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover == null ? null : bookCover.trim();
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType == null ? null : bookType.trim();
	}

	public String getBookSubject() {
		return bookSubject;
	}

	public void setBookSubject(String bookSubject) {
		this.bookSubject = bookSubject == null ? null : bookSubject.trim();
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress == null ? null : bookPress.trim();
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc == null ? null : bookDesc.trim();
	}

	public Float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookDegree() {
		return bookDegree;
	}

	public void setBookDegree(String bookDegree) {
		this.bookDegree = bookDegree == null ? null : bookDegree.trim();
	}

	public Integer getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Date getBookUploadDate() {
		return bookUploadDate;
	}

	public void setBookUploadDate(Date bookUploadDate) {
		this.bookUploadDate = bookUploadDate;
	}

	public String getBookSellerId() {
		return bookSellerId;
	}

	public void setBookSellerId(String bookSellerId) {
		this.bookSellerId = bookSellerId;
	}

	public Float getBookOldPrice() {
		return bookOldPrice;
	}

	public void setBookOldPrice(Float bookOldPrice) {
		this.bookOldPrice = bookOldPrice;
	}

	public String getBookNotes() {
		return bookNotes;
	}

	public void setBookNotes(String bookNotes) {
		this.bookNotes = bookNotes;
	}
}