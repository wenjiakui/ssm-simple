package com.wjk.web.ssm.utils;

import java.io.Serializable;

//{"status":xx,"msg":xxx,"data":xxx}
public class Result<T> implements Serializable{
	private int status;
	private String msg;
	private T data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
