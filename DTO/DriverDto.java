package com.alpha.ABClogistics.DTO;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class DriverDto {
	@Id
	
	private int id;
	private String name;
	
	@NumberFormat
	private long contact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public DriverDto(int id, String name, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
	}
	public DriverDto() {
		super();
	}
	

}
