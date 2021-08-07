package com.cubtp.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cubtp.service.BookService;
import com.cubtp.util.ReturnDataInit;
import com.cubtp.vo.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private ReturnDataInit returnDataInit;

	/**
	 * 书籍上传
	 * 
	 * @param Book
	 * @return JSONObject
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(@RequestParam("file") CommonsMultipartFile file, Book book) throws IOException {

		JSONObject jsonObject = returnDataInit.initSetting();

		System.out.println("fileName：" + file.getOriginalFilename());
		System.out.println(book.getBookName());
		System.out.println(book.getBookPrice());

		String path = "C:\\Users\\Jason\\Desktop\\cubtp\\src\\assets\\images\\book_cover_img\\" + file.getOriginalFilename();

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		// long endTime=System.currentTimeMillis();
		// System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
		// 放入图片文件名
		book.setBookCover(file.getOriginalFilename());

		book.setBookStatus(0);

		boolean flag = bookService.bookUpload(book);

		// 判断是否上传成功
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}

	/**
	 * 分页获取书籍
	 * 
	 * @param params
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getBook", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getBook(@RequestBody Map<String, Integer> params) {
		System.out.println(params.get("currentPage") + "------" + params.get("pageSize"));
		JSONObject jsonObject = returnDataInit.initSetting();
		int currentPage = params.get("currentPage");
		int pageSize = params.get("pageSize");

		List<Book> book = bookService.getBook(currentPage, pageSize);

		if (book != null && book.size() > 0) {
			jsonObject.put("data", book);
		} else {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}

	/**
	 * 获取书籍总数
	 * 
	 * @param null
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getBookCount", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getBookCount() {
		JSONObject jsonObject = returnDataInit.initSetting();

		int total = bookService.getBookCount();
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("total", total);
		if (total > 0) {
			jsonObject.put("data", data);
		} else {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		return jsonObject;
	}

	/**
	 * 获取书籍详细信息
	 * 
	 * @param BookId
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getBookInfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getBookInfo(int bookId) {
		System.out.println(bookId);
		JSONObject jsonObject = returnDataInit.initSetting();

		Book book = bookService.getBookInfo(bookId);
		if (book == null) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		jsonObject.put("data", book);

		return jsonObject;
	}

	/**
	 * 根据sellerId查询书籍列表
	 * 
	 * @param sellerId
	 * @return JSONObject
	 */

	@RequestMapping(value = "/getBookBySellerId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getBookBySellerId(String sellerId) {
		System.out.println(sellerId);
		JSONObject jsonObject = returnDataInit.initSetting();

		List<Book> bookList = bookService.getBookBySellerId(sellerId);

		if (bookList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}

		jsonObject.put("data", bookList);

		return jsonObject;
	}

	/**
	 * 书籍修改
	 * 
	 * @param book
	 * @return JSONObject
	 */
	@RequestMapping(value = "/editBookInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject editBookInfo(@RequestParam(value ="file",required = false) CommonsMultipartFile file, Book book) throws IOException {

		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = false;
		if (file != null) {
			String path = "C:\\Users\\Jason\\Desktop\\cubtp\\src\\assets\\images\\book_cover_img\\" + file.getOriginalFilename();

			File newFile = new File(path);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			flag = bookService.editBookInfo(book);
		} else {
			flag = bookService.editBookInfo(book);
		}

		// 判断是否上传成功
		if (flag == false) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		}
		return jsonObject;
	}
	
	/**
	 * 删除书籍
	 * 
	 * @param bookId
	 * @return JSONObject
	 */
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject deleteBook(int bookId) {
		System.out.println(bookId);  
		
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = bookService.deleteBook(bookId);

		 if (flag == false) {
				jsonObject.put("code", 1);
				jsonObject.put("msg", "fail");
			}
			return jsonObject;
	}
	
	/** 
	 *  获取所有未审核的书籍
	 * @param null
	 * @return JSONObject
	 * */
	
	@RequestMapping(value = "/getAllNotcheckedBook", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllNotcheckedBook() {
		 
		
		JSONObject jsonObject = returnDataInit.initSetting();

		List<Book> bookList = bookService.getAllNotcheckedBook();

		 if (bookList.size() == 0) {
				jsonObject.put("code", 1);
				jsonObject.put("msg", "fail");
			} else {
				jsonObject.put("data", bookList);
			}
			return jsonObject;
	}
	
	/** 
	 *  获取所有已审核的书籍
	 * @param null
	 * @return JSONObject
	 * */
	
	@RequestMapping(value = "/getAllCheckedBook", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllCheckedBook() {
		 
		
		JSONObject jsonObject = returnDataInit.initSetting();

		List<Book> bookList = bookService.getAllCheckedBook();

		 if (bookList.size() == 0) {
				jsonObject.put("code", 1);
				jsonObject.put("msg", "fail");
			} else {
				jsonObject.put("data", bookList);
			}
			return jsonObject;
	}
	
	/**
	 *  通过JSON修改书籍信息(审核通过)
	 * @param bookId,bookStatus
	 * @return JSONObject
	 * */
	@RequestMapping(value = "/updateBookInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateBookStatus(@RequestBody Book book) {
		 
		
		JSONObject jsonObject = returnDataInit.initSetting();

		boolean flag = bookService.updateBookInfo(book);

		 if (flag == false) {
				jsonObject.put("code", 1);
				jsonObject.put("msg", "fail");
			}
			return jsonObject;
	}
	
	/**
	 * 书籍搜索
	 * @param map<String, Object>
	 * @return List
	 * */
	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject searchBook(@RequestBody Map<String, Object> params) {
		JSONObject jsonObject = returnDataInit.initSetting();
		System.out.println(params.get("currentPage") + "------" + params.get("pageSize"));
		List<Book> bookList = bookService.searchBook(params);
		
		if (bookList.size() == 0) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "fail");
		} else {
			jsonObject.put("data", bookList);
		}
		return jsonObject;
	}
	
	/**
	 * 获取书籍搜索总条数
	 * @param map<String, Object>
	 * @return List
	 * */
	@RequestMapping(value = "/getSearchBookCount", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getSearchBookCount(@RequestBody Map<String, Object> params) {
		JSONObject jsonObject = returnDataInit.initSetting();

		int SearchBookCount = bookService.getSearchBookCount(params);
		System.out.println(SearchBookCount);
		Map<String,Integer> total = new HashMap<String, Integer>();
		total.put("total", SearchBookCount);
		
		if (SearchBookCount >= 0) {
			jsonObject.put("data", total);
		}
		return jsonObject;
	}

}
