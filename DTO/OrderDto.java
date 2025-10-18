package com.alpha.ABClogistics.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDto {
	@Id
	@NotNull
	private int id;
	@NotNull(message= "order date is requried")
	private String orderdate;
	
	private int cargoID;
	private String cargoName;
	private String cargoDescription;
	@Email(message="Email format is missing")
	private String email;
	
	
	public OrderDto(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCarrier() {
		return Carrier;
	}

	public void setCarrier(int carrier) {
		Carrier = carrier;
	}

	private int Carrier;
	
	@NotNull(message= "must enter the cargo weight")
	@Positive
	private int cargowt;
	
	@NotNull(message= "Atleast one count need to be entered")
	@Positive
	@Min(1)
	private int cargoCount;
	@NotNull
	
	private int loadingAddId;
	
	@NotNull
	private int unloadingAddId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public int getCargoID() {
		return cargoID;
	}

	public void setCargoID(int cargoID) {
		this.cargoID = cargoID;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public int getCargowt() {
		return cargowt;
	}

	public void setCargowt(int cargowt) {
		this.cargowt = cargowt;
	}

	public int getCargoCount() {
		return cargoCount;
	}

	public void setCargoCount(int cargoCount) {
		this.cargoCount = cargoCount;
	}

	public int getLoadingAddId() {
		return loadingAddId;
	}

	public void setLoadingAddId(int loadingAddId) {
		this.loadingAddId = loadingAddId;
	}

	public int getUnloadingAddId() {
		return unloadingAddId;
	}

	public void setUnloadingAddId(int unloadingAddId) {
		this.unloadingAddId = unloadingAddId;
	}

	public OrderDto(int id, String orderdate, int cargoID, String cargoName, String cargoDescription, int cargowt,
			int cargoCount, int loadingAddId, int unloadingAddId) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.cargoID = cargoID;
		this.cargoName = cargoName;
		this.cargoDescription = cargoDescription;
		this.cargowt = cargowt;
		this.cargoCount = cargoCount;
		this.loadingAddId = loadingAddId;
		this.unloadingAddId = unloadingAddId;
	}

	public OrderDto() {
		super();
	}
	

}
