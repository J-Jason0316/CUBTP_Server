package com.cubtp.dao;

import java.util.List;

import com.cubtp.vo.BookSubject;

public interface BookSubjectDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookSubject record);

    int insertSelective(BookSubject record);

    BookSubject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookSubject record);

    int updateByPrimaryKey(BookSubject record);
    
    List<BookSubject> getAllBookSubject();
}