package com.alpha.ABClogistics.DTO;

import com.alpha.ABClogistics.Entity.Address;

public class UnLoadingDto {
	
	
		private int id;
		
		
		private String date;
		private String time;
		private Address address;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public UnLoadingDto(int id, String date, String time, Address address) {
			super();
			this.id = id;
			this.date = date;
			this.time = time;
			this.address = address;
		}
		public UnLoadingDto() {
			super();
		}
		@Override
		public String toString() {
			return "UnLoadingDto [id=" + id + ", date=" + date + ", time=" + time + ", address=" + address + "]";
		}


	}



