package com.cubtp.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubtp.dao.CommentDao;
import com.cubtp.vo.Comment;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	/**
	 * 获取该用户对该书籍的所有评论
	 * @param userId,bookId
	 * @return boolean
	 * */
	public List<Comment> getAllCommentByUidAndBid(Comment comment) {
        return commentDao.MultiConditionalQuery(comment);	
	}
	
	/**
	 * 获取该书籍的所有评论
	 * @param bookId
	 * @return boolean
	 * */
	public List<Comment> getAllCommentByBid(Comment comment) {
        return commentDao.MultiConditionalQuery(comment);	
	}
	
	/**
	 * 添加评论
	 * @param comment
	 * @return boolean
	 * */
	public boolean addCommment(Comment comment) {
		boolean flag = false;
		Date utilDate = new Date();
		Timestamp timeStamep = new Timestamp(utilDate.getTime());
		comment.setCommentDate(timeStamep);
		
		int status = commentDao.insertSelective(comment);
		if (status==1) {
			flag = true ;
		}
		return flag;
	}
	
	/**
	 * 删除评论
	 * @param comment
	 * @return boolean
	 * */
	public boolean deleteCommment(int commentId) {
		boolean flag = false;
		
		int status = commentDao.deleteByPrimaryKey(commentId);
		if (status==1) {
			flag = true ;
		}
		return flag;
	}
	
	/**
	 * 编辑评论
	 * @param comment
	 * @return boolean
	 * */
	public boolean editCommment(Comment comment) {
		boolean flag = false;
		
		int status = commentDao.updateByPrimaryKeySelective(comment);
		if (status==1) {
			flag = true ;
		}
		return flag;
	}
	
}
