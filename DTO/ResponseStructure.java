package com.alpha.ABClogistics.DTO;



public class ResponseStructure<T> {
	private int statuscode;
	private String meaasage;
	private T data;
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getMeaasage() {
		return meaasage;
	}
	public void setMeaasage(String meaasage) {
		this.meaasage = meaasage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ResponseStructure() {
		super();
		this.statuscode = statuscode;
		this.meaasage = meaasage;
		this.data = data;
	}
	
		
	
	

}
