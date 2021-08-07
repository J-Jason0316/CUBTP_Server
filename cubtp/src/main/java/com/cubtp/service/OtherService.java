package com.cubtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubtp.dao.BookSubjectDao;
import com.cubtp.dao.BookTypeDao;
import com.cubtp.vo.BookSubject;
import com.cubtp.vo.BookType;

@Service
public class OtherService {
	@Autowired
	private BookSubjectDao bookSubjectDao;
	@Autowired
	private BookTypeDao bookTypeDao;
	
	
	/**
	 * 获取所有的书籍学科
	 * @param null
	 * @return List
	 * */
	public List<BookSubject> getAllBookSubject(){
		return bookSubjectDao.getAllBookSubject();
	}
	
	/**
	 * 获取所有的书籍类型
	 * @param null
	 * @return List
	 * */
	public List<BookType> getAllBookType(){
		return bookTypeDao.getAllBookType();
	}
}
