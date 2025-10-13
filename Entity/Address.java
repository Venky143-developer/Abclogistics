package com.alpha.ABClogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", pincode=" + pincode + ", state="
				+ state + "]";
	}
	@Id
	@NotNull
	private int id;
	@NotNull
	private String street;
	@NotNull
	private String city;
	@NotNull
	private int pincode;
	@NotNull
	private String state;
	@NotNull
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Address(int id, String street, String city, int pincode, String state) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}
	public Address() {
		super();
	}
	

}
