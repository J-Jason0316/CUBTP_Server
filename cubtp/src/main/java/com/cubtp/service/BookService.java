package com.cubtp.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubtp.dao.BookDao;
import com.cubtp.vo.Book;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	
	/**
	 * 书籍上传
	 * @param Book
	 * @return flag boolean
	 * */
	public boolean bookUpload(Book book) {
		boolean flag = false;
		Date utilDate = new Date();
		Timestamp timeStamep = new Timestamp(utilDate.getTime());
		book.setBookUploadDate(timeStamep);
		int status = bookDao.insertSelective(book);
		if (status == 1) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 分页获取书籍
	 * 
	 * @param currentPage,pageSize
	 * @return JSONObject
	 */
	public List<Book> getBook(int currentPage, int pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currentIndex", (currentPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return bookDao.getBook(data);
    }
	
	/**
	  *  获取书籍总数
	 *  
	 * @param null
	 * @return JSONObject
	 */
	public int getBookCount() {
		 return bookDao.getBookCount();
	}
	
	/**
	 * 获取书籍详细信息
	 * 
	 * @param BookId
	 * @return JSONObject
	 */
	
	public Book getBookInfo(int bookId) {
		return bookDao.selectByPrimaryKey(bookId);
	}
	
	/**
	 * 根据sellerId查询书籍列表
	 * 
	 * @param sellerId
	 * @return JSONObject
	 */
	public List<Book> getBookBySellerId(String sellerId) {
		return bookDao.getBookBySellerId(sellerId);
	}
	
	/**
	 * 书籍修改
	 * 
	 * @param book
	 * @return JSONObject
	 */ 
	public boolean editBookInfo(Book book) {
		boolean flag = false;
		book.setBookStatus(0);
		int status = bookDao.updateByPrimaryKeySelective(book);
		if (status == 1) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 删除书籍
	 * 
	 * @param bookId
	 * @return JSONObject
	 */
	
	public boolean deleteBook(int bookId) {
		boolean flag = false;
		int status = bookDao.deleteByPrimaryKey(bookId);
		if (status == 1) {
			flag = true;
		}
		return flag;
	}
	
	/** 
	 *  获取所有未审核的书籍
	 * @param null
	 * @return JSONObject
	 * */
	
	public List<Book> getAllNotcheckedBook() {
		return bookDao.getAllNotcheckedBook();
	}
	
	/** 
	 *  获取所有已审核的书籍
	 * @param null
	 * @return JSONObject
	 * */
	
	public List<Book> getAllCheckedBook() {
		return bookDao.getAllCheckedBook();
	}
	
	/**
	 *  通过JSON修改书籍信息
	 * @param bookId,bookStatus
	 * @return JSONObject
	 * */
	public boolean updateBookInfo(Book book) {
		boolean flag = false;
		int status = bookDao.updateByPrimaryKeySelective(book);
		if (status == 1) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 书籍搜索
	 * @param map<String, Object>
	 * @return List
	 * */
	public List<Book> searchBook(Map<String, Object> data) {
		int currentPage = (int) data.get("currentPage");
		int pageSize = (int) data.get("pageSize");
		data.put("currentIndex", (currentPage-1)*pageSize);
		data.remove("currentPage");
		return bookDao.searchBook(data);
	}
	
	/**
	 * 获取书籍搜索总条数
	 * @param map<String, Object>
	 * @return List
	 * */
	public int getSearchBookCount(Map<String, Object> data) {	
		System.out.println(data.toString()+"---------"+bookDao.getSearchBookCount(data));
		return bookDao.getSearchBookCount(data);
	}
	
}
