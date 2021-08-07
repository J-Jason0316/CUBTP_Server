package com.cubtp.dao;

import java.util.List;
import java.util.Map;

import com.cubtp.vo.Book;

public interface BookDao {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    
    
    int getBookCount();
    
    List<Book> getBook(Map<String, Object> data);
    
    List<Book> getBookBySellerId(String bookSellerId);
    
    List<Book> getAllNotcheckedBook();
    
    List<Book> getAllCheckedBook();
    
    List<Book> MultiConditionalQuery();
    
    List<Book> searchBook(Map<String, Object> data);
    
    int getSearchBookCount(Map<String, Object> data);
}