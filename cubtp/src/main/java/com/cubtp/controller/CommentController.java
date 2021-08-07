package com.cubtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cubtp.service.CommentService;
import com.cubtp.util.ReturnDataInit;
import com.cubtp.vo.Comment;

@RequestMapping("/comment")
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReturnDataInit returnDataInit;
	
	/**
	 * 获取该用户对该书籍的所有评论
	 * @param comment.userId,comment.bookId
	 * @return boolean
	 * */
	@RequestMapping(value = "/getAllCommentByUidAndBid", method = RequestMethod.POST)
	@ResponseBody 
	public JSONObject getAllCommentByUidAndBid(@RequestBody Comment comment) {
		System.out.println(comment.getCommentUserId());
		
		JSONObject jsonObject = returnDataInit.initSetting();
		
		List<Comment> commentList = commentService.getAllCommentByUidAndBid(comment);
		
		if (commentList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", commentList);
		}
		return jsonObject;
	}
	
	/**
	 * 获取该书籍的所有评论
	 * @param comment.bookId
	 * @return boolean
	 * */
	
	@RequestMapping(value = "/getAllCommentByBid", method = RequestMethod.POST)
	@ResponseBody 
	public JSONObject getAllCommentByBid(@RequestBody Comment comment) {
		JSONObject jsonObject = returnDataInit.initSetting();
		
		List<Comment> commentList = commentService.getAllCommentByBid(comment);
		
		if (commentList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", commentList);
		}
		return jsonObject;
	}
	
	/**
	 * 添加评论
	 * @param comment
	 * @return boolean
	 * */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody 
	public JSONObject addComment(@RequestBody Comment comment) {
		JSONObject jsonObject = returnDataInit.initSetting();
		
		boolean flag = commentService.addCommment(comment);
		
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}
	
	

}
