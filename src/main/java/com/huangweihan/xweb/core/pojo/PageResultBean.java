package com.huangweihan.xweb.core.pojo;

import java.util.List;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/18 0016
 */
public class PageResultBean<T> {

	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int CHECK_FAIL = 1;

	public static final int NO_PERMISSION = 2;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public static final int UNKNOWN_EXCEPTION = -99;


	/**
	 * 返回的信息(主要出错的时候使用)
	 */
	private String msg = "success";

	/**
	 * 接口返回码, 0表示成功, 其他看对应的定义
	 * 晓风轻推荐的做法是:
	 * 0   : 成功
	 * >0 : 表示已知的异常(例如提示错误等, 调用方单独处理)
	 * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
	 */
	private int code = SUCCESS;

	/**
	 * 开始页码
	 */
	private int start;

	/**
	 * 结束页码
	 */
	private int end;

	/**
	 * 页数
	 */
	private int size;

	/**
	 * 返回的数据
	 */
	private List<?> data;

	public PageResultBean() {
		super();
	}

	public PageResultBean(List<?> data) {
		super();
		this.data = data;
	}

	public PageResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = UNKNOWN_EXCEPTION;
	}
}
