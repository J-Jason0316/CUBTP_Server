package com.cubtp.dao;

import java.util.List;


import com.cubtp.vo.BookType;

public interface BookTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookType record);

    int insertSelective(BookType record);

    BookType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);
    
    List<BookType> getAllBookType();
}